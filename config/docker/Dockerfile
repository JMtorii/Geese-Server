FROM java:8
COPY geese.jar geese.jar
COPY application.properties application.properties
EXPOSE 8080 8443
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-Djava.security.egd=file:/dev/./urandom", "-Denv=@environment@", "-Dversion=@version@", "-jar", "/geese.jar"]