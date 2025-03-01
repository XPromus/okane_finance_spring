FROM amazoncorretto:21
ARG JAR_FILE=./*.jar
COPY ${JAR_FILE} application.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]