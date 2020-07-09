FROM openjdk:8
ADD  target/SensorProject-1.0-SNAPSHOT.jar SensorProject-1.0-SNAPSHOT.
EXPOSE 8080
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=production","Splunk-IntegratedToolsDashboard.jar"]


