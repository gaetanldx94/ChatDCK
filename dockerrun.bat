docker build . -t chatdck -f Dockerfile --no-cache
docker run -p 9000:9000 chatdck
