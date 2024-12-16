package ar.edu.davinci.hito_dos.repository;

import ar.edu.davinci.hito_dos.model.Puntaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntajeRepository extends JpaRepository<Puntaje, Long> {
}
