package net.ausiasmarch.persutil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.persutil.entity.FernandezIdeaEntity;
import net.ausiasmarch.persutil.repository.FernandezIdeaRepository;

@Service
public class FernandezIdeaService {
    // bulk creation de ideas fake
    public Long bulkCreate(Long amount) {
        String[] titulos = {"Idea brillante", "Mejora urgente", "Bug misterioso", "Nueva funcionalidad", "Optimización"};
        String[] comentarios = {"Comentario de prueba", "Descripción extensa", "Texto aleatorio", "Pendiente de revisión", "Importante"};
        net.ausiasmarch.persutil.entity.CategoriaEnum[] categorias = net.ausiasmarch.persutil.entity.CategoriaEnum.values();
        for (long i = 0; i < amount; i++) {
            FernandezIdeaEntity idea = new FernandezIdeaEntity();
            idea.setTitulo(titulos[(int)(Math.random()*titulos.length)] + " " + i);
            idea.setComentario(comentarios[(int)(Math.random()*comentarios.length)] + " " + i);
            idea.setCategoria(categorias[(int)(Math.random()*categorias.length)]);
            idea.setPublico(Math.random() > 0.5);
            // @PrePersist automáticamente establece fechas
            oIdeaRepository.save(idea);
        }
        return oIdeaRepository.count();
    }

    @Autowired
    FernandezIdeaRepository oIdeaRepository;

    // ----------------------------CRUD---------------------------------
    
    public FernandezIdeaEntity get(Long id) {
        return oIdeaRepository.findById(id).orElseThrow(() -> new RuntimeException("Idea not found"));
    }

    public Long create(FernandezIdeaEntity ideaEntity) {
        // @PrePersist automáticamente establece fechas
        if (ideaEntity.getPublico() == null) {
            ideaEntity.setPublico(true);
        }
        oIdeaRepository.save(ideaEntity);
        return ideaEntity.getId();
    }

    public Long update(FernandezIdeaEntity ideaEntity) {
        FernandezIdeaEntity existingIdea = oIdeaRepository.findById(ideaEntity.getId())
                .orElseThrow(() -> new RuntimeException("Idea not found"));
        existingIdea.setTitulo(ideaEntity.getTitulo());
        existingIdea.setComentario(ideaEntity.getComentario());
        existingIdea.setCategoria(ideaEntity.getCategoria());
        existingIdea.setPublico(ideaEntity.getPublico());
        // @PreUpdate automáticamente actualiza fechaModificacion
        oIdeaRepository.save(existingIdea);
        return existingIdea.getId();
    }

    public Long delete(Long id) {
        oIdeaRepository.deleteById(id);
        return id;
    }

    public Page<FernandezIdeaEntity> getPage(Pageable oPageable) {
        return oIdeaRepository.findAll(oPageable);
    }

    // paginacion filtrada por 'publico' (filtra en BD antes de paginar)
    public org.springframework.data.domain.Page<FernandezIdeaEntity> getPageFiltered(int page, int size, Boolean publico, String sort, String direction) {
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.fromString(direction), sort));
        if (publico != null) {
            return oIdeaRepository.findByPublico(publico, pageable);
        } else {
            return oIdeaRepository.findAll(pageable);
        }
    }

    // contar según filtro publico (si publico == null devuelve count total)
    public long countFiltered(Boolean publico) {
        if (publico != null) {
            return oIdeaRepository.countByPublico(publico);
        } else {
            return oIdeaRepository.count();
        }
    }

    public Long count() {
        return oIdeaRepository.count();
    }

    // ----------------------------PUBLICACIÓN---------------------------------

    /**
     * Actualiza solo el campo publico de una idea
     */
    public FernandezIdeaEntity setPublico(Long id, Boolean publico) {
        FernandezIdeaEntity idea = oIdeaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idea no encontrada con id: " + id));
        idea.setPublico(publico);
        // @PreUpdate automáticamente actualiza fechaModificacion
        return oIdeaRepository.save(idea);
    }

    /**
     * Invierte el valor del campo publico (toggle)
     */
    public FernandezIdeaEntity togglePublico(Long id) {
        FernandezIdeaEntity idea = oIdeaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idea no encontrada con id: " + id));
        idea.setPublico(!idea.getPublico());
        // @PreUpdate automáticamente actualiza fechaModificacion
        return oIdeaRepository.save(idea);
    }

}
