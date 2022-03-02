#_______________v0____________________
#FROM adoptopenjdk/maven-openjdk11 AS MAVEN_BUILD
#MAINTAINER Pedro Bacchini
#COPY pom.xml /build/
#COPY src /build/src/
#WORKDIR /build/
#RUN mvn package -DexcludedGroups=integration
#FROM openjdk:11.0.4-jre-slim-buster
#WORKDIR /app
#COPY --from=MAVEN_BUILD /build/target/*.jar /app/app.jar
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]

#_______________v0.1____________________
#FROM adoptopenjdk/maven-openjdk11 AS MAVEN_BUILD
#MAINTAINER Pedro Bacchini
#COPY pom.xml /build/
#COPY src /build/src/
#WORKDIR /build/
#RUN mvn package -DexcludedGroups=integration
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#FROM openjdk:11.0.4-jre-slim-buster
#ARG DEPENDENCY=/build/target/dependency
#COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=MAVEN_BUILD ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.pedrobacchini.mercadolivreevaluation.MercadoLivreEvaluationApplication"]

#_______________v1____________________
#FROM openjdk:11.0.4-jre-slim-buster
#MAINTAINER Pedro Bacchini
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#_______________v2____________________
#FROM adoptopenjdk/openjdk11:alpine
#MAINTAINER Pedro Bacchini
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#_______________v3____________________
#FROM adoptopenjdk/openjdk11:alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.pedrobacchini.mercadolivreevaluation.MercadoLivreEvaluationApplication"]

##_______________v4____________________
##FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9-slim
#FROM adoptopenjdk/openjdk11:alpine as build
#WORKDIR /workspace/app
#
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN ./mvnw install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM adoptopenjdk/openjdk11:alpine
#VOLUME /tmp
#ARG DEPENDENCY=/workspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.pedrobacchini.mercadolivreevaluation.MercadoLivreEvaluationApplication"]

##_______________v5____________________
## syntax=docker/dockerfile:experimental
#FROM adoptopenjdk/openjdk11:alpine as build
#WORKDIR /workspace/app
#
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#COPY src src
#
#RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM adoptopenjdk/openjdk11:alpine
#VOLUME /tmp
#ARG DEPENDENCY=/workspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.pedrobacchini.mercadolivreevaluation.MercadoLivreEvaluationApplication"]