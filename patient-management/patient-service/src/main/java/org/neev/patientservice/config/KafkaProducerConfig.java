package org.neev.patientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * Configuration class for setting up Kafka producer components. This class defines a KafkaTemplate bean that can be
 * used to send messages to Kafka topics. The ProducerFactory is injected into the KafkaTemplate, allowing it to
 * create producer instances as needed.
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * Creates a KafkaTemplate bean for sending messages to Kafka topics. The KafkaTemplate is configured with a
     * ProducerFactory, which is responsible for creating producer instances that can send messages to Kafka.
     *
     * @param producerFactory the ProducerFactory used to create producer instances for the KafkaTemplate
     * @return a KafkaTemplate for sending messages to Kafka topics
     */
    @Bean
    KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
