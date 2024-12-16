package ar.edu.davinci.hito_dos.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "canciones")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String letra;


    //private String genero;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disco_id")
    private Disco disco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(mappedBy = "cancion", cascade = CascadeType.ALL)
    private List<Puntaje> puntajes = new ArrayList<>();


    public Cancion() {}

    public Cancion(String nombre, String letra, Genero genero) {
        this.nombre = nombre;
        this.letra = letra;
        this.genero = genero;
    }

    // Getters y Setters
    // public void setGenero(String genero) {this.genero = genero;}


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

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }


    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    /*public String getGenero() {
        return genero;
    }*/

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void addPuntaje(Puntaje puntaje) {
        this.puntajes.add(puntaje);
    }
}
