package br.edu.ufape.plataforma.mentoria.service;

import br.edu.ufape.plataforma.mentoria.service.contract.MentorServiceInterface;
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
public class MentorService implements MentorServiceInterface {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorMapper mentorMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentorSearchService mentorSearchService;

    @Override
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
    @Override
    public Mentor updateMentor(Long id, Mentor mentor) {
        if (mentorRepository.existsById(id)) {
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }
        throw new EntityNotFoundException(Mentor.class, id);
    }
    @Override
    public MentorDTO updateMentor(Long id, MentorDTO mentorDTO) {

        Mentor existingMentor = mentorSearchService.getMentorById(id);
        
        Mentor mentorToUpdate = mentorMapper.toEntity(mentorDTO);
        
        mentorToUpdate.setId(id);
        mentorToUpdate.setUser(existingMentor.getUser());
        
        Mentor updatedMentor = mentorRepository.save(mentorToUpdate);
        
        return mentorMapper.toDTO(updatedMentor);
    }
    @Override
    public Mentor updateMentor(Long id, UpdateMentorDTO dto) {
        Mentor mentor = mentorSearchService.getMentorById(id);

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
    @Override
    public void deleteById(Long id){
        if (!mentorRepository.existsById(id)) {
            throw new EntityNotFoundException(Mentor.class, id);
        }
        mentorRepository.deleteById(id);
    }


}