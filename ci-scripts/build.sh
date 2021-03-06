#!/bin/bash

function git_global_settings() {
    git config user.userName ${USERNAME}
    git config user.email ${EMAIL}
}

function git_commit_and_push() {
    git --no-pager diff
    git add --all
    git commit -am "[ci-skip] version ${RELEASE_VERSION}.RELEASE"
    git tag -a "${RELEASE_VERSION}.RELEASE" -m "v${RELEASE_VERSION} tagged"
    git status
    git push --follow-tags http://${USERNAME}:${GIT_ACCESS_TOKEN}@gitlab.com/${GROUP_NAME}/${PROJECT}.git HEAD:${BRANCH}
}

function git_checkout() {
    git checkout ${BRANCH}
    git pull origin ${BRANCH}
}

function mvn_clean_verify() {
    mvn clean verify
}

function mvn_get_version() {
    echo $(mvn -q \
        -Dexec.executable=echo \
        -Dexec.args='${project.version}' \
        --non-recursive \
        exec:exec | cut -d "=" -f 2)
}

function mvn_increment_minor_version() {
    local version_major=$(echo $1 | cut -d "." -f 1)
    local version_patch=$(echo $1 | cut -d "." -f 2)
    local version_minor=$(echo $1 | cut -d "." -f 3)
    version_minor=`expr ${version_minor} + 1`
    echo "${version_major}.${version_patch}.${version_minor}"
}

function mvn_set_version() {
    mvn versions:set -DnewVersion=${RELEASE_VERSION}
    mvn versions:commit -B
}

function mvn_get_name() {
    echo $(mvn -q \
        -Dexec.executable=echo \
        -Dexec.args='${project.name}' \
        --non-recursive \
        exec:exec | cut -d "=" -f 2)
}

set -ex
USERNAME=bilalcaliskan
GROUP_NAME="vpnbeast/backend"
EMAIL=bilalcaliskan@protonmail.com
PROJECT=$(mvn_get_name)
CURRENT_VERSION=$(mvn_get_version)
RELEASE_VERSION=$(mvn_increment_minor_version ${CURRENT_VERSION})
BRANCH=master

git_checkout
mvn_set_version
mvn_clean_verify
git_global_settings
git_commit_and_push