package ar.edu.davinci.hito_dos.model;

public enum Genero {

    JAZZ("Jazz", 1L),
    BLUES("Blues", 2L),
    ROCK("Rock", 3L),
    ELECTRONICA("Electrónica", 4L),
    CLASICA("Clasica", 5L),
    TANGO("Tango", 6L);

    private String nombre;
    private Long id;
    private Genero(String nombre, Long id) {
        this.nombre = nombre;
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public Long getId(){
        return this.id;
    }
}
