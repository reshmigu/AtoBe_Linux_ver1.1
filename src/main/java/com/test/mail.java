package com.test;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
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

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class mail {
	ExcelRead readExcel = new ExcelRead();
	private static final Logger LOGGER = LogManager.getLogger(mail.class);

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
				message.setSubject("AtoBe Xray Execution Report");
				BodyPart messageBodyPart1 = new MimeBodyPart();
				messageBodyPart1.setText("Please find attached Xray Execution Sample Report.\nThanks\nReshmi");
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

	public void sendEmailWithTemplate(final String subject, final List<String> emailToAddresses, String templatePath,
			VelocityContext context) {
		try {
			String host = "smtp.office365.com";
			Properties p = new Properties();
			p.setProperty("resource.loader", "class");
			p.setProperty("class.resource.loader.class",
					"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			Velocity.init(p);
			Template template = Velocity.getTemplate(templatePath, "UTF-8");
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(props,

					new javax.mail.Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {

							return new PasswordAuthentication(readExcel.getUserName(), readExcel.getPassword());

						}

					});

			String emails = null;

			// we create new message
			MimeMessage message = new MimeMessage(session);
			// set the from 'email address'
			message.setFrom(new InternetAddress("reshmi.g@thinkpalm.com"));
			// set email subject
			message.setSubject(subject);

			// message.setContent(writer.toString(), "text/html");
			message.setText(writer.toString(), "UTF-8", "html");
			// form all emails in a comma separated string
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (String email : emailToAddresses) {
				sb.append(email);
				i++;
				if (emailToAddresses.size() > i) {
					sb.append(", ");
				}
			}

			emails = sb.toString();

			// you can set also CC or TO for recipient type
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(sb.toString()));
			// send the email
			LOGGER.info("senting mails to " + emailToAddresses.toString());

			Transport.send(message);
			LOGGER.info("sent mails to " + emailToAddresses.toString());

		} catch (MessagingException e) {
			LOGGER.error("Exception inside MailUtil.sendEmail()" + e);
			e.printStackTrace();
		}

	}
}
