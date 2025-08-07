package br.edu.ufape.plataforma.mentoria.service.contract;

import br.edu.ufape.plataforma.mentoria.dto.MentoredDTO;
import br.edu.ufape.plataforma.mentoria.dto.UpdateMentoredDTO;
import br.edu.ufape.plataforma.mentoria.model.Mentored;

import java.util.List;

public interface MentoredServiceInterface {

    public List<Mentored> getAllMentored();
    public MentoredDTO createMentored(MentoredDTO mentoredDTO);
    public Mentored updateMentored(Long id, Mentored mentored);
    public MentoredDTO updateMentored(Long id, MentoredDTO mentoredDTO);
    public Mentored updateMentored(Long id, UpdateMentoredDTO dto);
    public void deleteById(Long id);
}
