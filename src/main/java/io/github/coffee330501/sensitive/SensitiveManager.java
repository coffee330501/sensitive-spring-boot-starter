package io.github.coffee330501.sensitive;

import io.github.coffee330501.sensitive.strategy.SensitiveStrategies;
import io.github.coffee330501.sensitive.strategy.SensitiveStrategy;

import java.util.HashMap;
import java.util.Map;

public class SensitiveManager {
    private static final Map<String, SensitiveStrategy> processorMap = new HashMap<>();

    static {
        registerSensitiveProcessor("mobile", SensitiveStrategies.MOBILE_PROCESSOR);
    }

    /**
     * 注册脱敏策略
     *
     * @param sensitiveName     策略名称
     * @param sensitiveStrategy 脱敏策略
     */
    public static void registerSensitiveProcessor(String sensitiveName, SensitiveStrategy sensitiveStrategy) {
        processorMap.put(sensitiveName, sensitiveStrategy);
    }

    /**
     * 数据脱敏
     *
     * @param strategy     脱敏策略
     * @param originString 要脱敏的字符串
     * @return 脱敏后数据
     */
    public static String desensitize(String strategy, String originString) {
        SensitiveStrategy sensitiveStrategy = processorMap.get(strategy);
        if (sensitiveStrategy == null) {
            throw new RuntimeException("no such sensitive strategy [" + strategy + "]");
        }
        return sensitiveStrategy.apply(originString);
    }
}
