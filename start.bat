call docker stop sb3jwt
call docker rm sb3jwt
call docker rmi springboot:jwt
call mvn clean package
call docker compose -f docker-compose.yml up --build -d