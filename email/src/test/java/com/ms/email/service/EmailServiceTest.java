package com.ms.email.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.UUID;
import org.springframework.test.util.ReflectionTestUtils;

import com.ms.email.entity.EmailEntity;
import com.ms.email.enums.StatusEmail;
import com.ms.email.repositories.EmailRepository;

public class EmailServiceTest {
	@Mock
	private EmailRepository emailRepository;
	@Mock
    private JavaMailSender emailSender;
	@InjectMocks
	private EmailService emailService;
	
	@BeforeEach
	void setup() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(emailRepository, emailSender);
        ReflectionTestUtils.setField(emailService, "emailFrom", "noreply@test.com");
        }
	
	@Test
	void enviarEmailQuandoNaoExisteRegistro() {
		UUID userId = UUID.randomUUID();
		EmailEntity emailEntity = new EmailEntity();
			
		emailEntity.setUserId(userId);
        emailEntity.setEmailTo("destinatario@test.com");
        emailEntity.setSubject("Assunto");
        emailEntity.setText("Mensagem");
        
        when(emailRepository.findByUserId(userId)).thenReturn(Optional.empty());
        when(emailRepository.save(any(EmailEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        

        EmailEntity result = emailService.sendEmail(emailEntity);

        assertEquals(StatusEmail.SENT, result.getStatusEmail());
        assertEquals("noreply@test.com", result.getEmailFrom());
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
        verify(emailRepository, times(1)).save(any(EmailEntity.class));
	}
	
}
