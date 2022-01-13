package com.flabedu.peoplemeet.common.mail;

public interface MailService {

    final String MAIL_TEMPLATE_LOCATION = "/templates/mail/";

    void sendHtmlMail(BaseMailInfo mailMessageInfo);
}
