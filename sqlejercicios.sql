DROP DATABASE IF EXISTS tienda;
CREATE DATABASE tienda CHARACTER SET utf8mb4;
USE tienda;

CREATE TABLE fabricante (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

CREATE TABLE producto (
  id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  precio DOUBLE NOT NULL,
  id_fabricante INT UNSIGNED NOT NULL,
  FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
);

INSERT INTO fabricante VALUES(1, 'Asus');
INSERT INTO fabricante VALUES(2, 'Lenovo');
INSERT INTO fabricante VALUES(3, 'Hewlett-Packard');
INSERT INTO fabricante VALUES(4, 'Samsung');
INSERT INTO fabricante VALUES(5, 'Seagate');
INSERT INTO fabricante VALUES(6, 'Crucial');
INSERT INTO fabricante VALUES(7, 'Gigabyte');
INSERT INTO fabricante VALUES(8, 'Huawei');
INSERT INTO fabricante VALUES(9, 'Xiaomi');

INSERT INTO producto VALUES(1, 'Disco duro SATA3 1TB', 86.99, 5);
INSERT INTO producto VALUES(2, 'Memoria RAM DDR4 8GB', 120, 6);
INSERT INTO producto VALUES(3, 'Disco SSD 1 TB', 150.99, 4);
INSERT INTO producto VALUES(4, 'GeForce GTX 1050Ti', 185, 7);
INSERT INTO producto VALUES(5, 'GeForce GTX 1080 Xtreme', 755, 6);
INSERT INTO producto VALUES(6, 'Monitor 24 LED Full HD', 202, 1);
INSERT INTO producto VALUES(7, 'Monitor 27 LED Full HD', 245.99, 1);
INSERT INTO producto VALUES(8, 'Portátil Yoga 520', 559, 2);
INSERT INTO producto VALUES(9, 'Portátil Ideapd 320', 444, 2);
INSERT INTO producto VALUES(10, 'Impresora HP Deskjet 3720', 59.99, 3);
INSERT INTO producto VALUES(11, 'Impresora HP Laserjet Pro M26nw', 180, 3);

-- 1. Lista el nombre de todos los productos que hay en la tabla producto.
SELECT nombre FROM producto;
-- 2. Lista los nombres y los precios de todos los productos de la tabla producto.
select nombre, precio FROM producto;
-- 3. Lista todas las columnas de la tabla producto.
SELECT* FROM producto;
-- 4. Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD).
SELECT nombre, round(precio, 2), round(precio*3.8,2), round(precio*4.10,2) from producto;
-- 5. Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD). Utiliza los siguientes alias para las columnas: nombre de producto, euros, dólares.
SELECT nombre, round(precio, 2) 'Soles', round(precio*3.8,2) 'Dolar', round(precio*4.10,2) 'Euro' from producto;
-- 6. Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a mayúscula.
select UPPER(nombre), round(precio, 2) 
from producto;
-- 7. Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a minúscula.
select LOWER(nombre), round(precio, 2)
FROM producto;
-- 8. Lista el nombre de todos los fabricantes en una columna, y en otra columna obtenga en mayúsculas los dos primeros caracteres del nombre del fabricante.
select nombre, UPPER(LEFT(nombre, 2)) from fabricante;
-- 9. Lista los nombres y los precios de todos los productos de la tabla producto, redondeando el valor del precio.
select nombre, round(precio,2) from producto;
-- 10. Lista los nombres y los precios de todos los productos de la tabla producto, truncando el valor del precio para mostrarlo sin ninguna cifra decimal.
select nombre, round(precio,0) from producto;
-- 11. Lista el identificador de los fabricantes que tienen productos en la tabla producto.
SELECT f.* from producto p, fabricante f where p.id_fabricante = f.id;
-- 12. Lista el identificador de los fabricantes que tienen productos en la tabla producto, eliminando los identificadores que aparecen repetidos.
SELECT distinct f.* from producto p, fabricante f where p.id_fabricante = f.id;
-- 13. Lista los nombres de los fabricantes ordenados de forma ascendente.
select nombre from fabricante order by fabricante.id ASC;
-- 14. Lista los nombres de los fabricantes ordenados de forma descencente.
select nombre from fabricante order by fabricante.id desc;
-- 15. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
select nombre, precio from producto order by nombre ASC,precio DESC;


-- 1.1.6
-- 1. Calcula el número total de productos que hay en la tabla productos.
select count(*) from producto;
-- 2. Calcula el número total de fabricantes que hay en la tabla fabricante.
select count(*) from fabricante;
-- 3. Calcula el número de valores distintos de identificador de fabricante aparecen en la tabla productos.
select count(DISTINCT id_fabricante) FROM producto;
-- 4. Calcula la media del precio de todos los productos.
select round(avg(precio),2) from producto;
-- 5. Calcula el precio más barato de todos los productos
select min(precio) from producto;
-- 6. Calcula el precio más caro de todos los productos
select max(precio) from producto;
-- 7. Lista el nombre y el precio del producto más barato
select * from producto order by precio asc;
-- 8. Lista el nombre y el precio del producto más caro.
select * from producto order by precio desc;
-- 9. Calcula la suma de los precios de todos los productos.
select sum(precio) from producto;