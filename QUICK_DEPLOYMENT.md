# Quick Deployment Guide

## 🚀 Deploy to Railway (Recommended - Free Tier)

1. **Visit Railway**: Go to [Railway.app](https://railway.app)
2. **Sign up/Login**: Use your GitHub account
3. **Create New Project**: Click "Deploy from GitHub repo"
4. **Select Repository**: Choose `sksaravanakumar18/spring-boot`
5. **Deploy**: Railway will automatically detect the configuration and deploy

**Live URL**: After deployment, you'll get a URL like `https://your-project-name.up.railway.app`

### Accessing the Application:
- **Swagger UI**: `https://your-app.up.railway.app/api/v1/swagger-ui/index.html`
- **Health Check**: `https://your-app.up.railway.app/api/v1/actuator/health`
- **API Endpoints**: `https://your-app.up.railway.app/api/v1/users`

---

## 🔄 Alternative Deployment Options

### Option 1: Render.com (Free Tier)
1. Go to [Render.com](https://render.com)
2. Connect GitHub account
3. Select `sksaravanakumar18/spring-boot` repository
4. Render will use `render.yaml` for configuration

### Option 2: Heroku (Free Tier Ended - Paid Only)
1. Install Heroku CLI
2. Run: `heroku create your-app-name`
3. Run: `git push heroku main`

### Option 3: GitHub Codespaces (Development)
1. Go to GitHub repository
2. Click "Code" → "Codespaces" → "Create codespace"
3. Application will be available at the forwarded port

---

## 🛡️ Security Configuration for Production

The application uses environment-based configuration:
- **Development**: H2 in-memory database, H2 console enabled
- **Production**: MySQL/PostgreSQL, enhanced security

---

## 📊 Monitoring & Management

Once deployed, access these endpoints:
- **Health Check**: `/api/v1/actuator/health`
- **Metrics**: `/api/v1/actuator/metrics`
- **Info**: `/api/v1/actuator/info`

---

## 🎯 Testing the Deployed Application

1. **API Documentation**: Visit Swagger UI at `/swagger-ui/index.html`
2. **Create Users**: Use POST `/api/v1/users` endpoint
3. **List Users**: Use GET `/api/v1/users` endpoint
4. **Health Check**: Verify `/actuator/health` returns UP status

---

## 🔧 Local Development

To run locally:
```bash
mvn spring-boot:run
```

Access:
- **Application**: http://localhost:8080/api/v1
- **Swagger UI**: http://localhost:8080/api/v1/swagger-ui/index.html
- **H2 Console**: http://localhost:8080/api/v1/h2-console (JDBC URL: `jdbc:h2:mem:testdb`)

---

**Note**: The application is now production-ready with comprehensive logging, monitoring, caching, security, and API documentation. Choose your preferred deployment platform and get your Spring Boot application live in minutes!
