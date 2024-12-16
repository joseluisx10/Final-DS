package ar.edu.davinci.hito_dos.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="artistas")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ElementCollection(targetClass = Genero.class)
    @CollectionTable(name = "artista_generos", joinColumns = @JoinColumn(name = "artista_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private List<Genero> generos;

    private String paisOrigen;
    private Date fechaNacimiento;
    private Date fechaFallecimiento;

    @ElementCollection(targetClass = Instrumento.class)
    @CollectionTable(name = "artista_instrumentos", joinColumns = @JoinColumn(name = "artista_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "instrumento")
    private List<Instrumento> instrumentos;

    private String bibliografia;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    private List<Disco> discosGrabados;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cancion> canciones;

    public Artista() {
        this.canciones = new ArrayList<>();
        this.generos = new ArrayList<>();
        this.discosGrabados = new ArrayList<>();
    }

    public Artista(String nombre, String paisOrigen, Date fechaNacimiento, Date fechaFallecimiento, Instrumento instrumentos, String bibliografia) {
        this.nombre = nombre;

        this.paisOrigen = paisOrigen;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.instrumentos = new ArrayList<>();
        this.bibliografia = bibliografia;


    }

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

    public List<Genero> getGeneros() {
        return generos;
    }

    public void addGenero(Genero genero) {
        this.generos.add(genero);
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Instrumento> getInstrumentos() {
        return instrumentos;
    }

    public void addInstrumento(Instrumento instrumento) {
        this.instrumentos.add(instrumento);
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String biografia) {
        this.bibliografia = biografia;
    }

    public List<Disco> getDiscosGrabados() {
        return discosGrabados;
    }

    public void addDiscoGrabado(Disco discosGrabado) {
        this.discosGrabados.add(discosGrabado);
    }

    /*public Cancion addCancion(Cancion cancion) {
        cancion.setArtista(this);
        if(!this.generos.contains(cancion.getGenero())){
            this.generos.add(cancion.getGenero());
        }

        this.canciones.add(cancion);
        return cancion;
    }*/

    public Disco addDisco(Disco disco) {
        disco.setArtista(this);
        this.discosGrabados.add(disco);
        return disco;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public Cancion addCancion(Cancion nuevacancion) {
        nuevacancion.setArtista(this);
        if(!this.generos.contains(nuevacancion.getGenero())){
            this.generos.add(nuevacancion.getGenero());
        }
        this.canciones.add(nuevacancion);
        return nuevacancion;
    }
}
