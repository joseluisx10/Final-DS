package ar.edu.davinci.hito_dos.service;

import ar.edu.davinci.hito_dos.model.*;
import ar.edu.davinci.hito_dos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CancionService {
    @Autowired
    public CancionRepository cancionRepository;
    @Autowired
    public ArtistaRepository artistaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PuntajeRepository puntajeRepository;

    public Cancion crear(String nombre, String letra, Genero genero, Long artistaId) {
        Cancion nuevacancion = new Cancion(nombre, letra, genero);
        nuevacancion =cancionRepository.save(nuevacancion);
        Artista artista = artistaRepository.findById(artistaId).get();
        artista.addCancion(nuevacancion);
        artistaRepository.save(artista);
        return cancionRepository.save(nuevacancion);
    }

    public Optional<Cancion> getCancionPorNombre(String nombre) {
        return cancionRepository.findByNombre(nombre);
    }

    public Optional<Cancion> getCancionPorId(long id) {
        return cancionRepository.findById(id);
    }

    public Optional<Cancion> getCancionPorLetra(String letra) {
        return cancionRepository.findByLetra(letra);
    }

    public Cancion actualizarCancion(Long id, String nombre, String letra, Genero genero) {
        Cancion nuevacancion = new Cancion(nombre, letra, genero);
        nuevacancion.setId(id);
        cancionRepository.save(nuevacancion);
        return nuevacancion;
    }

    public List<Cancion> obtenerCancionesSimilares(Usuario usuario) {
        List<Genero> generosFavoritos = usuario.getPuntajes().stream()
                .filter(p -> {
                    System.out.println("Valoración: " + p.getValoracion() + " nombre: " + p.getCancion().getNombre());
                    return p.getValoracion() >= 3.5;
                })
                .peek(p -> System.out.println("Filtrado: " + p.getValoracion()))
                .map(p -> p.getCancion().getGenero())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Géneros favoritos filtrados: " + generosFavoritos);

        return cancionRepository.findByGeneros(generosFavoritos);
    }

    //Puntuacion de cancion
    public void puntuarCancion(Long usuarioId, Long cancionId, double valoracion) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Cancion cancion = cancionRepository.findById(cancionId).get();
        Puntaje p = usuario.puntuarCancion(cancion, valoracion);
        puntajeRepository.save(p);
    }

    public void puntuarAlbum(Long usuarioId, Long albumId, double valoracion) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Album album = albumRepository.findById(albumId).get();
        PuntajeAlbum pa = usuario.puntuarAlbum(album, valoracion);
        puntajeRepository.save(pa);
    }
}