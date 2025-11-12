package net.ausiasmarch.es.persutil.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.es.persutil.entity.BlogEntity;
import net.ausiasmarch.es.persutil.repository.BlogRepository;



@Service
public class BlogService {

    @Autowired
    BlogRepository oBlogRepository;

    @Autowired
    AleatorioService oAleatorioService;

    ArrayList<String> alFrases = new ArrayList<>();

    public BlogService() {
        alFrases.add("La vida es bella.");
        alFrases.add("El conocimiento es poder.");
        alFrases.add("La perseverancia es la clave del éxito.");
        alFrases.add("El tiempo es oro.");
        alFrases.add("La creatividad es la inteligencia divirtiéndose.");
        alFrases.add("Más vale tarde que nunca.");
        alFrases.add("El cambio es la única constante en la vida.");
        alFrases.add("La esperanza es lo último que se pierde.");
        alFrases.add("La unión hace la fuerza.");
        alFrases.add("El respeto es la base de toda relación.");
        alFrases.add("La comunicación es clave en cualquier relación.");
        alFrases.add("Más vale pájaro en mano que ciento volando.");
        alFrases.add("A mal tiempo, buena cara.");
        alFrases.add("El que no arriesga no gana.");
        alFrases.add("La suerte favorece a los audaces.");
        alFrases.add("El tiempo lo dirá.");
    }

    public Long rellenaBlog() {
        BlogEntity oBlogEntity = new BlogEntity();
        oBlogEntity.setTitulo(alFrases.get(oAleatorioService.generarNumeroAleatorio(0, alFrases.size() - 1)));
        String contenidoGenerado = "";
        for (int i=1; i<=oAleatorioService.generarNumeroAleatorio(1, 30); i++) {
            contenidoGenerado += alFrases.get(oAleatorioService.generarNumeroAleatorio(0, alFrases.size() - 1)) + " ";
            if (oAleatorioService.generarNumeroAleatorio(0, 10) == 1) {
                contenidoGenerado += "\n";
            }
        }
        oBlogEntity.setContenido(contenidoGenerado);
        // extraer 5 palabras aleatorias del contenido para las etiquetas
            String[] palabrasContenido = contenidoGenerado.split(" ");
            ArrayList<String> palabrasUnicas = new ArrayList<>();
            for (String palabra : palabrasContenido) {
                palabra = palabra.replace(".", "").replace(",", "").toLowerCase();
                if (!palabrasUnicas.contains(palabra) && palabra.length() > 3) {
                    palabrasUnicas.add(palabra);
                }
            }
            Collections.shuffle(palabrasUnicas);
            StringBuilder etiquetasBuilder = new StringBuilder();
            for (int i = 0; i < Math.min(5, palabrasUnicas.size()); i++) {
                etiquetasBuilder.append(palabrasUnicas.get(i));
                if (i < Math.min(5, palabrasUnicas.size()) - 1) {
                    etiquetasBuilder.append(", ");
                }
            }
            oBlogEntity.setEtiquetas(etiquetasBuilder.toString());
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        oBlogRepository.save(oBlogEntity);
        return oBlogRepository.count();
    }

    public Page<BlogEntity> getAll(Pageable pageable) {
        return oBlogRepository.findAll(pageable);
    }

    public java.util.List<BlogEntity> getAll() {
        return oBlogRepository.findAll();
    }

    public Page<BlogEntity> getAllByCategory(String category, Pageable pageable) {
        // Buscar blogs que contengan la categoría en las etiquetas
        return oBlogRepository.findByEtiquetasContainingIgnoreCase(category, pageable);
    }

    public Page<BlogEntity> getAllByTitulo(String titulo, Pageable pageable) {
        return oBlogRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }

    public Page<BlogEntity> getAllByContenido(String contenido, Pageable pageable) {
        return oBlogRepository.findByContenidoContainingIgnoreCase(contenido, pageable);
    }

    public BlogEntity get(Long id) {
        return oBlogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Long create(BlogEntity oBlogEntity) {
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        oBlogRepository.save(oBlogEntity);
        return oBlogEntity.getId();
    }

    public Long update(BlogEntity oBlogEntity) {
        BlogEntity existingBlog = oBlogRepository.findById(oBlogEntity.getId())
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        existingBlog.setTitulo(oBlogEntity.getTitulo());
        existingBlog.setContenido(oBlogEntity.getContenido());
        existingBlog.setEtiquetas(oBlogEntity.getEtiquetas());
        existingBlog.setFechaModificacion(LocalDateTime.now());
        oBlogRepository.save(existingBlog);
        return oBlogEntity.getId();
    }

    public void delete(Long id) {
        BlogEntity existingBlog = oBlogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        oBlogRepository.delete(existingBlog);
    }

}