package ar.edu.davinci.hito_dos.controller;

import ar.edu.davinci.hito_dos.model.Disco;
import ar.edu.davinci.hito_dos.model.Genero;
import ar.edu.davinci.hito_dos.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/discos")
public class DiscoController {

    @Autowired
    private DiscoService discoService;

    @PostMapping("/crear")
    public ResponseEntity<Disco> crear(@RequestBody Disco disco) {
        Disco discoNuevo = discoService.crear(disco);
        return new ResponseEntity<>(discoNuevo, HttpStatus.CREATED);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Optional<Disco>> getDiscoPorNombre(@PathVariable String nombre) {
        Optional<Disco> disco = discoService.findByNombre(nombre);
        return new ResponseEntity<>(disco, HttpStatus.OK);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Optional<Disco>>> getArtistasPorGenero(@PathVariable Genero genero) {
        List<Optional<Disco>> discos = discoService.getDiscosPorGenero(genero);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping("/edad/{edad}")
    public ResponseEntity<Optional<Disco>> getArtistasPorEdad(@PathVariable Date fecha) {
        Optional<Disco> disco = discoService.getDiscoPorFecha(fecha);
        return new ResponseEntity<>(disco, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Disco>> getAllDiscos() {
        List<Disco> discos = discoService.getAllDiscos();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }



}
