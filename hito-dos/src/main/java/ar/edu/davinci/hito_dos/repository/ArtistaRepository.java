package ar.edu.davinci.hito_dos.repository;

import ar.edu.davinci.hito_dos.model.Artista;
import ar.edu.davinci.hito_dos.model.Cancion;
import ar.edu.davinci.hito_dos.model.Genero;
import ar.edu.davinci.hito_dos.model.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    @Query("SELECT a FROM Artista a JOIN a.generos g WHERE g = :genero")
    public List<Optional<Artista>> findByGenero(@Param("genero") Genero genero);

    @Query("SELECT a FROM Artista a WHERE a.paisOrigen = :nacionalidad")
    List<Optional<Artista>> findByNacionalidad(@Param("nacionalidad") String nacionalidad);

    @Query("SELECT DISTINCT a FROM Artista a JOIN a.instrumentos i WHERE i IN :instrumentos")
    List<Optional<Artista>> findByIntrumetos(@Param("instrumentos")List<Instrumento> instrumentos);

    /*@Query("SELECT a FROM Artista a WHERE FUNCTION('DATEDIFF', CURRENT_DATE, a.fechaNacimiento) / 365 >= :edadMinima " +
            "AND FUNCTION('DATEDIFF', CURRENT_DATE, a.fechaNacimiento) / 365 <= :edadMaxima")
    List<Artista> findByEdadBetween(@Param("edadMinima") int edadMinima, @Param("edadMaxima") int edadMaxima);
**/
    @Query("SELECT a FROM Artista a WHERE TIMESTAMPDIFF(YEAR , a.fechaNacimiento, CURDATE()) = :edad")
    List<Optional<Artista>> findByEdad(@Param("edad") int edad);

    List<Optional<Artista>> findByFechaFallecimientoIsNull();

    List<Optional<Artista>> findByFechaFallecimientoIsNotNull();

    @Query("SELECT a FROM Artista a JOIN a.canciones c GROUP BY a HAVING COUNT(c) = :cantidad")
    List<Optional<Artista>> findByCantidadCanciones(@Param("cantidad") int cantidad);
}
