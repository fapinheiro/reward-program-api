# Build
docker image build -t fapinheiro/reward-program-api:1.0.0 .  

# Run
docker container run -d -p 8089:8089 --name reward-program-api --rm fapinheiro/reward-program-api:1.0.0
docker container run -p 8089:8089 --name reward-program-api --rm fapinheiro/reward-program-api:1.0.0

# Get inside
docker container run -it --rm fapinheiro/reward-program-api:1.0.0 /bin/bash
docker container exec -it containerid bash