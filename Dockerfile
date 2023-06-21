FROM openjdk:11
ARG JAR_FILE=./build/libs/*.jar
VOLUME /semtleApp
COPY ${JAR_FILE} semtleapp.jar
ENTRYPOINT ["java","-jar","/semtleapp.jar"]