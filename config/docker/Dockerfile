FROM java:8
COPY geese.jar geese.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-Djava.security.egd=file:/dev/./urandom", "-Denv=@environment@", "-Dversion=@version@", "-jar", "/geese.jar"]