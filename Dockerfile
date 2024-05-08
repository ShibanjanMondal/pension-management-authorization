FROM adoptopenjdk/openjdk16:jre-16.0.1_9-debianslim
COPY target/pension-management-authorization-0.0.1.jar pension-management-auth.jar
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "pension-management-auth.jar"]