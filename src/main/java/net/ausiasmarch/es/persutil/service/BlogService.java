package net.ausiasmarch.es.persutil.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ausiasmarch.es.persutil.entity.BlogEntity;
import net.ausiasmarch.es.persutil.repository.BlogRepository;

@Service
public class BlogService {

    @Autowired
    BlogRepository oBlogRepository;

    @Autowired
    AleatorioService aleatorioService;

    public Long rellenaBlog() {
        BlogEntity oBlogEntity = new BlogEntity();
        oBlogEntity.setTitulo("Primer Post");
        oBlogEntity.setContenido("Este es el contenido de mi primer post en el blog.");
        oBlogEntity.setEtiquetas("etiqueta1, etiqueta2");
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        
        BlogEntity savedEntity = oBlogRepository.save(oBlogEntity);
        return savedEntity.getId();
    }

    public Long crearBlogConFraseAleatoria() {
        BlogEntity oBlogEntity = new BlogEntity();
        oBlogEntity.setTitulo(aleatorioService.generarTituloAleatorio());
        oBlogEntity.setContenido(aleatorioService.generarFraseAleatoria());
        oBlogEntity.setEtiquetas(aleatorioService.generarEtiquetasAleatorias());
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        
        BlogEntity savedEntity = oBlogRepository.save(oBlogEntity);
        return savedEntity.getId();
    }
}
