package dev.amulya.productservice.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class AppConfigurationTest {

    @Test
    public void restTemplateBeanIsCreated() {
        AppConfiguration cfg = new AppConfiguration();
        RestTemplate rt = cfg.restTemplate();
        assertThat(rt).isNotNull();
    }
}

