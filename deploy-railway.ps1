# Railway Deployment Script for Spring Boot Master Project
# This script helps deploy the Spring Boot application to Railway

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "Spring Boot Master - Railway Deployment" -ForegroundColor Cyan  
Write-Host "==================================" -ForegroundColor Cyan

# Check if Railway CLI is installed
Write-Host "`n1. Checking Railway CLI..." -ForegroundColor Yellow

if (Get-Command railway -ErrorAction SilentlyContinue) {
    Write-Host "✅ Railway CLI is installed" -ForegroundColor Green
} else {
    Write-Host "❌ Railway CLI not found. Installing..." -ForegroundColor Red
    Write-Host "Run this command to install Railway CLI:" -ForegroundColor Yellow
    Write-Host "npm install -g @railway/cli" -ForegroundColor White
    Write-Host "Or download from: https://docs.railway.app/develop/cli" -ForegroundColor White
    exit
}

# Login to Railway
Write-Host "`n2. Login to Railway..." -ForegroundColor Yellow
railway login

# Create or link to Railway project
Write-Host "`n3. Setting up Railway project..." -ForegroundColor Yellow
Write-Host "Choose an option:" -ForegroundColor Cyan
Write-Host "1. Create new Railway project" -ForegroundColor White
Write-Host "2. Link to existing project" -ForegroundColor White
$choice = Read-Host "Enter choice (1 or 2)"

if ($choice -eq "1") {
    railway init
} elseif ($choice -eq "2") {
    railway link
} else {
    Write-Host "Invalid choice. Please run the script again." -ForegroundColor Red
    exit
}

# Deploy to Railway
Write-Host "`n4. Deploying to Railway..." -ForegroundColor Yellow
Write-Host "This will build and deploy your Spring Boot application..." -ForegroundColor Cyan

railway up

Write-Host "`n==================================" -ForegroundColor Cyan
Write-Host "🚀 Deployment Complete!" -ForegroundColor Green
Write-Host "==================================" -ForegroundColor Cyan

# Get the deployment URL
Write-Host "`nGetting your application URL..." -ForegroundColor Yellow
$url = railway domain

if ($url) {
    Write-Host "`n🌐 Your application is live at:" -ForegroundColor Green
    Write-Host $url -ForegroundColor White
    
    Write-Host "`n📖 Access your application:" -ForegroundColor Cyan
    Write-Host "• Swagger UI: $url/api/v1/swagger-ui/index.html" -ForegroundColor White
    Write-Host "• API Health: $url/api/v1/actuator/health" -ForegroundColor White
    Write-Host "• User API: $url/api/v1/users" -ForegroundColor White
    Write-Host "• Metrics: $url/api/v1/actuator/metrics" -ForegroundColor White
} else {
    Write-Host "Run 'railway domain' to get your application URL" -ForegroundColor Yellow
}

Write-Host "`n🎉 Spring Boot Master Project is now live!" -ForegroundColor Green
