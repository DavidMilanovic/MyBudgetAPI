package com.productdock.RBCAccountProject.config;

import com.productdock.RBCAccountProject.data.LegacyDataLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LegacyDataConfig {

    @Value("${legacy.data.load:false}")
    private boolean loadLegacyData;

    @Bean
    public CommandLineRunner loadLegacyData(LegacyDataLoader legacyDataLoader) {
        return args -> {
            if (loadLegacyData) {
                legacyDataLoader.load();
            }
        };
    }
}
