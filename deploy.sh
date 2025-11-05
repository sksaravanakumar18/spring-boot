#!/bin/bash

# Spring Boot Deployment Script

echo "🚀 Spring Boot Master Project - Quick Deployment"
echo "================================================"

# Function to deploy to Heroku
deploy_heroku() {
    echo "📦 Deploying to Heroku..."
    
    # Check if Heroku CLI is installed
    if ! command -v heroku &> /dev/null; then
        echo "❌ Heroku CLI not found. Please install it first."
        return 1
    fi
    
    # Login to Heroku
    heroku login
    
    # Create app if it doesn't exist
    read -p "Enter your Heroku app name: " app_name
    heroku create $app_name
    
    # Set environment variables
    heroku config:set SPRING_PROFILES_ACTIVE=prod --app $app_name
    heroku config:set JAVA_TOOL_OPTIONS="-Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8" --app $app_name
    
    # Deploy
    git push heroku main
    
    # Open application
    heroku open --app $app_name
    
    echo "✅ Deployed to Heroku: https://$app_name.herokuapp.com"
    echo "🔗 Swagger UI: https://$app_name.herokuapp.com/api/v1/swagger-ui.html"
    echo "📊 Health Check: https://$app_name.herokuapp.com/api/v1/actuator/health"
}

# Function to build Docker image
build_docker() {
    echo "🐳 Building Docker image..."
    
    docker build -t spring-boot-master:latest .
    
    echo "✅ Docker image built successfully"
    echo "🏃‍♂️ Run locally with: docker run -p 8080:8080 spring-boot-master:latest"
}

# Function to deploy with Docker Compose
deploy_docker_compose() {
    echo "🐳 Deploying with Docker Compose..."
    
    docker-compose up -d
    
    echo "✅ Application deployed with Docker Compose"
    echo "🔗 Access at: http://localhost:8080/api/v1"
    echo "🔗 Swagger UI: http://localhost:8080/api/v1/swagger-ui.html"
    echo "🗄️ H2 Console: http://localhost:8080/api/v1/h2-console"
}

# Main menu
echo "Choose deployment option:"
echo "1. Deploy to Heroku"
echo "2. Build Docker image"
echo "3. Deploy with Docker Compose"
echo "4. Run locally with Maven"

read -p "Enter your choice (1-4): " choice

case $choice in
    1)
        deploy_heroku
        ;;
    2)
        build_docker
        ;;
    3)
        deploy_docker_compose
        ;;
    4)
        echo "🏃‍♂️ Running locally with Maven..."
        mvn spring-boot:run
        ;;
    *)
        echo "❌ Invalid choice"
        exit 1
        ;;
esac
