# Spring Boot Deployment Script (PowerShell)

Write-Host "🚀 Spring Boot Master Project - Quick Deployment" -ForegroundColor Green
Write-Host "================================================" -ForegroundColor Green

function Deploy-Heroku {
    Write-Host "📦 Deploying to Heroku..." -ForegroundColor Yellow
    
    # Check if Heroku CLI is installed
    if (!(Get-Command "heroku" -ErrorAction SilentlyContinue)) {
        Write-Host "❌ Heroku CLI not found. Please install it first." -ForegroundColor Red
        return
    }
    
    # Login to Heroku
    heroku login
    
    # Create app
    $appName = Read-Host "Enter your Heroku app name"
    heroku create $appName
    
    # Set environment variables
    heroku config:set SPRING_PROFILES_ACTIVE=prod --app $appName
    heroku config:set JAVA_TOOL_OPTIONS="-Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8" --app $appName
    
    # Deploy
    git push heroku main
    
    # Open application
    heroku open --app $appName
    
    Write-Host "✅ Deployed to Heroku: https://$appName.herokuapp.com" -ForegroundColor Green
    Write-Host "🔗 Swagger UI: https://$appName.herokuapp.com/api/v1/swagger-ui.html" -ForegroundColor Cyan
    Write-Host "📊 Health Check: https://$appName.herokuapp.com/api/v1/actuator/health" -ForegroundColor Cyan
}

function Build-Docker {
    Write-Host "🐳 Building Docker image..." -ForegroundColor Yellow
    
    docker build -t spring-boot-master:latest .
    
    Write-Host "✅ Docker image built successfully" -ForegroundColor Green
    Write-Host "🏃‍♂️ Run locally with: docker run -p 8080:8080 spring-boot-master:latest" -ForegroundColor Cyan
}

function Deploy-DockerCompose {
    Write-Host "🐳 Deploying with Docker Compose..." -ForegroundColor Yellow
    
    docker-compose up -d
    
    Write-Host "✅ Application deployed with Docker Compose" -ForegroundColor Green
    Write-Host "🔗 Access at: http://localhost:8080/api/v1" -ForegroundColor Cyan
    Write-Host "🔗 Swagger UI: http://localhost:8080/api/v1/swagger-ui.html" -ForegroundColor Cyan
    Write-Host "🗄️ H2 Console: http://localhost:8080/api/v1/h2-console" -ForegroundColor Cyan
}

function Run-Local {
    Write-Host "🏃‍♂️ Running locally with Maven..." -ForegroundColor Yellow
    mvn spring-boot:run
}

# Main menu
Write-Host "Choose deployment option:" -ForegroundColor Cyan
Write-Host "1. Deploy to Heroku"
Write-Host "2. Build Docker image"
Write-Host "3. Deploy with Docker Compose"
Write-Host "4. Run locally with Maven"

$choice = Read-Host "Enter your choice (1-4)"

switch ($choice) {
    "1" { Deploy-Heroku }
    "2" { Build-Docker }
    "3" { Deploy-DockerCompose }
    "4" { Run-Local }
    default { 
        Write-Host "❌ Invalid choice" -ForegroundColor Red
        exit 1 
    }
}
