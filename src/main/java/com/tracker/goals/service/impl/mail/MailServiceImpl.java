package com.tracker.goals.service.impl.mail;

import com.tracker.goals.service.abst.mail.MailService;
import com.tracker.goals.service.abst.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final UserService userService;

    @Override
    @Scheduled(cron = "0 30 17 * * *")
    public void sendMessage() {

    }

    @Override
    public void sendConfirmationEmail(Email email) {

    }
}
