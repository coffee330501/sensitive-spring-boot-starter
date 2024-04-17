package io.github.coffee330501.sensitive.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
public class RequestMappingResolver implements HandlerMethodResolver {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public RequestMappingResolver(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @Override
    public HandlerMethod resolve() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(this::getHandler)
                .map(HandlerExecutionChain::getHandler)
                .map(HandlerMethod.class::cast)
                .orElse(null);
    }

    private HandlerExecutionChain getHandler(HttpServletRequest httpServletRequest) {
        try {
            return requestMappingHandlerMapping.getHandler(httpServletRequest);
        } catch (Exception e) {
            log.error("Cannot get handler from current HttpServletRequest: {}", e.getMessage(), e);
            return null;
        }
    }


}
