# Step-by-Step Guide to Dockerize a Microservice Architecture

This guide outlines the process of converting an existing microservice project to Docker to facilitate easier deployment, scaling, and management.

## Step 1: Create Dockerfiles for Each Microservice

Create a `Dockerfile` in each microservice directory with the appropriate configuration for your technology stack. Here's an example for a Java Spring Boot application:

```dockerfile
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Step 2: Update Service Communication

Modify service-to-service communication to use container names instead of `localhost`:
- Replace any hardcoded `localhost` references with the service names you'll define in your docker-compose file
- Update configuration files to use environment variables for service URLs

## Step 3: Build Docker Images for Each Microservice

Build images for each microservice:

```bash
# Navigate to each microservice directory and run:
docker build -t your-org/service-name:version .
```

## Step 4: Create a docker-compose.yml File

Create a docker-compose.yml file in the root directory of your project:

```yaml
version: '3.8'

services:
  service1:
    image: your-org/service1:latest
    ports:
      - "8081:8080"
    environment:
      - SERVICE2_URL=http://service2:8080
    depends_on:
      - service2
      - database
    networks:
      - microservice-network

  service2:
    image: your-org/service2:latest
    ports:
      - "8082:8080"
    environment:
      - DATABASE_URL=jdbc:postgresql://database:5432/mydb
    depends_on:
      - database
    networks:
      - microservice-network

  database:
    image: postgres:13
    volumes:
      - db-data:/var/lib/postgresql/data
    environment:
      - POSTGRES_USER=user
      - POSTGRES_PASSWORD=password
      - POSTGRES_DB=mydb
    networks:
      - microservice-network

networks:
  microservice-network:
    driver: bridge

volumes:
  db-data:
```

## Step 5: Launch Your Dockerized Microservice Architecture

Start all services with Docker Compose:

```bash
docker-compose up -d
```

## Step 6: Test Your Application

Test the services from Postman or any API client:
- Use the mapped ports for direct access (e.g., `http://localhost:8081` for service1)
- Services can communicate with each other using their service names within the Docker network

## Step 7: Common Troubleshooting

- If services can't communicate, verify they're on the same network
- Check logs using `docker-compose logs service-name`
- Ensure environment variables are correctly passed to containers
