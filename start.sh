docker stop sb3jwt
docker rm sb3jwt
docker rmi springboot:jwt
mvn clean package -DskipTests=true
docker compose -f docker-compose.yml up --build -d