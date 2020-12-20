package com.mask.mask.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface RenewalNotificationService {
    public void sendRenewalNotification() throws IOException, MessagingException;
}
