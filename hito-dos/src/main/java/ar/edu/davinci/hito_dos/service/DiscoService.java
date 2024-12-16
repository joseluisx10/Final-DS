package ar.edu.davinci.hito_dos.service;

import ar.edu.davinci.hito_dos.model.Disco;
import ar.edu.davinci.hito_dos.model.Genero;
import ar.edu.davinci.hito_dos.repository.DiscoRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DiscoService {
    private final DiscoRepository discoRepository;

    public DiscoService(DiscoRepository discoRepository) {
        this.discoRepository = discoRepository;
    }

    public Disco crear(Disco disco) {
        discoRepository.save(disco);
        return disco;
    }

    public List<Optional<Disco>> getDiscosPorGenero(Genero genero) {
        List<Optional<Disco>> disco = discoRepository.findByGenero(genero);
        return null;
    }

    public Optional<Disco> getDiscoPorFecha(Date fecha) {
        Optional<Disco> disco = discoRepository.findByFecha(fecha);
        return null;
    }

    public List<Disco> getAllDiscos() {
        return discoRepository.findAll();
    }

    public Optional<Disco> findByNombre(String nombre) {
        Optional<Disco> disco = discoRepository.findByNombre(nombre);
        return disco;
    }

}
