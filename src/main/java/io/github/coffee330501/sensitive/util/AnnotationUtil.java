package io.github.coffee330501.sensitive.util;

import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.Objects;

public class AnnotationUtil {
    public static <A extends Annotation> A getAnnotation(Class<A> clazz, HandlerMethod handlerMethod) {
        A annotation = handlerMethod.getMethodAnnotation(clazz);
        if (Objects.isNull(annotation)) {
            annotation = handlerMethod.getBeanType().getAnnotation(clazz);
        }
        return annotation;
    }
}
