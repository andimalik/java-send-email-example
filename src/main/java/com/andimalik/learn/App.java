package com.andimalik.learn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
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
		try {
			Properties properties = App.loadMailConfigProperties();

			final String mailUsername = properties.getProperty("mail.username");
			final String mailPassword = properties.getProperty("mail.password");

			Session session = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(mailUsername,
									mailPassword);
						}
					});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailUsername));

			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse("user2@host.tld,user3@host.tld"));

			message.addRecipients(Message.RecipientType.CC,
					InternetAddress.parse("user4@host.tld,user5@host.tld"));

			message.addRecipients(Message.RecipientType.BCC,
					"user6@host.tld,user7@host.tld");

			message.setSubject("A Subject");
			message.setContent("<h1>A Title</h1><p>A paragraph.</p>",
					"text/html");

			Transport.send(message);
			System.out.println("Message sent.");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static Properties loadMailConfigProperties() throws IOException {
		InputStream inputFile = new FileInputStream(
				"src/main/resources/mail.cfg.properties");
		Properties properties = new Properties();

		properties.load(inputFile);

		return properties;
	}
}
