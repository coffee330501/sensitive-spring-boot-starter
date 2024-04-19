package io.github.coffee330501.sensitive;

import io.github.coffee330501.sensitive.resolver.RequestMappingResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class SensitiveAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RequestMappingResolver handlerMethodServletParser(
            @Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping requestMappingHandlerMapping) {
        return new RequestMappingResolver(requestMappingHandlerMapping);
    }
}
