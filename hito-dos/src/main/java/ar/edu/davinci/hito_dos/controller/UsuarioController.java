package ar.edu.davinci.hito_dos.controller;

import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Genero;
import ar.edu.davinci.hito_dos.model.Puntaje;
import ar.edu.davinci.hito_dos.model.Usuario;
import ar.edu.davinci.hito_dos.repository.UsuarioRepository;
import ar.edu.davinci.hito_dos.service.CancionService;
import ar.edu.davinci.hito_dos.service.UsuarioService;
import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CancionService cancionService;

    @Autowired
    private CancionService puntajeService;

    @PostMapping("/puntuar")
    public ResponseEntity<String> puntuar(@RequestParam Long usuarioId, @RequestParam Long cancionId, @RequestParam double valoracion) {
        try {
            // Llamar al servicio para registrar la puntuación
            puntajeService.puntuarCancion(usuarioId, cancionId, valoracion);
            return ResponseEntity.ok("Puntaje registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/puntuar-album")
    public ResponseEntity<String> puntuarAlbum(@RequestParam Long usuarioId, @RequestParam Long albumId, @RequestParam double valoracion) {
        try {
            // Llamar al servicio para registrar la puntuación
            puntajeService.puntuarAlbum(usuarioId, albumId, valoracion);
            return ResponseEntity.ok("Puntaje Album registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{usuarioId}/canciones-similares")
    public ResponseEntity<List<Cancion>> obtenerCancionesSimilares(@PathVariable Long usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (usuarioOpt.isPresent()) {
            List<Cancion> cancionesSimilares = cancionService.obtenerCancionesSimilares(usuarioOpt.get());
            return ResponseEntity.ok(cancionesSimilares);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
