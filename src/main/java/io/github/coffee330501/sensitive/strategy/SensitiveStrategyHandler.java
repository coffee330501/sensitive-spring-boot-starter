package io.github.coffee330501.sensitive.strategy;

@FunctionalInterface
public interface SensitiveStrategyHandler {
    String apply(String originString);
}
