package com.andimalik.learn;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String mailHost = "smtp.host.tld";
		String smtpPort = "465";
		final String mailUsername = "user1@host.tld";
		final String mailPassword = "user1password";

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", mailHost);
		properties.setProperty("mail.smtp.port", smtpPort);
		properties.put("mail.smtp.socketFactory.port", smtpPort);
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(mailUsername,
								mailPassword);
					}
				});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailUsername));

			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse("user2@host.tld,user3@host.tld"));

			message.addRecipients(Message.RecipientType.CC,
					InternetAddress.parse("user4@host.tld,user5@host.tld"));

			message.addRecipients(Message.RecipientType.BCC,
					"user6@host.tld,user7@host.tld");

			message.setSubject("Send e-Mail with JavaMail and JAF");
			message.setContent("<h1>HTML Header</h1><p>HTML paragraph.</p>",
					"text/html");

			Transport.send(message);
			System.out.println("Message sent.");
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}
	}
}
