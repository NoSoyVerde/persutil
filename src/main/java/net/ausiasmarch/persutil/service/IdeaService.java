package net.ausiasmarch.persutil.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.persutil.entity.IdeaEntity;
import net.ausiasmarch.persutil.repository.IdeaRepository;

@Service
public class IdeaService {

    @Autowired
    IdeaRepository oIdeaRepository;

    // ----------------------------CRUD---------------------------------
    
    public IdeaEntity get(Long id) {
        return oIdeaRepository.findById(id).orElseThrow(() -> new RuntimeException("Idea not found"));
    }

    public Long create(IdeaEntity ideaEntity) {
        ideaEntity.setFechaCreacion(LocalDateTime.now());
        ideaEntity.setFechaModificacion(LocalDateTime.now());
        if (ideaEntity.getPublico() == null) {
            ideaEntity.setPublico(true);
        }
        oIdeaRepository.save(ideaEntity);
        return ideaEntity.getId();
    }

    public Long update(IdeaEntity ideaEntity) {
        IdeaEntity existingIdea = oIdeaRepository.findById(ideaEntity.getId())
                .orElseThrow(() -> new RuntimeException("Idea not found"));
        existingIdea.setTitulo(ideaEntity.getTitulo());
        existingIdea.setComentario(ideaEntity.getComentario());
        existingIdea.setCategoria(ideaEntity.getCategoria());
        existingIdea.setPublico(ideaEntity.getPublico());
        existingIdea.setFechaModificacion(LocalDateTime.now());
        oIdeaRepository.save(existingIdea);
        return existingIdea.getId();
    }

    public Long delete(Long id) {
        oIdeaRepository.deleteById(id);
        return id;
    }

    public Page<IdeaEntity> getPage(Pageable oPageable) {
        return oIdeaRepository.findAll(oPageable);
    }

    public Long count() {
        return oIdeaRepository.count();
    }

}
