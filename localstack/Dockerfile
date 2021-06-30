FROM localstack/localstack:0.12.10

COPY bin /docker-entrypoint-initaws.d

RUN chmod +x /docker-entrypoint-initaws.d/localstack-entrypoint.sh

RUN sed -i -e 's/\r$//' /docker-entrypoint-initaws.d/localstack-entrypoint.sh