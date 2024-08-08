# Use Confluent's Kafka Connect image as the base
FROM confluentinc/cp-kafka-connect:latest

# Switch to root user to install packages
USER root

# Install unzip using yum
RUN yum update -y && yum install -y unzip

# Set the working directory
WORKDIR /usr/share/java

# Download the Elasticsearch sink connector
RUN curl -O https://d2p6pa21dvn84.cloudfront.net/api/plugins/confluentinc/kafka-connect-elasticsearch/versions/11.1.0/confluentinc-kafka-connect-elasticsearch-11.1.0.zip && \
    unzip confluentinc-kafka-connect-elasticsearch-11.1.0.zip && \
    rm confluentinc-kafka-connect-elasticsearch-11.1.0.zip

# Copy the connect-standalone.properties file
COPY connect-standalone.properties /etc/kafka/connect-standalone.properties

# Copy the log4j.properties file
COPY log4j.properties /etc/cp-base-new/log4j.properties

# Switch back to the original user
USER appuser

# Expose the Kafka Connect REST port
EXPOSE 8888
