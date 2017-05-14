package org.zheng.convert.transfer;

import com.sun.deploy.util.StringUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Create by zxb on 2017/5/14
 */
public class StringToListFormatter implements Formatter<List> {

    @Override
    public List parse(String text, Locale locale) throws ParseException {
        return Arrays.asList(text.split(","));
    }

    @Override
    public String print(List object, Locale locale) {
        return StringUtils.join(object, ",");
    }
}
