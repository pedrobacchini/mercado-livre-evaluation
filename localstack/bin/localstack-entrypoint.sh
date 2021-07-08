#!/bin/bash

printf "Configuring localstack components"

printf "Creating Analysis..."

awslocal dynamodb create-table \
    --table-name Analysis \
    --attribute-definitions \
      "AttributeName=pk, AttributeType=S" \
      "AttributeName=sk, AttributeType=S" \
    --key-schema \
      "AttributeName=pk,KeyType=HASH" \
      "AttributeName=sk,KeyType=RANGE" \
    --global-secondary-indexes \
      "IndexName=SkIndex,KeySchema=[{AttributeName=sk,KeyType=HASH}],Projection={ProjectionType=ALL},ProvisionedThroughput={ReadCapacityUnits=10,WriteCapacityUnits=10}" \
    --provisioned-throughput \
      "ReadCapacityUnits=10,WriteCapacityUnits=10"

printf "Done create components"