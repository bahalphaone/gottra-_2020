FROM ubuntu:20.04
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update -y && apt-get install -y apt-utils && apt-get upgrade -y
RUN apt-get install -y libgtk2.0-0 libcanberra-gtk-module libxext-dev libxrender-dev libxtst-dev mysql-server mysql-client

RUN apt-get install dialog apt-utils -y
RUN apt-get update
RUN apt-get install -y xauth openssh-server 
RUN apt-get update
# laisser Firefox, sinon eclipse ne se lance pas sous windows
RUN apt-get install -y firefox
RUN apt-get update -y && apt-get upgrade -y
RUN apt-get install -y openjdk-8-jdk 

RUN apt-get install -y curl libgtk2.0-0 libcanberra-gtk-module libxext-dev libxrender-dev libxtst-dev
RUN export uid=1000 gid=1000 && \
    mkdir -p /home/workspace && \
    chown ${uid}:${gid} -R /home/workspace

RUN apt-get install -y python3.6

RUN ln -s /workspace workspace
RUN curl -fSL "https://download.springsource.com/release/STS4/4.6.2.RELEASE/dist/e4.15/spring-tool-suite-4-4.6.2.RELEASE-e4.15.0-linux.gtk.x86_64.tar.gz" -o eclipse.tar.gz \
	&& tar -xvf eclipse.tar.gz && rm eclipse.tar.gz

EXPOSE 80

CMD  sts-4.6.2.RELEASE/SpringToolSuite4

#/usr/lib/jvm/java-11-openjdk-amd64/bin/javaw.exe