#!/bin/sh

KUBE_USERNAME=''
KUBE_PASSWD=''

HELM_IMAGE_TAG='913a96c'
JENKINS_IMAGE_TAG='36669e9'

docker run --rm -e KUBE_USERNAME=${KUBE_USERNAME} -e KUBE_PASSWD=${KUBE_PASSWD} -e CI_NAME=${CI_NAME} somashekar10/helm:${HELM_IMAGE_TAG} /root/delete_ci.sh

#docker run --rm -v /Users/soruganti/Development/charts/stable/jenkins/values.yaml:/helm_values/values.yaml -e KUBE_USERNAME=${KUBE_USERNAME} -e KUBE_PASSWD=${KUBE_PASSWD} -e CI_NAME=test-ci1 -e ImageName=somashekar10/jenkins -e ImageTag=${JENKINS_IMAGE_TAG} -e LB_IP="104.198.103.181" -e GIT_ORG=test_org -e GIT_USER=test_user -e GIT_TOKEN=test_token somashekar10/helm:${HELM_IMAGE_TAG} /root/create_ci.sh

docker run --rm -e KUBE_USERNAME=${KUBE_USERNAME} -e KUBE_PASSWD=${KUBE_PASSWD} -e CI_NAME=${CI_NAME} -e ImageName=somashekar10/jenkins -e ImageTag=${JENKINS_IMAGE_TAG} -e LB_IP="104.198.103.181" -e GIT_ORG=${GIT_ORG} -e GIT_USER=${GIT_USER} -e GIT_TOKEN=${GIT_TOKEN} somashekar10/helm:${HELM_IMAGE_TAG} /root/create_ci.sh
