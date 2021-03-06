#!/bin/bash

function git_checkout() {
    git checkout ${BRANCH}
    git pull origin ${BRANCH}
}

function docker_login() {
    docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}
}

function docker_build_image() {
    echo "building docker image with maven..."
    mvn install dockerfile:build
}

function docker_tag_image() {
    echo "tagging builded image with the version info..."
    docker tag ${DOCKER_USERNAME}/${IMAGE}:latest ${DOCKER_USERNAME}/${IMAGE}:${RELEASE_VERSION}
}

function docker_push_image() {
    echo "pushing with version info..."
    docker push ${DOCKER_USERNAME}/${IMAGE}:${RELEASE_VERSION}
    echo "pushing with latest tag..."
    docker push ${DOCKER_USERNAME}/${IMAGE}:latest
}

function mvn_get_version() {
    echo $(mvn -q \
        -Dexec.executable=echo \
        -Dexec.args='${project.version}' \
        --non-recursive \
        exec:exec | cut -d "=" -f 2)
}

function mvn_get_name() {
    echo $(mvn -q \
        -Dexec.executable=echo \
        -Dexec.args='${project.name}' \
        --non-recursive \
        exec:exec | cut -d "=" -f 2)
}

set -ex
IMAGE=$(mvn_get_name)
RELEASE_VERSION=$(mvn_get_version)
BRANCH=master

git_checkout
docker_login
docker_build_image
docker_tag_image
docker_push_image