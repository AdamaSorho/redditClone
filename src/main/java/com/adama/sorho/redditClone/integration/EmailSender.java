package com.adama.sorho.redditClone.integration;

import com.adama.sorho.redditClone.exception.SpringRedditException;
import com.adama.sorho.redditClone.model.NotificationEmail;
import com.adama.sorho.redditClone.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EmailSender {
	private final JavaMailSender javaMailSender;
	private final EmailContentBuilder emailContentBuilder;

	@Async
	public void send(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(AppUtils.EMAIL_SENDER);
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(emailContentBuilder.build(notificationEmail.getBody()));
		};

		try {
			javaMailSender.send(messagePreparator);
			log.info(AppUtils.ACTIVATION_EMAIL_SENT);
		} catch (MailException e) {
			throw new SpringRedditException(AppUtils.emailSendException(notificationEmail.getRecipient()));
		}
	}
}
