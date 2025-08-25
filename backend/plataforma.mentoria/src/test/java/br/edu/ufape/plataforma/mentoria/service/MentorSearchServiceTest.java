package br.edu.ufape.plataforma.mentoria.service;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentorMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.repository.MentorRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MentorSearchServiceTest {

    @InjectMocks
    private MentorSearchService mentorSearchService;

    @Mock
    private MentorRepository mentorRepository;

    @Mock
    private MentorMapper mentorMapper;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMentorByIdFound() {
        Mentor mentor = new Mentor.Builder().build();
        when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));
        Mentor result = mentorSearchService.getMentorById(1L);
        assertEquals(mentor, result);
    }

    @Test
    void testGetMentorByIdNotFound() {
        when(mentorRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> mentorSearchService.getMentorById(2L));
    }

    @Test
    void testGetMentorDetailsDTO() {
        Mentor mentor = new Mentor.Builder().build();
        MentorDTO dto = new MentorDTO.Builder().build();
        when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));
        when(mentorMapper.toDTO(mentor)).thenReturn(dto);
        MentorDTO result = mentorSearchService.getMentorDetailsDTO(1L);
        assertEquals(dto, result);
    }

    @Test
    void testGetAllMentors() {
        Mentor mentor1 = new Mentor.Builder().build();
        Mentor mentor2 = new Mentor.Builder().build();
        List<Mentor> mentors = Arrays.asList(mentor1, mentor2);
        when(mentorRepository.findAll()).thenReturn(mentors);
        List<Mentor> result = mentorSearchService.getAllMentors();
        assertEquals(mentors, result);
    }

    @Test
    void testGetCurrentMentorFound() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("user@gmail.com");
        SecurityContextHolder.getContext().setAuthentication(auth);

        Mentor mentor = new Mentor.Builder().build();
        MentorDTO dto = new MentorDTO.Builder().build();
        when(mentorRepository.findByUserEmail("user@gmail.com")).thenReturn(Optional.of(mentor));
        when(mentorMapper.toDTO(mentor)).thenReturn(dto);

        MentorDTO result = mentorSearchService.getCurrentMentor();
        assertEquals(dto, result);
    }

    @Test
    void testGetCurrentMentorNotFound() {
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("notfound@email.com");
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(mentorRepository.findByUserEmail("notfound@email.com")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> mentorSearchService.getCurrentMentor());
    }

    @Test
    void testFindByInterestAreaAndSpecializations() {
        Mentor mentor1 = new Mentor.Builder().build();
        Mentor mentor2 = new Mentor.Builder().build();
        MentorDTO dto1 = new MentorDTO.Builder().build();
        MentorDTO dto2 = new MentorDTO.Builder().build();
        List<Mentor> mentors = Arrays.asList(mentor1, mentor2);

        when(mentorRepository.findByInterestAreaAndSpecializationsContaining(InterestArea.CIBERSEGURANCA, "Java"))
                .thenReturn(mentors);
        when(mentorMapper.toDTO(mentor1)).thenReturn(dto1);
        when(mentorMapper.toDTO(mentor2)).thenReturn(dto2);

        List<MentorDTO> result = mentorSearchService.findByInterestAreaAndSpecializations(InterestArea.CIBERSEGURANCA,
                "Java");
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }
}