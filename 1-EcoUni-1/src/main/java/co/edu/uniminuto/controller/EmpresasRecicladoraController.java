package co.edu.uniminuto.controller;

import co.edu.uniminuto.entity.EmpresasRecicladora;
import co.edu.uniminuto.service.IEmpresasRecicladoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("*")
@RequestMapping(value="api/EmpresasRecicladora")
public class EmpresasRecicladoraController {

    @Autowired
    private IEmpresasRecicladoraService empresasRecicladoraService;

    // Regex
    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
    private static final String TELEFONO_REGEX = "^\\+?57\\d{10}$";

    @GetMapping(value="EmpresasRecicladora", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE) //Persistencia de datos JPA
    public List<EmpresasRecicladora> obtenerTodas() {
        return empresasRecicladoraService.obtenerTodas();
    }

    @GetMapping(value="/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public EmpresasRecicladora obtenerPorId(@PathVariable("id") Long id) {
        return empresasRecicladoraService.obtenerPorId(id);
    }

    @PostMapping(value="EmpresasRecicladora", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> crear(@RequestBody EmpresasRecicladora empresa) {
        if (!Pattern.matches(EMAIL_REGEX, empresa.getEmailContacto())) {
            return ResponseEntity.badRequest().body("El email no tiene un formato válido.");
        }

        if (!Pattern.matches(TELEFONO_REGEX, empresa.getTelefono())) {
            return ResponseEntity.badRequest().body("El teléfono debe tener un formato válido.");
        }

        empresasRecicladoraService.guardar(empresa);
        return ResponseEntity.ok("Empresa creada exitosamente");
    }

    @PutMapping(value="/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizar(@PathVariable("id") Long id, @RequestBody EmpresasRecicladora empresa) {
        if (!Pattern.matches(EMAIL_REGEX, empresa.getEmailContacto())) {
            return ResponseEntity.badRequest().body("El email no tiene un formato válido.");
        }

        if (!Pattern.matches(TELEFONO_REGEX, empresa.getTelefono())) {
            return ResponseEntity.badRequest().body("El teléfono debe tener un formato válido.");
        }

        empresasRecicladoraService.actualizar(id, empresa);
        return ResponseEntity.ok("Empresa actualizada exitosamente");
    }

    @DeleteMapping(value="/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public void eliminar(@PathVariable("id") Long id) {
        empresasRecicladoraService.eliminar(id);
    }
}