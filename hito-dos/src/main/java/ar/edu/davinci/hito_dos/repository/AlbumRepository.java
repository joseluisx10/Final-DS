package ar.edu.davinci.hito_dos.repository;

import ar.edu.davinci.hito_dos.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
