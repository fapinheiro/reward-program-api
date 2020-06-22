# Reward Program API

A REST Web-Service responsible of maintaining a database of the Reward Project. It uses JWT Authentication and runs with Docker.

# Key Features

- Timezone
- Pagination
- Lazy loading

# Build
docker image build -t fapinheiro/reward-program-api:x.x.x .

# Run
docker pull fapinheiro/reward-program-api:x.x.x

docker container run --rm -p 8089:8089 --name reward-program-api fapinheiro/reward-program-api:x.x.x

docker container run --rm -it -p 8089:8089 --name reward-program-api fapinheiro/reward-program-api:x.x.x

# Debug
docker container stop containerID

docker container exec -it containerID bash

docker container logs -f containerID


