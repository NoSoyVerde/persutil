package net.ausiasmarch.es.persutil.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import net.ausiasmarch.es.persutil.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    
    // Buscar blogs que contengan una palabra en las etiquetas (ignora mayúsculas/minúsculas)
    Page<BlogEntity> findByEtiquetasContainingIgnoreCase(String etiqueta, Pageable pageable);
    
    // Buscar blogs por título que contenga una palabra
    Page<BlogEntity> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    
    // Buscar blogs por contenido que contenga una palabra
    Page<BlogEntity> findByContenidoContainingIgnoreCase(String contenido, Pageable pageable);
    
}
