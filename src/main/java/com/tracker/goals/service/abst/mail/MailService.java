package com.tracker.goals.service.abst.mail;

import javax.validation.constraints.Email;

public interface MailService {

    void sendMessage();

    void sendConfirmationEmail(Email email);
}
