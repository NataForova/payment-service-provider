FROM maven:3-openjdk-17 as builder

WORKDIR /tmp/app

ADD . .

RUN mvn package

EXPOSE 8080

WORKDIR /tmp/app/target

ENTRYPOINT ["java", "-jar", "payment-service-provider-0.0.1-SNAPSHOT.war"]
