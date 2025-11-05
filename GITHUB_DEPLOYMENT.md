# 🚀 GitHub Deployment Guide - Spring Boot Master Project

## 📋 **Quick Start Deployment Options**

### **🌟 Option 1: Deploy to Railway (Recommended)**

Railway offers the easiest deployment with automatic HTTPS and custom domains.

#### **Steps:**
1. **Go to [Railway.app](https://railway.app)**
2. **Sign up with GitHub**
3. **Click "Deploy from GitHub repo"**
4. **Select repository**: `sksaravanakumar18/spring-boot`
5. **Railway auto-detects Spring Boot** and deploys
6. **Get your live URL**: `https://spring-boot-production-xxxx.up.railway.app`

#### **Access Your Deployed App:**
```
🏠 Main API: https://your-app.up.railway.app/api/v1
📚 Swagger UI: https://your-app.up.railway.app/api/v1/swagger-ui.html  
💚 Health Check: https://your-app.up.railway.app/api/v1/actuator/health
📊 Metrics: https://your-app.up.railway.app/api/v1/actuator/metrics
```

---

### **🌟 Option 2: Deploy to Render (Free Tier)**

Render offers free hosting perfect for learning projects.

#### **Steps:**
1. **Go to [Render.com](https://render.com)**
2. **Connect your GitHub account**
3. **Create new Web Service**
4. **Connect repository**: `sksaravanakumar18/spring-boot`
5. **Configure:**
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar target/spring-boot-master-1.0.0.jar`
   - **Environment Variables**: `SPRING_PROFILES_ACTIVE=prod`
6. **Deploy and get URL**: `https://spring-boot-master.onrender.com`

---

### **🌟 Option 3: Deploy to Heroku**

#### **Prerequisites:**
- Install [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)

#### **Manual Deployment:**
```powershell
# Login to Heroku
heroku login

# Create Heroku app
heroku create your-spring-boot-app

# Set environment variables
heroku config:set SPRING_PROFILES_ACTIVE=prod
heroku config:set JAVA_TOOL_OPTIONS="-Xmx300m"

# Deploy
git push heroku main

# Open application
heroku open
```

#### **Automatic Deployment (GitHub Actions):**
1. **Set Heroku secrets in GitHub:**
   - Go to your repo: Settings → Secrets → Actions
   - Add secrets:
     ```
     HEROKU_API_KEY: your-heroku-api-key
     HEROKU_APP_NAME: your-app-name
     HEROKU_EMAIL: your-email@example.com
     ```
2. **Push to main branch** - automatic deployment!

---

### **🌟 Option 4: GitHub Codespaces (Development)**

Run your Spring Boot app directly in GitHub with zero setup.

#### **Steps:**
1. **Go to your GitHub repository**
2. **Click "Code" → "Codespaces" → "Create codespace on main"**
3. **Wait for environment setup**
4. **Run application:**
   ```bash
   mvn spring-boot:run
   ```
5. **Access via forwarded port 8080**

---

## 🔗 **Live Application Access**

Once deployed, your application will be accessible at:

| **Interface** | **URL** | **Purpose** |
|---------------|---------|-------------|
| **🏠 Main API** | `https://your-app.com/api/v1` | REST API endpoints |
| **📚 Swagger UI** | `https://your-app.com/api/v1/swagger-ui.html` | **Interactive API Documentation** |
| **💚 Health Check** | `https://your-app.com/api/v1/actuator/health` | Application status |
| **📊 Metrics** | `https://your-app.com/api/v1/actuator/metrics` | Performance monitoring |
| **📋 API Docs** | `https://your-app.com/api/v1/api-docs` | OpenAPI specification |

---

## 🧪 **Test Your Deployed Application**

### **1. Quick Health Check:**
```bash
curl https://your-deployed-app.com/api/v1/actuator/health
```

**Expected Response:**
```json
{
  "status": "UP",
  "components": {
    "db": {"status": "UP"},
    "diskSpace": {"status": "UP"}
  }
}
```

### **2. Create a User via API:**
```bash
curl -X POST https://your-app.com/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "role": "USER"
  }'
```

### **3. Get All Users:**
```bash
curl https://your-app.com/api/v1/users
```

### **4. Test with Swagger UI:**
1. **Open**: `https://your-app.com/api/v1/swagger-ui.html`
2. **Expand `POST /users`**
3. **Click "Try it out"**
4. **Fill in user data**
5. **Click "Execute"**
6. **See live response!**

---

## 📊 **Monitoring Your Deployed App**

### **Application Metrics:**
```bash
# Memory usage
curl https://your-app.com/api/v1/actuator/metrics/jvm.memory.used

# HTTP requests
curl https://your-app.com/api/v1/actuator/metrics/http.server.requests

# Database connections  
curl https://your-app.com/api/v1/actuator/metrics/hikaricp.connections
```

### **Health Checks:**
```bash
# Overall health
curl https://your-app.com/api/v1/actuator/health

# Readiness probe
curl https://your-app.com/api/v1/actuator/health/readiness

# Liveness probe  
curl https://your-app.com/api/v1/actuator/health/liveness
```

---

## 🔧 **Troubleshooting Deployment Issues**

### **Common Issues & Solutions:**

| **Issue** | **Solution** |
|-----------|--------------|
| **Build Fails** | Check Java 17 compatibility in `pom.xml` |
| **Application Won't Start** | Verify `SPRING_PROFILES_ACTIVE=prod` |
| **Database Errors** | Use in-memory H2 for cloud deployment |
| **Port Issues** | Use `$PORT` environment variable |
| **Memory Issues** | Set `JAVA_TOOL_OPTIONS="-Xmx512m"` |

### **Check Application Logs:**

**Railway:**
```bash
railway logs
```

**Heroku:**
```bash
heroku logs --tail --app your-app-name
```

**Render:**
- Check logs in Render dashboard

---

## 🎯 **Deployment Verification Checklist**

- [ ] ✅ **Application starts successfully**
- [ ] ✅ **Health check returns 200 OK**
- [ ] ✅ **Swagger UI loads and works**
- [ ] ✅ **Can create users via API**
- [ ] ✅ **Can retrieve users via API**
- [ ] ✅ **Database operations work**
- [ ] ✅ **Metrics endpoint accessible**
- [ ] ✅ **HTTPS enabled (automatic on cloud platforms)**

---

## 📚 **What You Get After Deployment**

### **✨ Live Spring Boot Application with:**
- **🌐 Public URL** accessible from anywhere
- **📱 Mobile-friendly Swagger UI** for API testing
- **🔍 Real-time monitoring** with Actuator endpoints
- **💾 In-memory database** that resets on restart
- **🛡️ Security** with HTTPS encryption
- **📊 Performance metrics** and health checks
- **🚀 Auto-scaling** (platform dependent)

### **🎮 Interactive Features:**
- **Test APIs directly in browser** via Swagger UI
- **Monitor application health** in real-time
- **Create/read/update/delete users** through REST API
- **View performance metrics** and system info
- **Download API documentation** in multiple formats

---

## 🔗 **Example Live URLs**

Once deployed, bookmark these for quick access:

```
🏠 Your App: https://spring-boot-master-xxxx.up.railway.app
📚 API Docs: https://spring-boot-master-xxxx.up.railway.app/api/v1/swagger-ui.html
💚 Health: https://spring-boot-master-xxxx.up.railway.app/api/v1/actuator/health
📊 Metrics: https://spring-boot-master-xxxx.up.railway.app/api/v1/actuator/metrics
```

---

**🎉 Your Spring Boot application is now live on the internet with full API documentation and monitoring capabilities!**

**🌟 Perfect for portfolio, learning, and demonstrating Spring Boot expertise!**
