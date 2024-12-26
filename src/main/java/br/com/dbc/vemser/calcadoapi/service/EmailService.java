package br.com.dbc.vemser.calcadoapi.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class EmailService {

    @Value("${spring.mail.username}")
    private String remetente;

    private final freemarker.template.Configuration fmConfiguration;
    private final JavaMailSender emailSender;

    public EmailService(freemarker.template.Configuration fmConfiguration, JavaMailSender emailSender) {
        this.fmConfiguration = fmConfiguration;
        this.emailSender = emailSender;
    }

    public void enviarEmailCriacao(String destinatario, String assunto, String nomePessoa) {
        enviar(destinatario, assunto, nomePessoa, "email-criacao-template.ftl");
    }

    public void enviarEmailEdicao(String destinatario, String assunto, String nomePessoa) {
        enviar(destinatario, assunto, nomePessoa, "email-edicao-template.ftl");
    }

    public void enviarEmailExclusao(String destinatario, String assunto, String nomePessoa) {
        enviar(destinatario, assunto, nomePessoa, "email-exclusao-template.ftl");
    }

    private void enviar(String destinatario, String assunto, String nomePessoa, String pathTemplate) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(remetente);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject(assunto);
            mimeMessageHelper.setText(getConteudoTemplate(assunto, nomePessoa, pathTemplate), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
            log.info("E-mail enviado com sucesso para {}", destinatario);
        } catch (MessagingException | IOException | TemplateException e) {
            log.error("Erro ao enviar e-mail para {}: {}", destinatario, e.getMessage(), e);
        }
    }

    private String getConteudoTemplate(String assunto, String nomePessoa, String templateNome) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nomePessoa);
        dados.put("assunto", assunto);

        Template template = fmConfiguration.getTemplate(templateNome);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
    }
}
