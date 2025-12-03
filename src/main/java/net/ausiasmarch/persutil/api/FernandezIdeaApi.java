package net.ausiasmarch.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.persutil.dto.PublicoDto;
import net.ausiasmarch.persutil.entity.FernandezIdeaEntity;
import net.ausiasmarch.persutil.service.FernandezIdeaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/idea")
public class FernandezIdeaApi {
    // bulk creation de ideas fake con CORS específico para Angular
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping("/bulk/{amount}")
    public ResponseEntity<Long> bulkCreate(@PathVariable int amount) {
        return ResponseEntity.ok(oIdeaService.bulkCreate((long) amount));
    }

    @Autowired
    FernandezIdeaService oIdeaService;

    // ----------------------------CRUD---------------------------------

    // obtener idea por id
    @GetMapping("/{id}")
    public ResponseEntity<FernandezIdeaEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(oIdeaService.get(id));
    }

    // crear idea
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody FernandezIdeaEntity ideaEntity) {
        return ResponseEntity.ok(oIdeaService.create(ideaEntity));
    }

    // modificar idea
    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody FernandezIdeaEntity ideaEntity) {
        return ResponseEntity.ok(oIdeaService.update(ideaEntity));
    }

    // borrar idea
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(oIdeaService.delete(id));
    }

    // listado paginado de ideas (acepta filtro publico)
    @GetMapping("")
    public ResponseEntity<Page<FernandezIdeaEntity>> getPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "publico", required = false) Boolean publico,
            @RequestParam(name = "sort", defaultValue = "fechaCreacion") String sort,
            @RequestParam(name = "direction", required = false) String direction) {
        // Support both styles:
        // - frontend sending separate params: sort=fechaCreacion&direction=desc
        // - frontend sending combined param as Spring usually does: sort=field,dir
        String sortField = sort;
        String dir = (direction == null) ? "desc" : direction;
        if (sort != null && sort.contains(",")) {
            String[] parts = sort.split(",");
            if (parts.length > 0) {
                sortField = parts[0];
            }
            if (parts.length > 1) {
                dir = parts[1];
            }
        }
        return ResponseEntity.ok(oIdeaService.getPageFiltered(page, size, publico, sortField, dir));
    }

    // contar ideas (opcionalmente filtrado por publico)
    @GetMapping("/count")
    public ResponseEntity<Long> count(@RequestParam(name = "publico", required = false) Boolean publico) {
        return ResponseEntity.ok(oIdeaService.countFiltered(publico));
    }

    // ----------------------------ENDPOINTS DE PUBLICACIÓN---------------------------------

    /**
     * Actualizar solo el campo publico de una idea (requiere autenticación)
     * POST /idea/{id}/publico con body: { "publico": true/false }
     */
    @PatchMapping("/{id}/publico")
    public ResponseEntity<?> setPublico(@PathVariable Long id, @RequestBody PublicoDto dto) {
        try {
            FernandezIdeaEntity idea = oIdeaService.setPublico(id, dto.getPublico());
            return ResponseEntity.ok(idea);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /**
     * Toggle del campo publico (invierte el valor actual)
     * PATCH /idea/{id}/toggle
     */
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> togglePublico(@PathVariable Long id) {
        try {
            FernandezIdeaEntity idea = oIdeaService.togglePublico(id);
            return ResponseEntity.ok(idea);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

}
