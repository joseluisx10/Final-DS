package ar.edu.davinci.hito_dos.controller;

import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Genero;
import ar.edu.davinci.hito_dos.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/canciones")
public class CancionController {
    @Autowired
    private CancionService cancionService;


    //CREAR NUEVA CANCION
    //http://localhost:8080/canciones/crear
    @PostMapping("/crear")
    public ResponseEntity<Cancion> crearCancion(@RequestParam Long artistaId, @RequestBody Map<String, Object> requestBody) {
        String nombre = requestBody.get("nombre").toString();
        String letra = requestBody.get("letra").toString();
        String generoStr = requestBody.get("genero").toString();
        Genero genero;
        try {
            genero = Genero.valueOf(generoStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // Crear la nueva canción usando el servicio
        Cancion nuevaCancion = cancionService.crear(nombre, letra, genero, artistaId);
        return new ResponseEntity<>(nuevaCancion, HttpStatus.CREATED);
    }
    //MODIFICAR UNA CANCION POR SU ID
    //http://localhost:8080/canciones/modificar/1
    @PutMapping("/modificar/{id}")
    public  ResponseEntity<Cancion> modificarCancion(@RequestBody Map<String, Object> requestBody, @PathVariable Long id) {
        String nombre = requestBody.get("nombre").toString();
        String letra = requestBody.get("letra").toString();
        String genero = requestBody.get("genero").toString().toUpperCase();
        if (!isValidoGenero(genero)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Genero generoEnum = Genero.valueOf(genero);
        return new ResponseEntity<>(this.cancionService.actualizarCancion(id, nombre, letra, generoEnum),  HttpStatus.OK);
    }
    //BUSCAR UNA CANCION POR NOMBRE
    //http://localhost:8080/canciones/nombre/..
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Optional<Cancion>> getCancionPorNombre(@PathVariable String nombre) {
        return this.isPresentCancion(this.cancionService.getCancionPorNombre(nombre));
    }
    //BUSCAR UNA CANCION POR ID
    //http://localhost:8080/canciones/id/..
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Cancion>> getCancionPorId(@PathVariable int id){
        return this.isPresentCancion(this.cancionService.getCancionPorId(id));
    }
    //BUSCAR UNA CANCION POR LETRA
    //http://localhost:8080/canciones/letra/..
    @GetMapping("/letra/{letra}")
    public ResponseEntity<Optional<Cancion>> getCancionByLetra(@PathVariable String letra) {
        return this.isPresentCancion(this.cancionService.getCancionPorLetra(letra));
    }

    private ResponseEntity<Optional<Cancion>> isPresentCancion(Optional<Cancion> cancion) {
        if (!cancion.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cancion, HttpStatus.OK);
    }

    private boolean isValidoGenero(String genero) {
        try {
            Genero.valueOf(genero);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
