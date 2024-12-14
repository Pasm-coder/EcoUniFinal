package co.edu.uniminuto.controller;

import co.edu.uniminuto.entity.PuntosReciclaje;
import co.edu.uniminuto.service.IPuntosReciclajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/puntos-reciclaje")
public class PuntosReciclajeController {

    @Autowired
    private IPuntosReciclajeService puntosReciclajeService;

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<PuntosReciclaje> obtenerTodos() {
        return puntosReciclajeService.obtenerTodos();
    }

    @GetMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PuntosReciclaje> obtenerPorId(@PathVariable("id") Long id) {
        PuntosReciclaje punto = puntosReciclajeService.obtenerPorId(id);
        if (punto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(punto, HttpStatus.OK);
    }

    @PostMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                 consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> crear(@Valid @RequestBody PuntosReciclaje punto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }
        
        puntosReciclajeService.guardar(punto);
        return new ResponseEntity<>("Punto de reciclaje creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizar(@PathVariable("id") Long id, @Valid @RequestBody PuntosReciclaje punto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        punto.setId(id);
        puntosReciclajeService.actualizar(id, punto);
        return new ResponseEntity<>("Punto de reciclaje actualizado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        puntosReciclajeService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}