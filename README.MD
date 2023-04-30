# SIGENU - EaD

## Acerca de

Es un módulo web para SIGENU, desarrollado en la Universidad Agraria de La Habana. Es utilizado para la gestión de los procesos de la secretaría docente correspondiente a la Educación a Distancia (EaD) de las universidades cubanas. Permite realizar un seguimiento desde que los estudiantes matriculan en la universidad hasta que culminan sus estudios por parte de las secretarías docentes. Incluye los procesos de matricular asignaturas, notas, baja, promoción, entre otros.

## Despliegue

SIGENU-EaD ha sido desarrollado con el siguiente entorno:

- Java JDK 18
- PostgreSQL 15
- JBoss Wildfly 27

Para compilar la aplicación se utiliza Apache Maven a través del comando en la terminal:

```shell
$ mvn clean package
```
A través de este comando se podrá obtener el fichero compilado `war` (web archive) y el proyecto compilado (web exploded). Con estos archivos será posible realizar el despliegue del proyecto.

## Configuración

Este proyecto requiere además de una base de datos. Para ello se de debe utilizar PostgreSQL 15. Debe existir una base de datos con el nombre `SIGENU-EaD`. Para una configuración diferente deben modificarse los ficheros `/src/main/webapp/WEB-INF/applicationContext.xml` que contiene la configuración de autenticación y el fichero  `/src/main/resources/META-INF/persistence.xml` que contiene la configuración de acceso a la base de datos por parte de persistence.

## Flujo de actividades

![Diagrama de activiades SIGENU-EaD](docs\Imagen1.png)

**Imagen 1**: Descripción del flujo de actividades

## Ejemplos de interfaz

![Página de Autenticación](docs\Imagen2.jpg)
![Dashboard](docs\Imagen3.jpg)
![Perfil](docs\Imagen4.jpg)
![Nueva Matrícula](docs\Imagen5.jpg)
![Matrícula Responsable](docs\Imagen6.jpg)

