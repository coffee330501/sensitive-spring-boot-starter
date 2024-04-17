package io.github.coffee330501.sensitive.strategy;

import io.github.coffee330501.sensitive.SensitiveConstant;

public class SensitiveStrategies {
    /**
     * 创建脱敏替换符字符串
     * @param count 替换符的数量
     * @return 脱敏替换符号字符串
     */
    private static String createSensitiveReplacer(int count) {
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < count; i++) {
            mask.append(SensitiveConstant.REPLACER);
        }
        return mask.toString();
    }

    /**
     * 字符串脱敏
     * @param originString 需要被脱敏的字符串
     * @param keepFirstCount 起始保留的字符数
     * @param keepLastCount 尾部保留的字符数
     * @param maskCount 中间拼接的脱敏替换符号数
     * @return 脱敏后字符串
     */
    private static String mask(String originString, int keepFirstCount, int keepLastCount, int maskCount) {
        return originString.substring(0, keepFirstCount)
                + createSensitiveReplacer(maskCount)
                + originString.substring(originString.length() - keepLastCount);
    }

    /**
     * 手机号脱敏
     */
   public static SensitiveStrategy MOBILE_PROCESSOR = originString -> {
        if (originString.startsWith("1") && originString.length() == 11) {
            return mask(originString, 3, 4, 4);
        }
        return originString;
    };
}
