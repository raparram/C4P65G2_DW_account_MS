# account_MS
Miscroservicio Cuenta bancaria para banco desarrollado en Spring Boot dockerizado.

# Despliegue local:
Este proyecto se desarrolló en Apache NetBeans 12.5, una versión anterior puede servir desde que permita el uso de Maven y de Spring Boot, para la base de datos se utilizó Mongo Atlas. Para ejecutar de manera exitosa un clon de este repositorio se recomienda usar las herramientas mencionadas, al utilizar Maven todas las dependencias serán instaladas al darle Build al proyecto, debe garantizar que el Build sea compilado con JDK 1.8.

Después de terminar el proceso de Build pude ejecutar el archivo AplicacionApplication.java, y es posible ingresar al aplicativo en http://localhost:8081/, si lo desea puede editarse el puerto y las credenciales de la base de datos en el archivo application.properties

# Para desplegar la app en Heroku
```
.\mvnw package
docker build -t name_app .

heroku login
```
se inicia sesion en la web de heroku
```
heroku create name_app
heroku container:login
heroku container:push web -a name_app
heroku container:release web -a name_app
heroku open -a name_app
```

# Para verificar Endpoints
Para conocer y verificar los endpoints lo invitamos al siguiente link:  https://github.com/raparram/C4P65G2_DW_4a-docs/blob/main/Microservicios%20API%20tests/API_ACCOUNT_HEROKU.postman_collection.json , donde se muestra un JSON con los endpoints listos para correr desde Postman.

# Para mas información
Por favor visitar: https://github.com/raparram/C4P65G2_DW_4a-docs
