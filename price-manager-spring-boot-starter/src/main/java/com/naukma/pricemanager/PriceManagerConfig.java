package com.naukma.pricemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(PriceManager.class)
@EnableConfigurationProperties(PriceManagerProperties.class)
public class PriceManagerConfig {

    @Autowired
    private PriceManagerProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public PriceManager priceManager() {
        return new PriceManager(this);
    }

    public PriceManagerProperties getProperties() {
        return properties;
    }
}
