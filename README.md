# Car wash

### -not hugely sure of quality of code since i will rewriting the entire project soon-

#### TO-DO:
1. Change the whole domain 
2. Completely rethink connection between classes
3. Create an actually usable app 
4. Remake all of the tests
5. Continue Docker/SonarQube integration with possibility of Jenkins
6. Create an angular front-end 

## Installation

### Option 1: Docker Container

While slightly harder it's preferred over the other option

```bash
$ git clone https://github.com/ArcLightSlavik/carwash.git
$ cd carwash
$ mvn package
$ docker image build -t carwash:latest .
$ docker container run -p 8080:8080 -d --name carwash-container carwash
```

Should start up a docker instance which you can see if you run docker ps:

![Docker_ps](https://imgur.com/SXmOfDH.png) 

After which you should be able to run postman:

![Postman](https://imgur.com/Ya9oPbT.png)


### Option 2: Running spring-boot directly

```bash
$ git clone https://github.com/ArcLightSlavik/carwash.git
$ cd carwash
$ mvn spring-boot:run
```

### Basic usage
```
Postman: https://www.getpostman.com/collections/ef1225b2851f2c3a76a8
```
