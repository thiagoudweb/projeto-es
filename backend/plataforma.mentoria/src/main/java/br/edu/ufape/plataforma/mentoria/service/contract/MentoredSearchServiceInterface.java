package br.edu.ufape.plataforma.mentoria.service.contract;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.enums.InterestArea;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

import java.util.List;

public interface MentoredSearchServiceInterface {
    public Mentored getMentoredById(Long id);
    public MentoredDTO getMentoredDetailsDTO(Long id);
    public MentoredDTO getCurrentMentored();
    public List<MentoredDTO> findByInterestArea(InterestArea interestArea);
}
