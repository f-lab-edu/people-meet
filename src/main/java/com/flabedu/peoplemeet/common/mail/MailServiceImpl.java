package com.flabedu.peoplemeet.common.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public void sendHtmlMail(BaseMailInfo baseMailInfo) {
        mailSender.send((MimeMessagePreparator) mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            // To, From, Subject 설정
            if (baseMailInfo.getTo() != null) message.setTo(baseMailInfo.getTo());
            if (baseMailInfo.getFrom() != null) message.setFrom(baseMailInfo.getFrom());
            if (baseMailInfo.getSubject() != null) message.setSubject(baseMailInfo.getSubject());

            // HTML -> String 변환
            InputStream inputStream = this.getClass().getResourceAsStream(MAIL_TEMPLATE_LOCATION + baseMailInfo.getTemplateFileName() + ".html");
            String mailContent = new String(inputStream.readAllBytes());

            // HTML Parameter 세팅
            for (String key : baseMailInfo.getAttributes().keySet()) {
                mailContent = mailContent.replace("[" + key + "]", baseMailInfo.getAttribute(key));
            }

            message.setText(mailContent, true);
        });
    }
}

