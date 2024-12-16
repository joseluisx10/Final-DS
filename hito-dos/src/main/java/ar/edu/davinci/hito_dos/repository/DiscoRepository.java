package ar.edu.davinci.hito_dos.repository;

import ar.edu.davinci.hito_dos.model.Artista;
import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Disco;
import ar.edu.davinci.hito_dos.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {

    @Query("SELECT d FROM Disco d WHERE LOWER(d.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) OR LOWER(d.nombre) = LOWER(:nombre)")
    Optional<Disco> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT d FROM Disco d JOIN d.generos g WHERE g = :genero")
    public List<Optional<Disco>> findByGenero(@Param("genero") Genero genero);

    @Query("SELECT d FROM Disco d WHERE d.fechaLanzamiento = :fecha")
    Optional<Disco> findByFecha(@Param("fecha") Date fecha);
}
