package co.edu.unbosque.pokemonapp.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendWelcomeEmail(String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
		try {
			helper.setTo(to);
			helper.setSubject("Welcome Trainer");

			// Utiliza HTML para dar estilo al correo electrónico
			String htmlMsg = "<html><body style=\"font-family: Arial, sans-serif; background-color: #3498db;\">"
					+ "<h2>Welcome battle trainer</h2>" + "<p>thank you for joining us into this project</p>"
					+ "</body></html>";
			helper.setText(htmlMsg, true); // Indica que el texto es HTML

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendUpdateEmail(String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setSubject("Information updated");
			helper.setText(
					"Your information has been succesfully updated, if you don't see the changes please contact admin");

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			// Maneja la excepción apropiadamente
		}
	}

	public String recoverPasswordEmail(String to) {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(to);
			helper.setSubject("Recovering Password");
			String code = randomString();
			helper.setText("This is your code:\n" + code);
			javaMailSender.send(message);
			return code;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Email not found";

	}

	public void changedPassword(String to, String newPassword) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(to);
			helper.setSubject("New password changed");
			helper.setText("You have changed your password to: " + newPassword);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public int randomNumberInRange(int minimo, int maximo) {

		return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}

	public String randomString() {
		String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String cadena = "";
		int longitud = 20;
		for (int x = 0; x < longitud; x++) {
			int indiceAleatorio = randomNumberInRange(0, banco.length() - 1);
			char caracterAleatorio = banco.charAt(indiceAleatorio);
			cadena += caracterAleatorio;
		}
		return cadena;
	}

}
