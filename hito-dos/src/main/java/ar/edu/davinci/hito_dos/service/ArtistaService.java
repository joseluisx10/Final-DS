package ar.edu.davinci.hito_dos.service;

import ar.edu.davinci.hito_dos.model.*;
import ar.edu.davinci.hito_dos.repository.ArtistaRepository;
import ar.edu.davinci.hito_dos.repository.CancionRepository;
import ar.edu.davinci.hito_dos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private DiscoRepository discoRepository;



    public Artista crearArtista(List<Long> discoIds, Artista artista) {
        artista = artistaRepository.save(artista);
        List<Disco> discos = discoRepository.findAllById(discoIds);
        for (Disco disco : discos) {
            disco = artista.addDisco(disco);
            discoRepository.save(disco);
        }
        return artistaRepository.save(artista);
    }

    public List<Artista> getAllArtistas() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> getArtistaPorId(long id) {
        return artistaRepository.findById(id);
    }

    public List<Optional<Artista>> getArtistasPorGenero(Genero genero) {
        return artistaRepository.findByGenero(genero);
    }

    public List<Optional<Artista>> getArtistasPorNacionalidad(String nacionalidad) {
        return artistaRepository.findByNacionalidad(nacionalidad);
    }

    public List<Optional<Artista>> getArtistasPorInstrumentos(List<Instrumento> instrumentos) {
        return  artistaRepository.findByIntrumetos(instrumentos);
    }

    public List<Optional<Artista>> getArtistasPorEdad(int edad) {
        return artistaRepository.findByEdad(edad);
    }

    public List<Optional<Artista>> getArtistasVivos() {
        return artistaRepository.findByFechaFallecimientoIsNull();
    }

    public List<Optional<Artista>> getArtistasFallecidos() {
        return artistaRepository.findByFechaFallecimientoIsNotNull();
    }

    public List<Optional<Artista>> getArtistasPorCantidadDeCanciones(int cantidad) {
        return  artistaRepository.findByCantidadCanciones(cantidad);
    }
}
