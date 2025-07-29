package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.exceptions.AttributeAlreadyInUseException;
import br.edu.ufape.plataforma.mentoria.exceptions.MentorNotFoundException;
import br.edu.ufape.plataforma.mentoria.mapper.MentorMapper;
import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.repository.MentorRepository;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorMapper mentorMapper;

    public Mentor getMentorById(Long id) throws Exception {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new MentorNotFoundException(id));
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

    public Mentor createMentor(MentorDTO mentorDTO) {
        Mentor mentor = mentorMapper.toEntity(mentorDTO);
        if (mentorRepository.existsByCpf(mentor.getCpf())) {
            throw new AttributeAlreadyInUseException("CPF", mentor.getCpf(), Mentor.class);
        }
        return mentorRepository.save(mentor);
    }

    public Mentor updateMentor(Long id, Mentor mentor) throws Exception {
        if (mentorRepository.existsById(id)) {
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }
        throw new MentorNotFoundException(id);
    }

    public Mentor updateMentor(Long id, MentorDTO mentorDTO) throws Exception {
        if (mentorRepository.existsById(id)) {
            Mentor mentor = mentorMapper.toEntity(mentorDTO);
            mentor.setId(id);
            return mentorRepository.save(mentor);
        }
        throw new MentorNotFoundException(id);
    }

    public void deleteById(Long id) throws Exception {
        if (!mentorRepository.existsById(id)) {
            throw new MentorNotFoundException(id);
        }
        mentorRepository.deleteById(id);
    }
}