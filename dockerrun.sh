docker build . -t ChatDCK -f Dockerfile --no-cache
docker run -p 9000:9000 ChatDCK
