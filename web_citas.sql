DROP DATABASE if EXISTS web_citas;
CREATE DATABASE web_citas;
USE web_citas;

CREATE TABLE paises (
	pais VARCHAR(50) NOT NULL,
	n_usuarios INT NOT NULL DEFAULT 0,
	ultima_actualizacion TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
	IsAvailable BOOL	NOT NULL DEFAULT TRUE,
	PRIMARY KEY (pais)
);

CREATE TABLE usuarios (
	nombre VARCHAR(50) NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	ciudad VARCHAR(50) NOT NULL,
	pais VARCHAR(50) NOT NULL,
	sexo ENUM('Masculino','Femenino') NOT NULL,
	pareja ENUM('Masculino','Femenino','Sin preferencia') NOT NULL DEFAULT 'Sin preferencia',
	email VARCHAR(50) NOT NULL,
	contrasena VARCHAR(50) NOT NULL,
	IsAvailable BOOL	NOT NULL DEFAULT TRUE,
	PRIMARY KEY (email),
	FOREIGN KEY (pais) REFERENCES paises(pais)
);

CREATE TABLE categorias (
	categoria VARCHAR(50) NOT NULL,
	descripcion VARCHAR(500),
	n_usuarios INT NOT NULL,
	PRIMARY KEY (categoria)
);

CREATE TABLE preferencias(
	email VARCHAR(50) NOT NULL,
	categoria VARCHAR(50) NOT NULL,
	actividad TIMESTAMP NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
	sincronizar_preferencias ENUM('Si','No') NOT NULL,
	PRIMARY KEY (email),
	FOREIGN KEY (categoria) REFERENCES categorias(categoria),
	FOREIGN KEY (email) REFERENCES usuarios(email)
);

CREATE TABLE centros (
	cp INT(5) NOT NULL,
	centro VARCHAR(50) NOT NULL,
	direccion VARCHAR(100) NOT NULL,
	ciudad VARCHAR(50) NOT NULL,
	pais VARCHAR(50) NOT NULL,
	web VARCHAR(50),
	IsAvailable BOOL	NOT NULL DEFAULT TRUE,
	PRIMARY KEY (centro),
	FOREIGN KEY (pais) REFERENCES paises(pais)
);

CREATE TABLE citas(
	cita_id INT NOT NULL AUTO_INCREMENT,
	fech_hora DATETIME NOT NULL,
	centro VARCHAR(50) NOT NULL,
	fracaso ENUM('Si','No','Pendiente') NOT NULL DEFAULT 'Pendiente',
	email1 VARCHAR(50) NOT NULL,
	email2 VARCHAR(50) NOT NULL,
	IsAvailable BOOL	NOT NULL DEFAULT TRUE,
	PRIMARY KEY (cita_id),
	FOREIGN KEY (centro) REFERENCES centros(centro),
	FOREIGN KEY (email1) REFERENCES usuarios(email),
	FOREIGN KEY (email2) REFERENCES usuarios(email)
);
/*INSERCION DE DATOS*/
INSERT INTO web_citas.paises (pais) VALUES
	('España'),
	('Francia'),
	('Reino Unido'),
	('Alemania'),
	('Países Bajos'),
	('Bélgica'),
	('Polonia'),
	('Rumania'),
	('Italia')
;

DELIMITER $$
CREATE TRIGGER web_citas.UsuarioPais 
AFTER INSERT ON web_citas.usuarios
	FOR EACH ROW
	BEGIN
	IF NEW.IsAvailable = TRUE THEN
		UPDATE paises SET n_usuarios=n_usuarios+1 WHERE paises.pais=NEW.pais;
	END IF;
END;

INSERT INTO web_citas.usuarios 
	(nombre,direccion,ciudad,pais,sexo,pareja,email,contrasena,IsAvailable) VALUES
		('admin','x','x','España','Masculino',DEFAULT,'admin@gmail.com','admin',FALSE),
		('asd asd','C/asd,8,12345','Madrid','España','Masculino','Femenino','asd@gmail.com','asd',DEFAULT),
		('Jorge Preda','C/Tolosa,1,12345','Madrid','España','Masculino','Femenino','jog@gmail.com','asd',DEFAULT),
		('David Álvarez','C/Tolosa,2,12345','Madrid','España','Masculino','Femenino','dav@gmail.com','asd',DEFAULT),
		('Gisela Benito','C/Tolosa,3,12345','Madrid','España','Femenino','Masculino','gis@gmail.com','asd',DEFAULT),
		('Alba Prados','C/Tolosa,4,12345','Madrid','España','Femenino','Masculino','alb@gmail.com','asd',DEFAULT)
;


INSERT INTO web_citas.centros 
	(cp,centro,direccion,ciudad,pais,web) VALUES
		(28012,'Restaurante DCorazon','Pl. Mayor, 30','Madrid','España','thefork.es'),
		(28004,'Restaurante Ático','C. del Marqués de Valdeiglesias, 1','Madrid','España','theprincipalmadridhotel.com'),
		(28001,'Restaurante La Bien Aparecida','C. de Jorge Juan, 8','Madrid','España','restaurantelabienaparecida.com'),
		(28013,'Restaurante Gloria Bendita','Calle de Santiago, 3','Madrid','España','thefork.es'),
		(28012,'Restaurante Arrabal','Pl. Mayor, 23','Madrid','España','opentable.es/thefork.es')
;


INSERT INTO web_citas.citas 
	(fech_hora,centro,email1,email2) VALUES
		('2022-06-21 17:00','Restaurante DCorazon','dav@gmail.com','gis@gmail.com')
;
