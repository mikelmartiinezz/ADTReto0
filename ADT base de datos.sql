DROP DATABASE gamedb;		

CREATE DATABASE gamedb;

USE gamedb;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    telefono VARCHAR(20),
    fechaAlta DATE NOT NULL,
    ruta VARCHAR(255)
);

CREATE TABLE desarrollador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais VARCHAR(100),
    añoFundacion INT
);

CREATE TABLE compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    cantidad INT NOT NULL,
    usuario_id INT NOT NULL,
    juego_id INT NOT NULL,

    CONSTRAINT fk_compra_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
);

INSERT INTO usuario (nombre, email, telefono, fechaAlta, ruta) VALUES
('Iker Bosquez', 'iker@gmail.com', '600123456', '2026-09-15', '/usuarios/iker'),
('Carlos García', 'carlos@gmail.com', '611234567', '2026-09-10', '/usuarios/carlos'),
('Laura López', 'laura@gmail.com', '622345678', '2026-09-05', '/usuarios/laura'),
('Mikel Martín', 'mikel@gmail.com', '633456789', '2026-09-01', '/usuarios/mikel'),
('Ane Rodríguez', 'ane@gmail.com', '644567890', '2026-08-28', '/usuarios/ane');

INSERT INTO desarrollador (nombre, pais, añoFundacion) VALUES
('Nintendo', 'Japón', 1889),
('Electronic Arts', 'Estados Unidos', 1982),
('Ubisoft', 'Francia', 1986),
('Rockstar Games', 'Estados Unidos', 1998),
('CD Projekt Red', 'Polonia', 2002);

INSERT INTO compra (fecha, cantidad, usuario_id, juego_id) VALUES
('2026-09-10', 1, 1, 1),
('2026-09-11', 2, 2, 3),
('2026-09-12', 1, 3, 2),
('2026-09-13', 3, 1, 4),
('2026-09-14', 1, 4, 5);