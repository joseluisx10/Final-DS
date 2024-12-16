package ar.edu.davinci.hito_dos.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Puntaje> puntajes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PuntajeAlbum> puntajesAlbum = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Puntaje> getPuntajes() {
        return puntajes;
    }

    public Puntaje puntuarCancion(Cancion cancion, double valoracion) {
        Puntaje puntaje = new Puntaje(this, cancion, valoracion);
        this.puntajes.add(puntaje);
        cancion.addPuntaje(puntaje);
        return puntaje;
    }

    public PuntajeAlbum puntuarAlbum(Album album, double valoracion) {
        if (valoracion < 0 || valoracion > 5) {
            throw new RuntimeException("La valoración debe estar entre 0 y 5.");
        }
        PuntajeAlbum puntaje = new PuntajeAlbum(this, album, valoracion);
        this.puntajesAlbum.add(puntaje);
        album.addPuntaje(puntaje);
        return puntaje;
    }

}