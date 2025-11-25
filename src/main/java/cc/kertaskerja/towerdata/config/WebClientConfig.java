package cc.kertaskerja.towerdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient towerdataClient(
            WebClient.Builder builder,
            @Value("${towerdata.base-url:https://towerdata.kertaskerja.cc}") String baseUrl
    ) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }
}
