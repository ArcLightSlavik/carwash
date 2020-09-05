# Car wash

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

Starts up a docker instance to which you make requests.

After which you should be able to make requests using postman or curl.

### Option 2: Running spring-boot directly

```bash
$ git clone https://github.com/ArcLightSlavik/carwash.git
$ cd carwash
$ mvn spring-boot:run
```

### Usage
```
Postman: https://www.getpostman.com/collections/31c61f9c25119cf6df3e
```
