package org.zheng.convert.transfer;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * 自定义类型转换器，非线程安全
 */
public class ListTypeEditSupport extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] aNames = text.split(",");
        setValue(Arrays.asList(aNames));
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
