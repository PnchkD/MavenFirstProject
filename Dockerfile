########Maven build stage########
FROM maven:3.8-jdk-11 as maven_build
WORKDIR /app

#copy source
COPY controller/src ./controller/src
COPY email-service/src ./email-service/src
COPY entity/src ./entity/src
COPY repository/src ./repository/src
COPY security/src ./security/src
COPY service/src ./service/src

#copy poms
#copy settings.xml with repository data
COPY settings.xml .
COPY controller/pom.xml ./controller
COPY email-service/pom.xml ./email-service
COPY entity/pom.xml ./entity
COPY repository/pom.xml ./repository
COPY security/pom.xml ./security
COPY service/pom.xml ./service
COPY pom.xml .

#resolve maven dependencies, select settings and profile
RUN mvn -s settings.xml -P panteontrans-maven-central-store clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r automotive_selection/target/

# build the app (no dependency download here)
RUN mvn -s settings.xml -P panteontrans-maven-central-store clean package  -Dmaven.test.skip

# split the built app into multiple layers to improve layer rebuild
RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf /app/automotive_selection/target/automotive_selection*.jar

FROM openjdk:11

WORKDIR /app

COPY --from=maven_build /app/automotive_selection/target/automotive_selection*.jar .

CMD [ "java", "-jar","./automotive_selection.jar" ]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8080/api/account-service/actuator/health || exit 1

EXPOSE 8080:8080
