docker build . -t sae_image -f Dockerfile --no-cache
docker run -p 9000:9000 sae_image