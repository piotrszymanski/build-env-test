FROM node:18.14.2

ADD "https://artifactory.cphdev.deltek.com:443/artifactory/maconomy-generic/DeltekSubCA1-current.pem" /etc/ssl/certs/deltek-subca.pem
ADD "https://artifactory.cphdev.deltek.com:443/artifactory/maconomy-generic/DeltekUSRoot-current.pem" /etc/ssl/certs/deltek-root.pem

RUN cat /etc/ssl/certs/deltek-subca.pem /etc/ssl/certs/deltek-root.pem > /etc/ssl/deltek.pem

ENV NODE_EXTRA_CA_CERTS=/etc/ssl/deltek.pem

RUN apt update && apt install default-jre -y
