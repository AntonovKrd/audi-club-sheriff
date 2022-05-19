FROM openjdk:17.0.2-jdk
MAINTAINER antonov_krd
COPY target/audi-club-sheriff-1.0.jar audi-club-bot-1.0.jar
ENTRYPOINT ["java","-jar","/audi-club-bot-1.0.jar"]