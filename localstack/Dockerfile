FROM localstack/localstack:1.0.3

COPY bin /docker-entrypoint-initaws.d

RUN chmod +x /docker-entrypoint-initaws.d/localstack-entrypoint.sh

RUN sed -i -e 's/\r$//' /docker-entrypoint-initaws.d/localstack-entrypoint.sh