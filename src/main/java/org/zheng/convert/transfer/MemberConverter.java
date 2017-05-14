package org.zheng.convert.transfer;

import org.springframework.core.convert.converter.Converter;
import org.zheng.convert.model.Member;

public class MemberConverter implements Converter<String, Member> {

    @Override
    public Member convert(String source) {
        Member m=new Member();
        m.setName(source);
        return m;
    }

}
