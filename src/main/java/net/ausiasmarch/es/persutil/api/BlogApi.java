package net.ausiasmarch.es.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.es.persutil.entity.BlogEntity;
import net.ausiasmarch.es.persutil.service.AleatorioService;
import net.ausiasmarch.es.persutil.service.BlogService;


@RestController
@RequestMapping("/blog")
public class BlogApi {

    @Autowired
    AleatorioService aleatorioService;

    @Autowired
    BlogService oBlogService;


    @GetMapping("/saludar")
    public ResponseEntity<String> saludar() {
        return new ResponseEntity<>("¡Hola desde la API de Blog!", HttpStatus.OK);

    }

    @GetMapping("/saludar/{nombre}")
    public ResponseEntity<String> saludarNombre(@PathVariable String nombre) {
        return new ResponseEntity<>("¡Hola " + nombre + " desde la API de Blog!", HttpStatus.OK);
    }

    @GetMapping("/saludar/buenosdias")
    public ResponseEntity<String> saludarBuenosDias() {
        return new ResponseEntity<>("¡Buenos días desde la API de Blog!", HttpStatus.OK);
    }

    @GetMapping("/aleatorio")
    public ResponseEntity<Integer> aleatorio() {
        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        return new ResponseEntity<>(numeroAleatorio, HttpStatus.OK);
    }

    @GetMapping("/aleatorio/service/{min}/{max}")
    public ResponseEntity<Integer> aleatorioEnRango(@PathVariable int min, @PathVariable int max) {
        int numeroAleatorio = aleatorioService.generarNumeroAleatorio(min, max);
        return new ResponseEntity<>(numeroAleatorio, HttpStatus.OK);
    }

    @GetMapping("/rellenauno")
    public ResponseEntity<Long> rellenaUno() {
        return ResponseEntity.ok(aleatorioService.rellenaBlog());
    }

    @GetMapping("/frase")
    public ResponseEntity<Long> crearFraseAleatoria() {
        try {
            Long id = aleatorioService.crearBlogConFraseAleatoria();
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L);
        }
    }

    @GetMapping("/frasealeatoria")
    public ResponseEntity<Long> frasealeatoria() {
        try {
            Long id = oBlogService.rellenaBlog();
            return ResponseEntity.ok(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(oBlogService.get(id));

    }

    // crear posts
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody BlogEntity blog) {
        return ResponseEntity.ok(oBlogService.create(blog));
    }

    // modificar posts
    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody BlogEntity blog) {
        blog.setId(id);
        return ResponseEntity.ok(oBlogService.update(blog));
    }

    // eliminar posts
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            oBlogService.delete(id);
            return ResponseEntity.ok("Blog eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog no encontrado");
        }
    }

}
