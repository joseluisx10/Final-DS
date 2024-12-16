CREATE TABLE IF  NOT EXISTS artistas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    generos VARCHAR(255),
    pais_origen VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    fecha_fallecimineto DATE,
    instrumentos TEXT,
    bibliografia VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS discos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    fecha_lanzamiento DATE,
    generos TEXT,
    artista_id BIGINT,
    FOREIGN KEY (artista_id) REFERENCES artistas(id)
);

CREATE TABLE IF NOT EXISTS canciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    letra VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    disco_id BIGINT,
    FOREIGN KEY (disco_id) REFERENCES discos(id)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS puntajes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cancion_id BIGINT,
    valoracion DOUBLE NOT NULL,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (cancion_id) REFERENCES canciones(id)
);


INSERT INTO usuarios (nombre, email) VALUES ('Juan Perez', 'juan.perez@example.com');
INSERT INTO usuarios (nombre, email) VALUES ('Maria Garcia', 'maria.garcia@example.com');
INSERT INTO usuarios (nombre, email) VALUES ('Pedro Martinez', 'pedro.martinez@example.com');
INSERT INTO usuarios (nombre, email) VALUES ('Ana Lopez', 'ana.lopez@example.com');
INSERT INTO usuarios (nombre, email) VALUES ('Luis Rodriguez', 'luis.rodriguez@example.com');