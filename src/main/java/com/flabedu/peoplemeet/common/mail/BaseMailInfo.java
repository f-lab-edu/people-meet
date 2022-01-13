package com.flabedu.peoplemeet.common.mail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Builder
public class BaseMailInfo {
    private String to;

    // properties 설정값 자동 세팅
    private String from;

    private String subject;

    private String templateFileName;

    @Setter(AccessLevel.NONE)
    private Map<String, String> attributes;

    public String getAttribute(String key) {
        return attributes.get(key);
    }

    public void setAttribute(String key, String value) {
        getAttributes().put(key, value);
    }

    public static BaseMailInfo createTemplateMailInfo(String to, String subject, String templateFileName, Map<String, String> attributes) {
        return BaseMailInfo.builder()
                .to(to)
                .subject(subject)
                .templateFileName(templateFileName)
                .attributes(attributes)
                .build();
    }

    public static BaseMailInfo createBaseMailInfo(String to, String subject, Map<String, String> attributes) {
        return BaseMailInfo.builder()
                .to(to)
                .subject(subject)
                .attributes(attributes)
                .build();
    }
}




