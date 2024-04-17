package io.github.coffee330501.sensitive.resolver;

import org.springframework.web.method.HandlerMethod;

public interface HandlerMethodResolver {
    HandlerMethod resolve();
}
