package com.smart.services;

import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.websocket.*;


@Service
public class EmailServices {

	public void sendEmail(String message, String subject, String to, String from)
	{
		//Variable for gmail
				String host="smtp.gmail.com";
				
				//get the system properties
				Properties properties = System.getProperties();
				System.out.println("PROPERTIES "+properties);
				
				//setting important information to properties object
				
				//host set
				properties.put("mail.smtp.host", host);
				properties.put("mail.smtp.port","465");
				properties.put("mail.smtp.ssl.enable","true");
				properties.put("mail.smtp.auth","true");
				
				//Step 1: to get the session object..
				Session session=Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {				
						return new PasswordAuthentication("techsoftindia2018@gmail.com", "*******");
					}
					
					
					
				});
				
				session.setDebug(true);
				
				//Step 2 : compose the message [text,multi media]
				MimeMessage m = new MimeMessage(session);
				
				try {
				
				//from email
				m.setFrom(from);
				
				//adding recipient to message
				m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				
				//adding subject to message
				m.setSubject(subject);
			
				
				//attachement..
				
				//file path
				String path="C:\\Users\\user\\Desktop\\ca_logo.png";
				
				
				MimeMultipart mimeMultipart = new MimeMultipart();
				//text
				//file
				
				MimeBodyPart textMime = new MimeBodyPart();
				
				MimeBodyPart fileMime = new MimeBodyPart();
				
				try {
					
					textMime.setText(message);
					
					File file=new File(path);
					fileMime.attachFile(file);
					
					
					mimeMultipart.addBodyPart(textMime);
					mimeMultipart.addBodyPart(fileMime);
				
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
				m.setContent(mimeMultipart);
				
				
				//send 
				
				//Step 3 : send the message using Transport class
				Transport.send(m);
				
				
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
				
				
				
			
				System.out.println("Sent success...................");
	}
}
