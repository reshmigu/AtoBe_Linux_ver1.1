package com.test;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class mail {
	ExcelRead readExcel = new ExcelRead();

	public void mailm(String filePath) {
		String host = "smtp.office365.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		{

			Session session = Session.getDefaultInstance(props,

					new javax.mail.Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {

							return new PasswordAuthentication(readExcel.getUserName(), readExcel.getPassword());

						}

					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("reshmi.g@thinkpalm.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("reshmi.g@thinkpalm.com"));
				message.setSubject("AtoBe Sample Report");
				BodyPart messageBodyPart1 = new MimeBodyPart();
				messageBodyPart1.setText("Please find attached Execution Sample Report.\nThanks\nReshmi");
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();

				DataSource source = new FileDataSource(new File(filePath));
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(filePath);
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart1);
				multipart.addBodyPart(messageBodyPart2);
				message.setContent(multipart);
				Transport.send(message);
				System.out.println("Mail Sent Successfully");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		}
	}
}