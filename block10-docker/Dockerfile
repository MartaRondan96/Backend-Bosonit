#FROM especifica la imagen de la cual parte
FROM openjdk:11
#EXPOSE especifica en que puerto esta escuchando el contenedor
EXPOSE 8081
COPY target/*.jar /usr/local/lib/spring.jar
ENTRYPOINT ["java" , "-jar","/usr/local/lib/spring.jar"]