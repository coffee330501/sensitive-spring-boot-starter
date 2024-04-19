package io.github.coffee330501.sensitive;

import io.github.coffee330501.sensitive.strategy.SensitiveStrategy;
import io.github.coffee330501.sensitive.strategy.SensitiveStrategyHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SensitiveManager {
    private static final Map<String, SensitiveStrategyHandler> processorMap = new HashMap<>();

    static {
        registerSensitiveProcessor(SensitiveStrategy.MOBILE, SensitiveStrategy.MOBILE_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.FIXED_PHONE, SensitiveStrategy.FIXED_PHONE_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.ID_CARD_NUMBER, SensitiveStrategy.ID_CARD_NUMBER_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.CHINESE_NAME, SensitiveStrategy.CHINESE_NAME_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.ADDRESS, SensitiveStrategy.ADDRESS_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.EMAIL, SensitiveStrategy.EMAIL_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.PASSWORD, SensitiveStrategy.PASSWORD_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.CAR_LICENSE, SensitiveStrategy.CAR_LICENSE_PROCESSOR);
        registerSensitiveProcessor(SensitiveStrategy.BANK_CARD_NUMBER, SensitiveStrategy.BANK_CARD_PROCESSOR);
    }

    /**
     * 注册脱敏策略
     *
     * @param sensitiveName            策略名称
     * @param sensitiveStrategyHandler 脱敏策略
     */
    public static void registerSensitiveProcessor(String sensitiveName, SensitiveStrategyHandler sensitiveStrategyHandler) {
        processorMap.put(sensitiveName, sensitiveStrategyHandler);
    }

    /**
     * 数据脱敏
     *
     * @param strategy     脱敏策略
     * @param originString 要脱敏的字符串
     * @return 脱敏后数据
     */
    public static String sensitize(String strategy, String originString) {
        if (originString == null) return originString;

        SensitiveStrategyHandler sensitiveStrategyHandler = processorMap.get(strategy);
        if (sensitiveStrategyHandler == null) {
            throw new RuntimeException("no such sensitive strategy [" + strategy + "]");
        }
        return sensitiveStrategyHandler.apply(originString);
    }


}
