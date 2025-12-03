# !/usr/bin
./gradlew bootBuildImage
docker tag growth-backend:0.0.1-SNAPSHOT qq1187688895/growth-backend:0.0.1-SNAPSHOT
docker push qq1187688895/growth-backend:0.0.1-SNAPSHOT