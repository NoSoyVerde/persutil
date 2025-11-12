package net.ausiasmarch.es.persutil.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/generarvarios")
    public ResponseEntity<String> generarVarios() {
        try {
            // Generar varios blogs para probar la ordenación
            for (int i = 0; i < 5; i++) {
                oBlogService.rellenaBlog();
            }
            return ResponseEntity.ok("Se generaron 5 blogs aleatorios");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generando blogs");
        }
    }

    @GetMapping
    public ResponseEntity<Page<BlogEntity>> getAllBasic(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String contenido) {
        try {
            // Validar que el campo de ordenación existe para evitar errores
            String validSortBy = validateSortField(sortBy);
            
            Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sorting = Sort.by(direction, validSortBy);
            
            Pageable pageable = PageRequest.of(page, size, sorting);
            
            // Aplicar filtros según los parámetros proporcionados
            Page<BlogEntity> blogs;
            if (category != null && !category.isEmpty()) {
                blogs = oBlogService.getAllByCategory(category, pageable);
            } else if (titulo != null && !titulo.isEmpty()) {
                blogs = oBlogService.getAllByTitulo(titulo, pageable);
            } else if (contenido != null && !contenido.isEmpty()) {
                blogs = oBlogService.getAllByContenido(contenido, pageable);
            } else {
                blogs = oBlogService.getAll(pageable);
            }
            
            return ResponseEntity.ok(blogs);
        } catch (Exception e) {
            e.printStackTrace(); // Para debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String validateSortField(String sortBy) {
        // Lista de campos válidos para ordenar
        java.util.Set<String> validFields = java.util.Set.of(
            "id", "titulo", "contenido", "etiquetas", "fechaCreacion", "fechaModificacion"
        );
        
        if (validFields.contains(sortBy)) {
            return sortBy;
        } else {
            // Si el campo no es válido, usar 'id' por defecto
            return "id";
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<BlogEntity>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        try {
            String validSortBy = validateSortField(sortBy);
            Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, validSortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<BlogEntity> blogs = oBlogService.getAll(pageable);
            return ResponseEntity.ok(blogs);
        } catch (Exception e) {
            e.printStackTrace(); // Para debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<java.util.List<BlogEntity>> getAllWithoutPagination() {
        try {
            return ResponseEntity.ok(oBlogService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogEntity> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(oBlogService.get(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // crear posts
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody BlogEntity blog) {
        try {
            Long id = oBlogService.create(blog);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1L);
        }
    }

    // modificar posts
    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody BlogEntity blog) {
        try {
            blog.setId(id);
            Long updatedId = oBlogService.update(blog);
            return ResponseEntity.ok(updatedId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1L);
        }
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
