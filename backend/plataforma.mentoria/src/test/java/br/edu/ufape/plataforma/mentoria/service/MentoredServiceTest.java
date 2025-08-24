package br.edu.ufape.plataforma.mentoria.service;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.dto.UpdateMentoredDTO;
import br.edu.ufape.plataforma.mentoria.enums.Course;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.MentoredRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class MentoredServiceTest {

    @InjectMocks
    private MentoredService mentoredService;

    @Mock
    private MentoredRepository mentoredRepository;

    @Mock
    private MentoredSearchService mentoredSearchService;

    @Mock
    private MentoredMapper mentoredMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testGetAllMentored() {
        Mentored m1 = new Mentored();
        Mentored m2 = new Mentored();
        when(mentoredRepository.findAll()).thenReturn(Arrays.asList(m1, m2));
        List<Mentored> result = mentoredService.getAllMentored();
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateMentored_Success() {
        MentoredDTO dto = new MentoredDTO();
        Mentored mentored = new Mentored();
        User user = new User();
        Mentored savedMentored = new Mentored();
        MentoredDTO savedDto = new MentoredDTO();

        when(authentication.getName()).thenReturn("test@email.com");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userRepository.findByEmail("test@email.com")).thenReturn(user);
        when(mentoredMapper.toEntity(dto)).thenReturn(mentored);
        when(mentoredRepository.existsByCpf(mentored.getCpf())).thenReturn(false);
        when(mentoredRepository.save(any(Mentored.class))).thenReturn(savedMentored);
        when(mentoredMapper.toDTO(savedMentored)).thenReturn(savedDto);

        MentoredDTO result = mentoredService.createMentored(dto);
        assertEquals(savedDto, result);
    }

    @Test
    public void testCreateMentored_CpfAlreadyExists() {
        MentoredDTO dto = new MentoredDTO();
        Mentored mentored = new Mentored();
        mentored.setCpf("123");
        when(authentication.getName()).thenReturn("test@email.com");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userRepository.findByEmail("test@email.com")).thenReturn(new User());
        when(mentoredMapper.toEntity(dto)).thenReturn(mentored);
        when(mentoredRepository.existsByCpf("123")).thenReturn(true);

        AttributeAlreadyInUseException thrown = assertThrows(AttributeAlreadyInUseException.class, () -> mentoredService.createMentored(dto));
        assertNotNull(thrown);
    }

    @Test
    public void testUpdateMentored_WithMentoredObject_Success() {
        Mentored mentored = new Mentored();
        mentored.setId(1L);
        when(mentoredRepository.existsById(1L)).thenReturn(true);
        when(mentoredRepository.save(mentored)).thenReturn(mentored);

        Mentored result = mentoredService.updateMentored(1L, mentored);
        assertEquals(mentored, result);
    }

    @Test
    public void testUpdateMentored_WithMentoredObject_NotFound() {
        Mentored mentored = new Mentored();
        when(mentoredRepository.existsById(1L)).thenReturn(false);
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> mentoredService.updateMentored(1L, mentored));
        assertNotNull(thrown);
    }

    @Test
    public void testUpdateMentored_WithDTO_Success() {
        MentoredDTO dto = new MentoredDTO();
        Mentored existing = new Mentored();
        existing.setUser(new User());
        Mentored toUpdate = new Mentored();
        Mentored updated = new Mentored();
        MentoredDTO updatedDto = new MentoredDTO();

        when(mentoredSearchService.getMentoredById(1L)).thenReturn(existing);
        when(mentoredMapper.toEntity(dto)).thenReturn(toUpdate);
        when(mentoredRepository.save(toUpdate)).thenReturn(updated);
        when(mentoredMapper.toDTO(updated)).thenReturn(updatedDto);

        MentoredDTO result = mentoredService.updateMentored(1L, dto);
        assertEquals(updatedDto, result);
    }

    @Test
    public void testUpdateMentored_WithUpdateMentoredDTO_Success() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        dto.setFullName("New Name");
        dto.setBirthDate(java.time.LocalDate.now());
        dto.setCourse(Course.CIENCIA_DA_COMPUTACAO); 
        dto.setAcademicSummary("Summary");
        dto.setInterestArea(Arrays.asList(InterestArea.TECNOLOGIA_DA_INFORMACAO));

        Mentored mentored = new Mentored();
        when(mentoredSearchService.getMentoredById(1L)).thenReturn(mentored);
        when(mentoredRepository.save(mentored)).thenReturn(mentored);

        Mentored result = mentoredService.updateMentored(1L, dto);
        assertEquals(mentored, result);
        assertEquals("New Name", mentored.getFullName());
        assertEquals(Course.CIENCIA_DA_COMPUTACAO, mentored.getCourse());
        assertEquals("Summary", mentored.getAcademicSummary());
        assertEquals(Arrays.asList(InterestArea.TECNOLOGIA_DA_INFORMACAO), mentored.getInterestArea());
    }

    @Test
    public void testUpdateMentored_WithUpdateMentoredDTO_NullValues() {
        UpdateMentoredDTO dto = new UpdateMentoredDTO();
        dto.setFullName(null);
        dto.setBirthDate(null);
        dto.setCourse(null); 
        dto.setAcademicSummary(null);
        dto.setInterestArea(null);

        Mentored mentored = new Mentored();
        mentored.setFullName("Original Name");
        mentored.setCourse(Course.ADMINISTRACAO);
        mentored.setAcademicSummary("Original Summary");
        mentored.setInterestArea(Arrays.asList(InterestArea.ADMINISTRACAO_E_GESTAO));
        
        when(mentoredSearchService.getMentoredById(1L)).thenReturn(mentored);
        when(mentoredRepository.save(mentored)).thenReturn(mentored);

        Mentored result = mentoredService.updateMentored(1L, dto);
        assertEquals(mentored, result);
        assertEquals("Original Name", mentored.getFullName());
        assertEquals(Course.ADMINISTRACAO, mentored.getCourse());
        assertEquals("Original Summary", mentored.getAcademicSummary());
        assertEquals(Arrays.asList(InterestArea.ADMINISTRACAO_E_GESTAO), mentored.getInterestArea());
    }

    @Test
    public void testDeleteById_Success() {
        when(mentoredRepository.existsById(1L)).thenReturn(true);
        doNothing().when(mentoredRepository).deleteById(1L);
        assertDoesNotThrow(() -> mentoredService.deleteById(1L));
        verify(mentoredRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteById_NotFound() {
        when(mentoredRepository.existsById(1L)).thenReturn(false);
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> mentoredService.deleteById(1L));
        assertNotNull(thrown);
    }
}
