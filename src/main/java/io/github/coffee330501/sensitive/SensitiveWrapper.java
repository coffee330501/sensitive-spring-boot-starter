package io.github.coffee330501.sensitive;

public class SensitiveWrapper {
    private Object object;
    private String filedName;
    private String filedValue;

    public SensitiveWrapper() {
    }

    public SensitiveWrapper(Object object, String filedName, String filedValue) {
        this.object = object;
        this.filedName = filedName;
        this.filedValue = filedValue;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledValue() {
        return filedValue;
    }

    public void setFiledValue(String filedValue) {
        this.filedValue = filedValue;
    }
}
