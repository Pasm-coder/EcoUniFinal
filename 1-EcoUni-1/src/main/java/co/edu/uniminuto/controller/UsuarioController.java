package co.edu.uniminuto.controller;

import javax.validation.Valid;

import co.edu.uniminuto.entity.Usuario;
import co.edu.uniminuto.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    // Regex
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    @GetMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                 consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> crear(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        if (!Pattern.matches(EMAIL_REGEX, usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Email no válido");
        }
        
        if (!Pattern.matches(PASSWORD_REGEX, usuario.getPassword())) {
            return ResponseEntity.badRequest().body("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número.");
        }

        usuarioService.guardar(usuario);
        return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizar(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Datos inválidos");
        }

        if (!Pattern.matches(EMAIL_REGEX, usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Email no válido");
        }

        if (!Pattern.matches(PASSWORD_REGEX, usuario.getPassword())) {
            return ResponseEntity.badRequest().body("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número.");
        }

        usuario.setId(id); 
        usuarioService.actualizar(id, usuario);
        return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        usuarioService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}