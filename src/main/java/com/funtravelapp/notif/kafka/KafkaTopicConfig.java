package com.funtravelapp.notif.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic createNotif(){
        return TopicBuilder.name("CreateNotif").build();
    }

    @Bean
    public NewTopic updateNotifStatus(){
        return TopicBuilder.name("UpdateNotifStatus").build();
    }
}
