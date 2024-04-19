package io.github.coffee330501.sensitive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWrapper {
    private Object object;
    private String filedName;
    private String filedValue;
}
