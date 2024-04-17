package io.github.coffee330501.sensitive.strategy;

@FunctionalInterface
public interface SensitiveStrategy {
    String apply(String originString);
}
