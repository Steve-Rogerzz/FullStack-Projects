# 🚀 Deployment Guide

Hosting a Spring Boot application for free requires a platform that supports a Java Runtime Environment (JRE) or Docker. Unlike static sites (which work on Netlify/Vercel), Spring Boot is a "long-running" server process.

## 🏆 Recommended Free Tiers

### 1. Render (Easiest)
Render offers a "Free" tier that supports Web Services.
- **Pros**: Very easy setup (connect GitHub and go).
- **Cons**: The server "sleeps" after 15 minutes of inactivity. The first request after a sleep will be slow (30s+).
- **How to**: 
    1. Create a `Dockerfile` (see below).
    2. Connect your GitHub repo to Render.
    3. Choose **Web Service** and select **Docker** environment.

### 2. Railway (Best Experience)
Railway has a very developer-friendly interface.
- **Pros**: No "sleeping" (apps stay awake as long as you have credits).
- **Cons**: They give $5 of free credit one-time or monthly depending on their current policy.
- **How to**: Just connect your GitHub repo. Railway detects the `pom.xml` and builds it automatically.

### 3. Oracle Cloud (Truly Free & Powerful)
The "Always Free" tier is the most powerful free hosting available.
- **Pros**: 4 OCPUs and 24GB of RAM (ARM). No sleeping.
- **Cons**: Requires a credit card for identity verification and has a slightly complex setup.
- **How to**: Create an "Always Free" Compute Instance (Ubuntu), install JDK 21, and run your jar.

### 4. Koyeb
Koyeb provides a free tier for one "Nano" instance.
- **Pros**: Good performance, supports Docker.
- **How to**: Connect GitHub and deploy as a Docker container.

---

## 🐳 Dockerizing your App
To host on most of these platforms, adding a `Dockerfile` to your root directory is the most reliable method.

**Create a file named `Dockerfile` in the root folder:**
```dockerfile
# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## ⚠️ Important Note on Database
This project currently uses an **In-Memory H2 Database**. 
- **Effect**: Every time your app restarts (or goes to sleep on Render), **all data will be wiped**.
- **Solution**: For production, you should connect a persistent database like **PostgreSQL** (available for free on Supabase or Neon.tech).
