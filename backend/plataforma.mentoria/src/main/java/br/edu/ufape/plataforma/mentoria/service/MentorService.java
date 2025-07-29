package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestAreas;
import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;
import br.edu.ufape.plataforma.mentoria.exceptions.MentorNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentorMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.MentorRepository;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;

@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    
    @Autowired
    private MentorMapper mentorMapper;

    @Autowired
    private UserRepository userRepository;

    public Mentor getMentorById(Long id) throws Exception {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException(id));
    }

    public MentorDTO getMentorDetailsDTO(Long id) throws Exception {
        Mentor mentor = getMentorById(id); 
        return mentorMapper.toDTO(mentor);
    }


    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Mentor createMentor(Mentor mentor) {
        if (mentorRepository.existsByCpf(mentor.getCpf())) {
            throw new AttributeAlreadyInUseException("CPF", mentor.getCpf(), Mentor.class);
        }
        return mentorRepository.save(mentor);
    }

    public MentorDTO createMentor(MentorDTO mentorDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); 
        
        User user = userRepository.findByEmail(userEmail);

        Mentor mentor = mentorMapper.toEntity(mentorDTO);
        
        if (mentorRepository.existsByCpf(mentor.getCpf())) {
            throw new AttributeAlreadyInUseException("CPF", mentor.getCpf(), Mentor.class);
        }
        
        mentor.setUser(user);

        Mentor savedMentor = mentorRepository.save(mentor);
        return mentorMapper.toDTO(savedMentor);
    }

    public Mentor updateMentor(Long id, Mentor mentor) throws Exception {
        if (mentorRepository.existsById(id)) {
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }
        throw new MentorNotFoundException(id);
    }

    public MentorDTO updateMentor(Long id, MentorDTO mentorDTO) throws Exception {
        if (mentorRepository.existsById(id)) {
            Mentor mentor = mentorMapper.toEntity(mentorDTO);
            mentor.setId(id);
            User user = userRepository.findById(mentor.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + mentor.getUser().getId()));
            mentor.setUser(user);
            Mentor updatedMentor = mentorRepository.save(mentor);
            return mentorMapper.toDTO(updatedMentor);
        }
        throw new MentorNotFoundException(id);
    }

    public void deleteById(Long id) throws Exception {
        if (!mentorRepository.existsById(id)) {
            throw new MentorNotFoundException(id);
        }
        mentorRepository.deleteById(id);
    }

    public void deleteMentor(Long id) throws MentorNotFoundException {
        if (!mentorRepository.existsById(id)) {
            throw new MentorNotFoundException(id);
        }
        mentorRepository.deleteById(id);
    }

    public List<MentorDTO> findByNameAndInterestArea(String fullName, InterestAreas interestArea) {
        List<Mentor> mentors = mentorRepository.findByFullNameContainingIgnoreCaseAndInterestAreas(fullName, interestArea);
        return mentors.stream()
                .map(mentorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MentorDTO getCurrentMentor() throws MentorNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return mentorRepository.findByUserEmail(email)
                .map(mentorMapper::toDTO)
                .orElseThrow(() -> new MentorNotFoundException("Mentor não encontrado"));
    }
}