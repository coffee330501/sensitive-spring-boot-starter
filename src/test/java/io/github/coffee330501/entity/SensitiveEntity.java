/*
 * Copyright lzhpo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.coffee330501.entity;


import io.github.coffee330501.sensitive.annotation.Sensitive;
import io.github.coffee330501.sensitive.strategy.SensitiveStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveEntity {

    @Sensitive(SensitiveStrategy.CHINESE_NAME)
    private String name;

    @Sensitive(SensitiveStrategy.ID_CARD_NUMBER)
    private String idCard;

    @Sensitive(SensitiveStrategy.FIXED_PHONE)
    private String fixedPhone;

    @Sensitive(SensitiveStrategy.MOBILE)
    private String mobilePhone;

    @Sensitive(SensitiveStrategy.ADDRESS)
    private String address;

    @Sensitive(SensitiveStrategy.EMAIL)
    private String email;

    @Sensitive(SensitiveStrategy.PASSWORD)
    private String password;

    @Sensitive(SensitiveStrategy.CAR_LICENSE)
    private String carLicense;

    @Sensitive(SensitiveStrategy.BANK_CARD_NUMBER)
    private String bankCard;

    private String ipv4;

    private String ipv6;

    private String firstMask;

    private String clearToNull;

    private String clearToEmpty;

    private String keepLength1;

    private String filterWords1;

    private String handler1;

    private String aes;

    private String des;

    private String base64;

    private String rsa;
}
