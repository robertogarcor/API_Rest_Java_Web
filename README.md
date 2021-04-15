# API_Rest_Java_Web (Services)
API Rest Javaee Web Jersey, Backend para la gestión de incidencias de usuarios.

### Version 1.0 

### Backend 

**Setup** 

Dependencias:

  Servidor: Glassfish Server

  Dependencias (ver pom.xml): 
 
	MAVEN - JRE - JavaSE-1.7+ 
	javaee-web-api 7.0+
	mysql-connector-java 8.0.19+
	...  
		
### Database Mysql

**Inicializar base de datos | crear usuario propietario de base de datos (Opcional)** 

Ejecutar script sql de inicializacion. Contiene estructura de BD con datos de usuarios y servicios de prueba.

<ul>
  <li><a href="https://github.com/robertogarcor/API_Rest_Java_Web/blob/main/nvrservicesjws/src/main/resources/services.sql">services.sql</a></li>
</ul>

Configurar fichero propiedades <a href="https://github.com/robertogarcor/API_Rest_Java_Web/blob/main/nvrservicesjws/src/main/resources/db.properties">db.properties</a> con los datos de la BD 