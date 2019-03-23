package ua.slavik.carwash.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarWashConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
