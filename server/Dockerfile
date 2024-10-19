# Bazowy obraz
FROM bitnami/kafka:3.7.0

# Utworzenie pliku JAAS
RUN echo 'broker.KafkaServer {' > /bitnami/kafka/config/broker_jaas.conf && \
    echo '  org.apache.kafka.common.security.scram.ScramLoginModule required' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '  username="kafka_admin"' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '  password="secret-admin";' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '};' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo 'controller.KafkaServer {' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '  org.apache.kafka.common.security.plain.PLainLoginModule required' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '  username="controller_user"' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '  password="bitnami"' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '  user_controller_user="bitnami";' >> /bitnami/kafka/config/broker_jaas.conf && \
    echo '};' >> /bitnami/kafka/config/broker_jaas.conf

# Utworzenie pliku konfiguracyjnego
RUN echo 'sasl.mechanism=SCRAM-SHA-256' >> /bitnami/kafka/config/temp_conf.properties && \
    echo 'sasl.enabled.mechanisms=SCRAM-SHA-256' >> /bitnami/kafka/config/temp_conf.properties && \
    echo 'sasl.mechanism.inter.broker.protocol=PLAIN' >> /bitnami/kafka/config/temp_conf.properties && \
    echo 'security.inter.broker.protocol=SASL_PLAINTEXT' >> /bitnami/kafka/config/temp_conf.properties  