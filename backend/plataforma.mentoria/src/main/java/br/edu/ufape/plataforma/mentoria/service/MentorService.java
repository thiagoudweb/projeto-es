package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;
import java.util.stream.Collectors;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.dto.UpdateMentorDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;
import br.edu.ufape.plataforma.mentoria.exceptions.EntityNotFoundException;
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

    public Mentor getMentorById(Long id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Mentor.class, id));
    }

    public MentorDTO getMentorDetailsDTO(Long id) {
        Mentor mentor = this.getMentorById(id);
        return mentorMapper.toDTO(mentor);
    }

    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
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

    public Mentor updateMentor(Long id, Mentor mentor) {
        if (mentorRepository.existsById(id)) {
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }
        throw new EntityNotFoundException(Mentor.class, id);
    }

    public MentorDTO updateMentor(Long id, MentorDTO mentorDTO) {

        Mentor existingMentor = this.getMentorById(id);
        
        Mentor mentorToUpdate = mentorMapper.toEntity(mentorDTO);
        
        mentorToUpdate.setId(id);
        mentorToUpdate.setUser(existingMentor.getUser());
        
        Mentor updatedMentor = mentorRepository.save(mentorToUpdate);
        
        return mentorMapper.toDTO(updatedMentor);
    }

    public Mentor updateMentor(Long id, UpdateMentorDTO dto) {
        Mentor mentor = this.getMentorById(id);

        if (dto.getFullName() != null) {
            mentor.setFullName(dto.getFullName());
        }

        if (dto.getBirthDate() != null) {
            mentor.setBirthDate(dto.getBirthDate());
        }

        if (dto.getCourse() != null) {
            mentor.setCourse(dto.getCourse());
        }

        if (dto.getInterestArea() != null) {
            mentor.setInterestArea(dto.getInterestArea());
        }

        if (dto.getProfessionalSummary() != null) {
            mentor.setProfessionalSummary(dto.getProfessionalSummary());
        }

        if (dto.getAffiliationType() != null) {
            mentor.setAffiliationType(dto.getAffiliationType());
        }

        if (dto.getSpecializations() != null) {
            mentor.setSpecializations(dto.getSpecializations());
        }

        return mentorRepository.save(mentor);
    }

    public void deleteById(Long id){
        if (!mentorRepository.existsById(id)) {
            throw new EntityNotFoundException(Mentor.class, id);
        }
        mentorRepository.deleteById(id);
    }

    public MentorDTO getCurrentMentor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return mentorRepository.findByUserEmail(email)
                .map(mentorMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException(Mentor.class, email));
    }

    public List<MentorDTO> findByInterestAreaAndSpecializations(InterestArea interestArea, String specialization) {
        List<Mentor> mentors = mentorRepository.findByInterestAreaAndSpecializationsContaining(interestArea, specialization);
        return mentors.stream()
                .map(mentorMapper::toDTO)
                .collect(Collectors.toList());
    }
}