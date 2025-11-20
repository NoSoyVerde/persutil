package net.ausiasmarch.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.persutil.entity.IdeaEntity;
import net.ausiasmarch.persutil.service.IdeaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/idea")
public class IdeaApi {

    @Autowired
    IdeaService oIdeaService;

    // ----------------------------CRUD---------------------------------

    // obtener idea por id
    @GetMapping("/{id}")
    public ResponseEntity<IdeaEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(oIdeaService.get(id));
    }

    // crear idea
    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody IdeaEntity ideaEntity) {
        return ResponseEntity.ok(oIdeaService.create(ideaEntity));
    }

    // modificar idea
    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody IdeaEntity ideaEntity) {
        return ResponseEntity.ok(oIdeaService.update(ideaEntity));
    }

    // borrar idea
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return ResponseEntity.ok(oIdeaService.delete(id));
    }

    // listado paginado de ideas
    @GetMapping("")
    public ResponseEntity<Page<IdeaEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oIdeaService.getPage(oPageable));
    }

    // contar ideas
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(oIdeaService.count()); 
    }

}
