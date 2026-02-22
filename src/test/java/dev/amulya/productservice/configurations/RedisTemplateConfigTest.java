package dev.amulya.productservice.configurations;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class RedisTemplateConfigTest {

    @Test
    public void redisTemplateBeanHasFactorySet() {
        RedisConnectionFactory factory = Mockito.mock(RedisConnectionFactory.class);
        RedisTemplateConfig cfg = new RedisTemplateConfig();
        RedisTemplate<String, Object> rt = cfg.redisTemplate(factory);
        assertThat(rt).isNotNull();
        assertThat(rt.getConnectionFactory()).isEqualTo(factory);
    }
}

