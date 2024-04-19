package io.github.coffee330501.sensitive.aop;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.coffee330501.sensitive.SensitiveManager;
import io.github.coffee330501.sensitive.SensitiveWrapper;
import io.github.coffee330501.sensitive.annotation.IgnoreSensitive;
import io.github.coffee330501.sensitive.annotation.Sensitive;
import io.github.coffee330501.sensitive.resolver.RequestMappingResolver;
import io.github.coffee330501.sensitive.util.AnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class JacksonSerializer extends JsonSerializer<String> {

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String filedValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.isNull(filedValue)) {
            jsonGenerator.writeNull();
            return;
        }

        RequestMappingResolver requestMappingResolver = SpringUtil.getBean(RequestMappingResolver.class);

        HandlerMethod handlerMethod = requestMappingResolver.resolve();
        if (Objects.isNull(handlerMethod)) {
            jsonGenerator.writeString(filedValue);
            return;
        }

        // 当前对象
        Object currentValue = jsonGenerator.getCurrentValue();
        String fieldName = jsonGenerator.getOutputContext().getCurrentName();
        Field currentField = ReflectUtil.getField(currentValue.getClass(), fieldName);
        Sensitive sensitive = currentField.getAnnotation(Sensitive.class);
        if (Objects.isNull(sensitive)) {
            jsonGenerator.writeString(filedValue);
            return;
        }

        // 忽略脱敏
        IgnoreSensitive ignoreSensitive = AnnotationUtil.getAnnotation(IgnoreSensitive.class,handlerMethod);
        if (!Objects.isNull(ignoreSensitive)) {
            jsonGenerator.writeString(filedValue);
            return;
        }

        // 脱敏
        String sensitiveStrategy = sensitive.value();
        String desensitizedString = SensitiveManager.sensitize(sensitiveStrategy, filedValue);
        log.debug("Sensitive for {} with {} strategy.", fieldName, sensitiveStrategy);
        jsonGenerator.writeString(desensitizedString);
    }
}
