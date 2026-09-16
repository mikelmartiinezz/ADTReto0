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