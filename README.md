# Spring Boot Project 
That is the **backend part** of full stack project consisting of (at least) 4 Docker Containers 
started with docker-compose file
- database container (postgres)
- database user interface container (pgadmin4)
- backend container (spring boot)
- [frontend container (angular)](https://github.com/val3ri/angular-project-template)

You need this [docker-compose.yml file](https://github.com/val3ri/infrastructure-for-fullstack-project) for building this multiple container application.

#### Build springboot jar file (used in the docker image)
```
./mvnw package
```

#### Building docker image 
```
docker build -t springbootapp .
```