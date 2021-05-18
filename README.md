Ejecución programa en local:
1. Clonar el código java
2. Ingresar en una base de datos oracle (sql developer) los siguientes datos:
  Nombre de conexión: Mutants
  Usuario: MAGNETO
  Contraseña: ADMIN123
  Nombre del host: database-dna.cnoexmoiktwb.us-east-2.rds.amazonaws.com
  Puerto: 1521
  SID: DATABASE
 3. Importar el código como un proyecto maven
 4. Si no se tiene el plugin de spring , por favor instalarlo dentro del proyecto de la siguiente manera:
     En eclipse marketplace, ubicado en help, buscar: spring (esto es para poder ejecutar el aplicativo) e instalarlo
 5. En eclipse, ubicar project y dirigirse al tab: "java build path", ahí seleccionar: add external jar y seleccionar el archivo ojdbc8.jar que se encuentra en el proyecto
 6. Para ejecutar el proyecto dirigirse a Run as y seleccionar la opción: Spring Boot App ( la cuál ya habíamos añadido previamente) 
 7. Una vez desplegado, consumir los servicios. Dirigirse a postman y escoger:
    POST: http://localhost:8080/mutant, escribir el json que se quiere registrar y enviar la petición 
    GET: http://localhost:8080/stats, enviar la petición.
    
 Ejecución programa en cloud:
 1. Desde la consola de comandos ejecutar el siguiente comando para entrar a la nube
	  ssh -i "lauracamelo-aws.pem" ubuntu@ec2-13-58-102-127.us-east-2.compute.amazonaws.com
  1.1. Estando allí ejecutar el comando java -jar Mutant-0.0.1-SNAPSHOT.jar para ejecutar el archivo en la máquina
 2. Para consumir los servicios dirigirse a postman y escoger:
    POST: 18.116.85.15:8080/mutant,  en el body escribir el json que se quiere registrar y enviar la petición 
    GET: 18.116.85.15:8080/stats, enviar la petición.
 3. Para poder ver la base de datos 
    Ingresar en una base de datos oracle (sql developer) los siguientes datos:
    Nombre de conexión: Mutants
    Usuario: MAGNETO
    Contraseña: ADMIN123
    Nombre del host: database-dna.cnoexmoiktwb.us-east-2.rds.amazonaws.com
    Puerto: 1521
    SID: DATABASE
 
** Notas:
El reporte de la cobertura se encuentra en el proyecto, su nombres es index.html

  
