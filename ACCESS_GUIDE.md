# 🌐 Spring Boot Application - Complete Access Guide

## 📍 **Application Access Points**

### **🏠 Local Development** (`http://localhost:8080`)

| Service | URL | Description |
|---------|-----|-------------|
| **Main API** | `http://localhost:8080/api/v1` | Base API endpoint |
| **Swagger UI** | `http://localhost:8080/api/v1/swagger-ui.html` | Interactive API documentation |
| **OpenAPI Docs** | `http://localhost:8080/api/v1/api-docs` | Raw OpenAPI specification |
| **H2 Database Console** | `http://localhost:8080/api/v1/h2-console` | Database management interface |
| **Health Check** | `http://localhost:8080/api/v1/actuator/health` | Application health status |
| **Metrics** | `http://localhost:8080/api/v1/actuator/metrics` | Application metrics |
| **Info** | `http://localhost:8080/api/v1/actuator/info` | Application information |
| **Prometheus Metrics** | `http://localhost:8080/api/v1/actuator/prometheus` | Prometheus format metrics |

#### **H2 Database Console Access:**
```
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password
```

---

### **☁️ Cloud Deployment Access**

Replace `{your-domain}` with your actual deployed application domain:

| Platform | Example URL | Swagger UI | Health Check |
|----------|-------------|------------|--------------|
| **Heroku** | `https://your-app.herokuapp.com` | `/api/v1/swagger-ui.html` | `/api/v1/actuator/health` |
| **Railway** | `https://your-app.up.railway.app` | `/api/v1/swagger-ui.html` | `/api/v1/actuator/health` |
| **Render** | `https://your-app.onrender.com` | `/api/v1/swagger-ui.html` | `/api/v1/actuator/health` |
| **Vercel** | `https://your-app.vercel.app` | `/api/v1/swagger-ui.html` | `/api/v1/actuator/health` |
| **Netlify** | `https://your-app.netlify.app` | `/api/v1/swagger-ui.html` | `/api/v1/actuator/health` |

---

## 🧪 **API Testing Examples**

### **1. Health Check**
```bash
curl https://your-app.com/api/v1/actuator/health
```

**Expected Response:**
```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "diskSpace": { "status": "UP" }
  }
}
```

### **2. Create User**
```bash
curl -X POST https://your-app.com/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123",
    "role": "USER"
  }'
```

### **3. Get All Users**
```bash
curl https://your-app.com/api/v1/users
```

### **4. Get User by ID**
```bash
curl https://your-app.com/api/v1/users/1
```

---

## 🎯 **Swagger UI Features**

### **What You Can Do:**
1. **📖 Browse APIs** - See all available endpoints
2. **🧪 Test APIs** - Execute requests directly from browser
3. **📝 View Schemas** - Understand request/response formats
4. **🔍 Filter Operations** - Search for specific endpoints
5. **📋 Export** - Download OpenAPI specification

### **How to Use Swagger UI:**
1. Navigate to: `{your-app-url}/api/v1/swagger-ui.html`
2. Click on any endpoint to expand it
3. Click "Try it out" button
4. Fill in parameters/request body
5. Click "Execute" to test the API
6. View the response below

---

## 🗄️ **Database Access**

### **Development (H2 Console)**
- **URL**: `http://localhost:8080/api/v1/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

### **Production (External Database)**
Production databases are accessed via environment variables and not directly exposed for security.

---

## 📊 **Monitoring & Metrics**

### **Actuator Endpoints:**
```bash
# Health status
curl {your-app-url}/api/v1/actuator/health

# Application info
curl {your-app-url}/api/v1/actuator/info

# JVM metrics
curl {your-app-url}/api/v1/actuator/metrics/jvm.memory.used

# HTTP request metrics
curl {your-app-url}/api/v1/actuator/metrics/http.server.requests

# Database connection pool
curl {your-app-url}/api/v1/actuator/metrics/hikaricp.connections
```

### **Custom Metrics:**
```bash
# Cache statistics
curl {your-app-url}/api/v1/actuator/metrics/cache.gets

# Business metrics (if implemented)
curl {your-app-url}/api/v1/actuator/metrics/users.created
```

---

## 🔐 **Security & Authentication**

### **Development Mode:**
- Basic authentication is enabled
- Default credentials may be configured
- H2 Console is accessible

### **Production Mode:**
- Enhanced security settings
- Environment-based credentials
- H2 Console is disabled
- HTTPS enforced (on cloud platforms)

---

## 🚀 **Quick Access Shortcuts**

### **Bookmark These URLs:**
```
📱 Main App: {your-app-url}/api/v1
📚 Swagger: {your-app-url}/api/v1/swagger-ui.html
💚 Health: {your-app-url}/api/v1/actuator/health
📊 Metrics: {your-app-url}/api/v1/actuator/metrics
```

### **Browser Extensions for API Testing:**
- **Postman** - Complete API testing suite
- **Insomnia** - Lightweight API client  
- **Thunder Client** - VS Code extension
- **REST Client** - VS Code extension

---

## 🎮 **Interactive Demo Steps**

### **Step 1: Verify Application**
```bash
curl {your-app-url}/api/v1/actuator/health
```

### **Step 2: Open Swagger UI**
Navigate to: `{your-app-url}/api/v1/swagger-ui.html`

### **Step 3: Test User Creation**
1. Find `POST /users` endpoint
2. Click "Try it out"
3. Use sample JSON data
4. Click "Execute"

### **Step 4: View Created User**
1. Find `GET /users` endpoint  
2. Click "Try it out"
3. Click "Execute"
4. See your created user in the response

### **Step 5: Check Database (Local Only)**
1. Go to: `http://localhost:8080/api/v1/h2-console`
2. Login with credentials
3. Run: `SELECT * FROM users;`
4. See your data in the database

---

## 📱 **Mobile-Friendly Access**

All interfaces are responsive and work on mobile devices:
- **Swagger UI** - Touch-friendly API testing
- **H2 Console** - Mobile database access  
- **Actuator** - Mobile monitoring

---

## 🔧 **Troubleshooting**

### **Common Issues:**

| Issue | Solution |
|-------|----------|
| **404 Not Found** | Check if application is running and URL is correct |
| **401 Unauthorized** | Provide authentication credentials |
| **500 Server Error** | Check application logs and database connection |
| **CORS Issues** | Configure CORS settings in SecurityConfig |

### **Health Check Failed:**
```bash
# Check detailed health
curl {your-app-url}/api/v1/actuator/health/readiness
curl {your-app-url}/api/v1/actuator/health/liveness
```

---

**🎉 Your Spring Boot application is now accessible from anywhere with full UI access!**
