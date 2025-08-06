package br.edu.ufape.plataforma.mentoria.service.contract;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.dto.UpdateMentorDTO;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

public interface MentorServiceInterface {

    public MentorDTO createMentor(MentorDTO mentorDTO);
    public Mentor updateMentor(Long id, Mentor mentor);
    public MentorDTO updateMentor(Long id, MentorDTO mentorDTO);
    public Mentor updateMentor(Long id, UpdateMentorDTO dto);
    public void deleteById(Long id);
}
