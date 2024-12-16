package ar.edu.davinci.hito_dos.repository;

import ar.edu.davinci.hito_dos.model.Artista;
import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    //@Query("SELECT c FROM Cancion c WHERE c.nombre LIKE %:nombre% or c.nombre = :nombre")
    @Query("SELECT c FROM Cancion c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) OR LOWER(c.nombre) = LOWER(:nombre)")
    public Optional<Cancion> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Cancion c WHERE LOWER(c.letra) LIKE LOWER(CONCAT('%', :letra, '%')) OR LOWER(c.letra) = LOWER(:letra)")
    public Optional<Cancion> findByLetra(@Param("letra") String letra);

    @Query("SELECT c FROM Cancion c WHERE c.genero IN :generos")
    List<Cancion> findByGeneros(@Param("generos") List<Genero> generos);

}

