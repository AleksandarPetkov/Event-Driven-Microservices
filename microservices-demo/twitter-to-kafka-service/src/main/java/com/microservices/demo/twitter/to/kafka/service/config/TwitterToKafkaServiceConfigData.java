package com.microservices.demo.twitter.to.kafka.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public class TwitterToKafkaServiceConfigData {
    private List<String> twitterKeyWords;
    private String welcomeMessage;

    public List<String> getTwitterKeyWords() {
        return twitterKeyWords;
    }

    public void setTwitterKeyWords(List<String> twitterKeyWords) {
        this.twitterKeyWords = twitterKeyWords;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
