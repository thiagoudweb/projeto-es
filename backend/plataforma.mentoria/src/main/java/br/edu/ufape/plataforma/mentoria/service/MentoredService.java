package br.edu.ufape.plataforma.mentoria.service;

import java.util.List;

import br.edu.ufape.plataforma.mentoria.model.Mentor;
import br.edu.ufape.plataforma.mentoria.model.User;
import br.edu.ufape.plataforma.mentoria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
        return mentoredMapper.toDto(mentored);
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
        return mentoredMapper.toDto(savedMentored);
    }

    public Mentored updateMentored(Long id, Mentored mentored) throws Exception {
        if (mentoredRepository.existsById(id)) {
            mentored.setId(id);
            return mentoredRepository.save(mentored);
        }
        throw new EntityNotFoundException(Mentored.class, id);
    }

    public MentoredDTO updateMentored(Long id, MentoredDTO mentoredDTO) throws Exception {
        if (mentoredRepository.existsById(id)) {
            Mentored mentored = mentoredMapper.toEntity(mentoredDTO);
            mentored.setId(id);
            User user = userRepository.findById(mentored.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + mentored.getUser().getId()));
            mentored.setUser(user);
            Mentored updatedMentored = mentoredRepository.save(mentored);
            return mentoredMapper.toDto(updatedMentored);
        }
        throw new EntityNotFoundException(Mentor.class, id);
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
                .map(mentoredMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(Mentored.class, email));
    }

    public List<Mentored> searchMentoredByInterest(String interestName) {
        return mentoredRepository.findByInterestAreaContaining(interestName);
    }
}
