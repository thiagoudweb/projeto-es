package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;
import br.edu.ufape.plataforma.mentoria.mapper.MentoredMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentored;
import br.edu.ufape.plataforma.mentoria.repository.MentoredRepository;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MentoredService {

    @Autowired
    private MentoredRepository mentoredRepository;

    @Autowired
    private MentoredMapper mentoredMapper;

    @Autowired
    private UserRepository userRepository;

    public Mentored getMentoredById(Long id) throws Exception {
        return mentoredRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Mentored.class, id));
    }

    public MentoredDTO getMentoredDetailsDTO(Long id) throws Exception {
        Mentored mentored = getMentoredById(id);
        return mentoredMapper.toDTO(mentored);
    }

    public List<Mentored> getAllMentored() {
        return mentoredRepository.findAll();
    }

    public Mentored createMentored(Mentored mentored) {
        if (mentoredRepository.existsByCpf(mentored.getCpf())) {
            throw new AttributeAlreadyInUseException("CPF", mentored.getCpf(), Mentored.class);
        }
        return mentoredRepository.save(mentored);
    }

    public MentoredDTO createMentored(MentoredDTO mentoredDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail);
        Mentored mentored = mentoredMapper.toEntity(mentoredDTO);

        if (mentoredRepository.existsByCpf(mentored.getCpf())) {
            throw new AttributeAlreadyInUseException("CPF", mentored.getCpf(), Mentored.class);
        }

        mentored.setUser(user);
        Mentored savedMentored = mentoredRepository.save(mentored); // Salva o objeto já configurado
        return mentoredMapper.toDTO(savedMentored);
    }

    public Mentored updateMentored(Long id, Mentored mentored) throws Exception {
        if (mentoredRepository.existsById(id)) {
            mentored.setId(id);
            return mentoredRepository.save(mentored);
        }
        throw new EntityNotFoundException(Mentored.class, id);
    }

    public MentoredDTO updateMentored(Long id, MentoredDTO mentoredDTO) throws Exception {
        Mentored existingMentored = mentoredRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Mentored.class, id));
        
        Mentored mentoredToUpdate = mentoredMapper.toEntity(mentoredDTO);
        
        mentoredToUpdate.setId(id);
        mentoredToUpdate.setUser(existingMentored.getUser());
        
        Mentored updatedMentored = mentoredRepository.save(mentoredToUpdate);
        
        return mentoredMapper.toDTO(updatedMentored);
    }

    public void deleteById(Long id) throws Exception {
        if (!mentoredRepository.existsById(id)) {
            throw new EntityNotFoundException(Mentored.class, id);
        }
        mentoredRepository.deleteById(id);
    }

    public MentoredDTO getCurrentMentored() throws EntityNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return mentoredRepository.findByUserEmail(email)
                .map(mentoredMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(Mentored.class, email));
    }

    public List<MentoredDTO> findByInterestArea(InterestArea interestArea) {
        List<Mentored> mentoreds = mentoredRepository.findByInterestArea(interestArea);
        return mentoreds.stream()
                .map(mentoredMapper::toDTO)
                .collect(Collectors.toList());
    }
}