package io.github.coffee330501.sensitive.strategy;

import io.github.coffee330501.sensitive.SensitiveConstant;

public class SensitiveStrategy {
    public static final String MOBILE = "mobile";
    public static final String FIXED_PHONE = "fixed_phone";
    public static final String ID_CARD_NUMBER = "id_card_number";
    public static final String CHINESE_NAME = "chinese_name";
    public static final String ADDRESS = "address";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CAR_LICENSE = "car_license";
    public static final String BANK_CARD_NUMBER = "bank_card_number";

    /**
     * 创建脱敏替换符字符串
     *
     * @param count 替换符的数量
     * @return 脱敏替换符号字符串
     */
    private static String createSensitiveReplacer(int count) {
        StringBuilder replacer = new StringBuilder();
        for (int i = 0; i < count; i++) {
            replacer.append(SensitiveConstant.REPLACER);
        }
        return replacer.toString();
    }

    /**
     * 字符串脱敏
     *
     * @param originString   需要被脱敏的字符串
     * @param keepFirstCount 起始保留的字符数
     * @param keepLastCount  尾部保留的字符数
     * @param maskCount      中间拼接的脱敏替换符号数
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
    public static SensitiveStrategyHandler MOBILE_PROCESSOR = originString -> {
        if (originString.startsWith("1") && originString.length() == 11) {
            return mask(originString, 3, 4, 4);
        }
        return originString;
    };

    /**
     * 固定电话脱敏
     * 保留前三后四，中间的为星号  "*"
     */
    public static SensitiveStrategyHandler FIXED_PHONE_PROCESSOR = data -> {
        if (data != null && data.length() > 5) {
            return mask(data, 3, 2, data.length() - 5);
        }
        return data;
    };


    /**
     * 身份证号脱敏处理器
     * 身份证号的保留前三后四，中间的数为星号  "*"
     */
    public static SensitiveStrategyHandler ID_CARD_NUMBER_PROCESSOR = data -> {
        if (data.length() >= 15) {
            return mask(data, 3, 4, data.length() - 7);
        }
        return data;
    };


    /**
     * 姓名脱敏
     */
    public static SensitiveStrategyHandler CHINESE_NAME_PROCESSOR = data -> {
        if (data.length() == 2) {
            return data.charAt(0) + "*";
        } else if (data.length() == 3) {
            return data.charAt(0) + "*" + data.charAt(2);
        } else if (data.length() == 4) {
            return "**" + data.substring(2, 4);
        } else if (data.length() > 4) {
            return mask(data, 2, 1, data.length() - 3);
        }
        return data;
    };


    /**
     * 地址脱敏
     */
    public static SensitiveStrategyHandler ADDRESS_PROCESSOR = data -> {
        if (data.length() > 6) {
            return mask(data, 6, 0, 3);
        } else if (data.length() > 3) {
            return mask(data, 3, 0, 3);
        }
        return data;
    };


    /**
     * email 脱敏
     */
    public static SensitiveStrategyHandler EMAIL_PROCESSOR = data -> {
        int indexOf = data.lastIndexOf("@");
        String email = data.substring(0, indexOf);

        if (email.length() == 1) {
            return "*" + data.substring(indexOf);
        } else if (email.length() == 2) {
            return "**" + data.substring(indexOf);
        } else if (email.length() < 5) {
            return mask(email, 2, 0, email.length() - 2) + data.substring(indexOf);
        } else {
            return mask(email, 3, 0, email.length() - 3) + data.substring(indexOf);
        }
    };


    /**
     * 密码 脱敏
     */
    public static SensitiveStrategyHandler PASSWORD_PROCESSOR = data -> mask(data, 0, 0, data.length());


    /**
     * 车牌号 脱敏
     */
    public static SensitiveStrategyHandler CAR_LICENSE_PROCESSOR = data -> mask(data, 3, 1, data.length() - 4);


    /**
     * 银行卡号 脱敏
     */
    public static SensitiveStrategyHandler BANK_CARD_PROCESSOR = data -> {
        if (data.length() >= 8) {
            int dataLen = data.length();
            // 保留后四位
            String reserve = data.substring(dataLen - 4, dataLen);
            String sensitiveReplacer = createSensitiveReplacer(4);
            return sensitiveReplacer + " " + sensitiveReplacer + " " + sensitiveReplacer + " " + reserve;
        }
        return data;
    };
}
