1) CREAR DISCO
  POST http://localhost:8080/discos/crear
   {
    "nombre" : "Disco"
}
2) CREAR ARTISTAS
   POST http://localhost:8080/artistas/crear?discoIds=1, .....,
   {
    "nombre" : "Roxette",
    "generos" : [],
    "paisOrigen": "hnkjh",
    "fechaNacimiento": "1958-05-30",
    "bibliografia": "JBJKBJB"
}

3) CREAR CANCION
  POST http://localhost:8080/canciones/crear?artistaId=1
 {
"nombre": "Roxete",
"letra": "dsfdsd",
"genero": "CLASICA"
 }
4) AGREGAR PUNTAJE A UNA CANCION
  http://localhost:8080/usuarios/puntuar?usuarioId=1&cancionId=3&valoracion=6.5
5) FILTRAR POR GENERO FAVORITO
   http://localhost:8081/usuarios/1/canciones-similares



   
