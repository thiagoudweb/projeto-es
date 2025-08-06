package br.edu.ufape.plataforma.mentoria.service.contract;

import br.edu.ufape.plataforma.mentoria.dto.MentorDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.model.Mentor;

import java.util.List;

public interface MentorSearchServiceInterface {

    public Mentor getMentorById(Long id);
    public MentorDTO getMentorDetailsDTO(Long id);
    public List<Mentor> getAllMentors();
    public MentorDTO getCurrentMentor();
    public List<MentorDTO> findByInterestAreaAndSpecializations(InterestArea interestArea, String specialization);
}
