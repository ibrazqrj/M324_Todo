Dieses Tutorial beschreibt Schritt für Schritt, wie man ein Frontend und Backend aus dem M324-Projekt mit Docker containerisierst, in die GitHub Container Registry pushst und anschließend mit `docker-compose` lokal laufen lässt.

## Voraussetzungen

* Docker & Docker Compose installiert
* GitHub Repository mit Zugriff auf GitHub Actions
* Zugriff auf `ghcr.io` (GitHub Container Registry)

---

## 1. Dockerfile für das Backend

**Pfad:** `backend/Dockerfile`

```Dockerfile
# Build Phase
FROM maven:3.9.6-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run Phase
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## 2. Dockerfile für das Frontend

**Pfad:** `frontend/Dockerfile`

```Dockerfile
# Build Phase
FROM node:20-alpine as build
WORKDIR /app
COPY . .
RUN npm install && npm run build

# Serve via Nginx
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
```

---

## 3. GitHub Action: CI/CD Deployment

**Pfad:** `.github/workflows/docker-publish.yml`

```yaml
name: Build and Push Docker Images

on:
  push:
    branches: [ main ]

jobs:
  build-and-push:
    runs-on: ubuntu-latest
    permissions:
      contents: read
      packages: write

    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up Docker Buildx
        uses: docker/setup-buildx-action@v3

      - name: Log in to GitHub Container Registry
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ secrets.GITHUB_TOKEN }}

      - name: Build and push backend
        uses: docker/build-push-action@v5
        with:
          context: ./backend
          file: ./backend/Dockerfile
          push: true
          tags: ghcr.io/${{ github.repository_owner }}/m324-backend:latest

      - name: Build and push frontend
        uses: docker/build-push-action@v5
        with:
          context: ./frontend
          file: ./frontend/Dockerfile
          push: true
          tags: ghcr.io/${{ github.repository_owner }}/m324-frontend:latest
```

---

## 4. Docker Compose Infrastruktur

**Pfad:** `docker-compose.yml`

```yaml
version: "3"
services:
  backend:
    image: ghcr.io/ibrazqrj/m324-backend:latest
    ports:
      - "8080:8080"

  frontend:
    image: ghcr.io/ibrazqrj/m324-frontend:latest
    ports:
      - "5173:80"
```

---

## 5. Lokale Ausführung

```bash
docker compose up --build
```

Dann erreichst du:

* **Frontend:** [http://localhost:5173](http://localhost:5173)
* **Backend:** [http://localhost:8080](http://localhost:8080)

---

