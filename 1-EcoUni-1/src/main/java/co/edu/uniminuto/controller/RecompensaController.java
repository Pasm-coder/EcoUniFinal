package co.edu.uniminuto.controller;

import javax.validation.Valid;

import co.edu.uniminuto.entity.Recompensa;
import co.edu.uniminuto.service.IRecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/recompensas")
public class RecompensaController {

    @Autowired
    private IRecompensaService recompensaService;

    // Regex
    private static final int DESCRIPCION_MAX_LENGTH = 200; // 
    private static final int PUNTOS_MIN = 0; // 

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<Recompensa> obtenerTodas() {
        return recompensaService.obtenerTodas();
    }

    @GetMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recompensa> obtenerPorId(@PathVariable("id") Long id) {
        Recompensa recompensa = recompensaService.obtenerPorId(id);
        if (recompensa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recompensa, HttpStatus.OK);
    }

    @PostMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                 consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> crear(@Valid @RequestBody Recompensa recompensa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        String error = validarCampos(recompensa);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        recompensaService.guardar(recompensa);
        return new ResponseEntity<>("Recompensa creada exitosamente", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizar(@PathVariable("id") Long id, @Valid @RequestBody Recompensa recompensa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        String error = validarCampos(recompensa);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        recompensa.setId(id); 
        recompensaService.actualizar(id, recompensa);
        return new ResponseEntity<>("Recompensa actualizada exitosamente", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        recompensaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private String validarCampos(Recompensa recompensa) {

        if (recompensa.getDescripcion() != null && recompensa.getDescripcion().length() > DESCRIPCION_MAX_LENGTH) {
            return "La descripción no puede tener más de 200 caracteres.";
        }

        if (recompensa.getValorPuntos() < PUNTOS_MIN) {
            return "Los puntos deben ser un valor positivo o cero.";
        }

        return null; 
    }
}