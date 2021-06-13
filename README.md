# Quasar Challenge

<b>Proyecto:</b> Quasar
Tecnologías usadas: Java 8 con Spring Boot - Redis 3.2 - Host: AWS - Postman

Para levantar el proyecto, es necesario iniciar la instancia de Redis de la siguiente manera:

1- Situarse en la carpeta redis, la ubicación de la misma es: "src\main\resources\redis"
<br>
2- Una vez situado en esa carpeta, para iniciar la instancia se debe colocar redis-server.exe
<br>
3- Inicializará la instancia
<br>

Una vez levantado Redis, iremos hasta nuestro Application llamado QuasarApplication ("src\main\java\QuasarApplication.java"), click derecho y colocamos 'Run QuasarApplication'

Levantará la aplicación.

<b>Pruebas para nivel 1</b>:

En Postman, crear una nueva request

Método POST

Url: http://localhost:8080/topsecret/

Header: Content-Type - application/json

Body: 
{
   "satellites":[
      {
         "name":"kenobi",
         "distance":100.0,
         "message":[
            "este",
            "",
            "",
            "mensaje",
            ""
         ]
      },
      {
         "name":"skywalker",
         "distance":115.5,
         "message":[
            "",
            "es",
            "",
            "",
            "secreto"
         ]
      },
      {
         "name":"sato",
         "distance":142.7,
         "message":[
            "este",
            "",
            "un",
            "",
            ""
         ]
      }
   ]
}

Debería responder la posición de los satélites y el mensaje correspondiente.


<b>Para el nivel 2:</b>

Host AWS

Para iniciar la instancia de AWS se debe situar en: "src\main\resources\aws"

Una vez situado en esa dirección, enviar el siguiente comando: ssh -i "quasar-aws.pem" ubuntu@ec2-52-3-246-162.compute-1.amazonaws.com

y luego para levantar el proyecto enviar este comando: java -jar quasar-0.0.1-SNAPSHOT.jar

Esto levantará una instancia en AWS con el endpoint correspondiente al nivel 1.

Para probar, solo es necesario cambiar la url a: 52.3.246.162:8080/topsecret/ y seguir los pasos del nivel 1.

<b>Para el nivel 3:</b>

Ahora podemos recibir los post individuales. 

<b>Pruebas:</b>

Primero deberíamos chequear que no hayan satelites creados en nuestra instancia.

Método GET

Url: http://localhost:8080/topsecret/topsecret_split/

Si hay satelites creados y necesitamos eliminarlos podemos usar el siguiente método:

Método DELETE

Url: http://localhost:8080/topsecret/topsecret_split/{nombre_satelite}

Si deseamos crear un nuevo satélite utilizamos el siguiente método:

Método POST

Url: http://localhost:8080/topsecret/topsecret_split/{nombre_satelite}

Body: 
  Ejemplo: 

  {
  "distance": 142.7,
  "message": ["este", "", "un", "", ""]
  }

La creación de satélites tiene las siguientes validaciones:

  No se pueden crear más de 3 satelites
  
  Si en la instancia hay cargado menos de 3 satélites, no se puede obtener la posición por falta de datos
  
  No pueden haber satélites con el mismo nombre
  
Una vez creados los 3 satélites podemos obtener la posición de los mismos:

Método GET

Url: http://localhost:8080/topsecret/topsecret_split/getPosition

<b>Nota:</b> Para obtener la posición de los satélites utilicé la función Trilateration

Con esto finaliza el ejercicio propuesto.

Mercado Libre

Quasar Challenge

Desarrollador jr - Franco Germán Diaz
