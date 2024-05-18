FROM openjdk:23-jdk
MAINTAINER Sowrd <fanmeijian@gmail.com>

#WORKDIR /opt/services
# copy resource folder
COPY *.jar /opt/services/sparrow-server.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/services/sparrow-server.jar"]