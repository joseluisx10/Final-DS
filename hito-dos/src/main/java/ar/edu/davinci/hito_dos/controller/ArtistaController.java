package ar.edu.davinci.hito_dos.controller;

import ar.edu.davinci.hito_dos.model.Artista;
import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Genero;
import ar.edu.davinci.hito_dos.model.Instrumento;
import ar.edu.davinci.hito_dos.service.ArtistaService;
import ar.edu.davinci.hito_dos.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;


    @PostMapping("/crear")
    public ResponseEntity<Artista> crear(@RequestParam List<Long> discoIds, @RequestBody Artista artista) {
        Artista artistaNuevo = artistaService.crearArtista(discoIds, artista);
        return new ResponseEntity<>(artistaNuevo, HttpStatus.CREATED);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Artista>> getArtistaPorId(@PathVariable int id) {
        Optional<Artista> artista = artistaService.getArtistaPorId(id);
        return new ResponseEntity<>(artista, HttpStatus.OK);
    }
    //Retornar los Artistas que implementan un genero especifico
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Optional<Artista>>> getArtistasPorGenero(@PathVariable Genero genero) {
        List<Optional<Artista>> artistas = artistaService.getArtistasPorGenero(genero);
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/nacionalidad/{nacionalidad}")
    public ResponseEntity<List<Optional<Artista>>> getArtistasPorNacionalidad(@PathVariable String nacionalidad) {
        List<Optional<Artista>> artistas = artistaService.getArtistasPorNacionalidad(nacionalidad);
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }
    //http://localhost:8080/artistas/instrumento?instrumentos=VIOLIN&instrumentos=BAJO
    @GetMapping("/instrumento")
    public ResponseEntity<List<Optional<Artista>>> getArtistasPorInstrumentos(@RequestParam List<Instrumento> instrumentos) {
        List<Optional<Artista>> artistas = artistaService.getArtistasPorInstrumentos(instrumentos);
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/edad/{edad}")
    public ResponseEntity<List<Optional<Artista>>> getArtistasPorEdad(@PathVariable int edad) {
        List<Optional<Artista>> artistas = artistaService.getArtistasPorEdad(edad);
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/cantidad-canciones/{cantidad}")
    public ResponseEntity<List<Optional<Artista>>> getArtistasPorCantidadDeCanciones(@PathVariable int cantidad) {
        List<Optional<Artista>> artistas = artistaService.getArtistasPorCantidadDeCanciones(cantidad);
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/vivos")
    public ResponseEntity<List<Optional<Artista>>> getArtistasVivos() {
        List<Optional<Artista>> artistas = artistaService.getArtistasVivos();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping("/fallecidos")
    public ResponseEntity<List<Optional<Artista>>> getArtistasFallecidos() {
        List<Optional<Artista>> artistas = artistaService.getArtistasFallecidos();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Artista>> getAllArtistas() {
        List<Artista> artistas = artistaService.getAllArtistas();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }
}
