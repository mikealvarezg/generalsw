# generalsw

Se trata de una aplicación *big data* que gestiona los clientes de un banco.

## Resumen 

### 1. Aplicación *Java*

Este código es una aplicación *Java* que utiliza la librería *Apache Spark* para leer un archivo *CSV*, realiza transformaciones en los datos y los carga en una base de datos *MariaDB*.

Ademas, realiza varias consultas en la base de datos y muestra los resultados por consola.

El código realiza las siguientes acciones:

* Crea una sesión de *Spark* y lee el archivo "bank OB.csv" como un *Dataset* de *Spark*.
* Realiza transformaciones en los datos, convirtiendo las columnas de tipo entero.
* Escribe los datos transformados en la base de datos *MariaDB*, en la tabla "client".
* Lee los datos de *MariaDB* como un *Dataset* de *Spark*.
* Realiza varias consultas en los datos y muestra los resultados por consola.

### 2. Base de datos *MariaDB*

La base de datos simplemente contiene una tabla con la información relativa a los clientes del banco.

Se genera mediante *Docker* para poder ser desplegada desde cualquier máquina. 

### Pre-requisites 📋

Spark *3.3.2*

Java *20-jdk*

Docker

IntelliJ IDEA

## Deployment 📦

Ejecutar este comando desde el directorio principal **generalsw**

```
docker-compose up
```

Este comando crea un contenedor con la base de datos escuchando en el puerto *3306*

### Setup 🔧

1. Desde **generalsw** compilar con *Maven*:

```
mvn package
```

2. Ejecutar la aplicación con el perfil de *LocalSparkSubmit* desde *IntelliJ*:

```
Run 'LocalSparkSubmit'
```

*La base de datos debe haber sido desplegada previamente*

## Technology 🛠️

* Java

* Maven

* Spark

* MariaDB

* Docker

## Developer ✒️

* **Miguel Álvarez Granado** - [mikealvarezg](https://github.com/mikealvarezg)
