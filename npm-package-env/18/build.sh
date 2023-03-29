#!/bin/bash -ex

REGISTRY=maconomy-dev.artifactory.cphdev.deltek.com
IMAGE=build-env
TAG=${TAG:-development}
IMG_NAME=$REGISTRY/$IMAGE:$TAG

docker build --progress=plain -t $IMG_NAME .
