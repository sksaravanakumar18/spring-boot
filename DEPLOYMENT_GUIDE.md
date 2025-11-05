# Spring Boot Deployment Guide

## 🚀 Complete Deployment Options

### **1. Local Development**
```bash
# Run with Maven
mvn spring-boot:run

# Run with Docker
docker-compose up -d
```

### **2. GitHub Pages (Documentation Only)**
```bash
# Create docs directory
mkdir docs
cp README.md docs/
cp *.md docs/

# Enable GitHub Pages in repository settings
# Go to Settings > Pages > Source: GitHub Actions
```

### **3. Heroku Deployment**

#### Prerequisites:
- Heroku CLI installed
- Heroku account

#### Steps:
```bash
# Login to Heroku
heroku login

# Create Heroku app
heroku create spring-boot-master-app

# Set environment variables
heroku config:set SPRING_PROFILES_ACTIVE=prod
heroku config:set SERVER_PORT=$PORT

# Deploy
git push heroku main

# Open application
heroku open
```

#### Heroku Procfile:
```
web: java -Dserver.port=$PORT -jar target/spring-boot-master-1.0.0.jar
```

### **4. Railway Deployment**

#### Steps:
1. Connect GitHub repository to Railway
2. Set environment variables:
   - `SPRING_PROFILES_ACTIVE=prod`
   - `PORT=8080`
3. Deploy automatically on push

### **5. Render Deployment**

#### Steps:
1. Connect GitHub repository
2. Set build command: `mvn clean package -DskipTests`
3. Set start command: `java -jar target/spring-boot-master-1.0.0.jar`
4. Set environment variables

### **6. AWS Elastic Beanstalk**

#### Prerequisites:
- AWS CLI configured
- EB CLI installed

#### Steps:
```bash
# Initialize EB application
eb init

# Create environment
eb create production

# Deploy
eb deploy

# Open application
eb open
```

### **7. Google Cloud Platform (Cloud Run)**

#### Steps:
```bash
# Build and push to GCR
gcloud builds submit --tag gcr.io/PROJECT_ID/spring-boot-master

# Deploy to Cloud Run
gcloud run deploy --image gcr.io/PROJECT_ID/spring-boot-master --platform managed
```

### **8. Azure Container Instances**

#### Steps:
```bash
# Create resource group
az group create --name spring-boot-rg --location eastus

# Create container
az container create \
  --resource-group spring-boot-rg \
  --name spring-boot-master \
  --image your-dockerhub/spring-boot-master \
  --ports 8080 \
  --dns-name-label spring-boot-master-unique
```

## 🌐 **Access Points After Deployment**

### **Local (http://localhost:8080)**
- **Main Application**: `/api/v1`
- **Swagger UI**: `/api/v1/swagger-ui.html`
- **H2 Console**: `/api/v1/h2-console`
- **Actuator Health**: `/api/v1/actuator/health`

### **Cloud (https://your-app-domain.com)**
- **Main Application**: `/api/v1`
- **Swagger UI**: `/api/v1/swagger-ui.html`
- **Database Console**: `/api/v1/h2-console` (dev only)
- **Health Check**: `/api/v1/actuator/health`
- **Metrics**: `/api/v1/actuator/metrics`

## 🔧 **Environment Configuration**

### **Development Profile** (`application-dev.properties`)
```properties
# H2 In-Memory Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true

# Logging
logging.level.com.springboot.master=DEBUG
```

### **Production Profile** (`application-prod.properties`)
```properties
# Production Database (MySQL/PostgreSQL)
spring.datasource.url=${DATABASE_URL:jdbc:mysql://localhost:3306/springboot_master_prod}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:password}

# JPA Settings
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Security
spring.h2.console.enabled=false

# Logging
logging.level.com.springboot.master=INFO
```

## 🛡️ **Security Configuration for Production**

### **Environment Variables** (Set in your deployment platform)
```bash
# Database
DATABASE_URL=jdbc:mysql://your-db-host:3306/springboot_master
DB_USERNAME=your-username
DB_PASSWORD=your-password

# Application
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080

# Security (if using custom auth)
JWT_SECRET=your-jwt-secret
ADMIN_PASSWORD=your-admin-password
```

## 📊 **Monitoring & Health Checks**

### **Health Check Endpoints**
- **Liveness**: `/api/v1/actuator/health/liveness`
- **Readiness**: `/api/v1/actuator/health/readiness`
- **Metrics**: `/api/v1/actuator/metrics`
- **Info**: `/api/v1/actuator/info`

### **Application Metrics**
```bash
# HTTP requests
curl https://your-app.com/api/v1/actuator/metrics/http.server.requests

# JVM metrics
curl https://your-app.com/api/v1/actuator/metrics/jvm.memory.used

# Database connections
curl https://your-app.com/api/v1/actuator/metrics/hikaricp.connections
```

## 🔍 **Testing Deployed Application**

### **API Testing with curl**
```bash
# Health check
curl https://your-app.com/api/v1/actuator/health

# Create user
curl -X POST https://your-app.com/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "role": "USER"
  }'

# Get users
curl https://your-app.com/api/v1/users
```

### **Load Testing**
```bash
# Using Apache Bench
ab -n 1000 -c 10 https://your-app.com/api/v1/actuator/health

# Using wrk
wrk -t12 -c400 -d30s https://your-app.com/api/v1/users
```

---

## 🎯 **Quick Deployment Checklist**

- [ ] ✅ **Code pushed to GitHub**
- [ ] ⚙️ **CI/CD pipeline configured**
- [ ] 🐳 **Docker image built**
- [ ] 🌐 **Cloud platform chosen**
- [ ] 🔧 **Environment variables set**
- [ ] 🛡️ **Security configured**
- [ ] 📊 **Monitoring enabled**
- [ ] 🧪 **Application tested**
- [ ] 📚 **Documentation updated**

**Your Spring Boot application is now ready for production deployment! 🚀**
