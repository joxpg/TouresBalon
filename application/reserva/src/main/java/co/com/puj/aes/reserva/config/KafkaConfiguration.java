/*
package co.com.puj.aes.reserva.config;

import co.com.puj.aes.reserva.entity.Reserva;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<String, Reserva> reservaConsumerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-ep9mm.us-east-2.aws.confluent.cloud:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "reserva");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
           return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                   new JsonDeserializer<>(Reserva.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory <String, Reserva> reservaKafkaListenerFactory () {
        ConcurrentKafkaListenerContainerFactory <String, Reserva> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reservaConsumerFactory());
        return factory;


    }
}
*/
