package ar.edu.davinci.hito_dos.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
@Entity
@Table(name = "puntajes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Puntaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cancion_id", nullable = false)
    private Cancion cancion;

    private double valoracion;

    public Puntaje() {}

    public Puntaje(Usuario usuario, Cancion cancion, double valoracion) {
        this.usuario = usuario;
        this.cancion = cancion;
        this.valoracion = valoracion;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Cancion getCancion() { return cancion; }
    public double getValoracion() { return valoracion; }
    public void setValoracion(double valoracion) { this.valoracion = valoracion; }
}