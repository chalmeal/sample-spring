package sample;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.jackson.ModelResolver;
import jakarta.annotation.PostConstruct;

/**
 * <pre>
 * Swagger設定クラス
 * </pre>
 */
@Configuration
public class SwaggerConfig {

    private final ObjectMapper objectMapper;

    public SwaggerConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        ModelConverters.getInstance().addConverter(new ModelResolver(objectMapper));
    }
}
