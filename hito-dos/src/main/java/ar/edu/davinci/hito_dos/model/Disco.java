package ar.edu.davinci.hito_dos.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "discos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Disco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ElementCollection(targetClass = Genero.class)
    @CollectionTable(name = "disco_generos", joinColumns = @JoinColumn(name = "disco_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private List<Genero> generos;

    private Date fechaLanzamiento;

    @OneToMany(mappedBy = "disco", cascade = CascadeType.ALL)
    private List<Cancion> canciones;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artista_id")
    private Artista artista;


    /*@OneToMany(mappedBy = "disco", cascade = CascadeType.ALL)
    private List<Puntaje> puntajes = new ArrayList<>();
*/
    public Disco() {
    }

    public Disco(String nombre, Date fechaLanzamineto) {
        this.nombre = nombre;
        this.generos = new ArrayList<>();
        this.fechaLanzamiento = fechaLanzamineto;
        this.canciones = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void addGenero(Genero genero) {
        this.addGenero(genero);
    }

    public Date getFechaLanzamineto() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamineto(Date fechaLanzamineto) {
        this.fechaLanzamiento = fechaLanzamineto;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void addCancion(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }


}
