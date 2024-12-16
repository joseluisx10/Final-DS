package ar.edu.davinci.hito_dos.service;

import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Usuario;
import ar.edu.davinci.hito_dos.repository.CancionRepository;
import ar.edu.davinci.hito_dos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CancionRepository cancionRepository;

    public Usuario findById(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).get();
    }

    public List<Cancion> findAllCanciones() {
        return cancionRepository.findAll();
    }
}
