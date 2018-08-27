#!/bin/bash

KUBE_USERNAME=''
KUBE_PASSWD=''

HELM_IMAGE_TAG='913a96c'

docker run --rm -e KUBE_USERNAME=${KUBE_USERNAME} -e KUBE_PASSWD=${KUBE_PASSWD} -e CI_NAME=${CI_NAME} somashekar10/helm:${HELM_IMAGE_TAG} /root/check_ci.sh
