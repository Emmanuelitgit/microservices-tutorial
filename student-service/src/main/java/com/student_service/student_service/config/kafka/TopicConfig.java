package com.student_service.student_service.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic myTopic() {
        return new NewTopic("topic1", 1, (short) 1);
    }
}
