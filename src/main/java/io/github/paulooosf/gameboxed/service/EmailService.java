package io.github.paulooosf.gameboxed.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmailCadastro(String destinatario, String apelido) {
        try {
            MimeMessage mensagemCadastro = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject("Bem-vindo ao GameboXed!");

            StringBuilder email = new StringBuilder();
            email.append("<!DOCTYPE html>");
            email.append("<html lang=\"pt-BR\">");
            email.append("<head>");
            email.append("    <meta charset=\"UTF-8\">");
            email.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            email.append("    <title>Bem-vindo ao GameboXed!</title>");
            email.append("    <style>");
            email.append("        body {");
            email.append("            font-family: Arial, sans-serif;");
            email.append("            background-color: #f0f0f0;");
            email.append("            margin: 0;");
            email.append("            padding: 0;");
            email.append("        }");
            email.append("        .header {");
            email.append("            background-color: #C1FF72;");
            email.append("            color: #13171E;");
            email.append("            padding: 20px;");
            email.append("            text-align: center;");
            email.append("        }");
            email.append("        .content {");
            email.append("            background-color: #ffffff;");
            email.append("            font-size: 16px;");
            email.append("            padding: 30px;");
            email.append("            border-radius: 10px;");
            email.append("            margin: 40px auto;");
            email.append("            width: 80%;");
            email.append("            max-width: 600px;");
            email.append("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
            email.append("            text-align: center;");
            email.append("            color: #000000;");
            email.append("        }");
            email.append("        .token {");
            email.append("            display: inline-block;");
            email.append("            background-color: #C1FF72;");
            email.append("            color: #13171E;");
            email.append("            font-weight: bold;");
            email.append("            font-size: 20px;");
            email.append("            padding: 10px 20px;");
            email.append("            margin: 20px 0;");
            email.append("            border-radius: 8px;");
            email.append("            border: 2px solid #13171E;");
            email.append("        }");
            email.append("    </style>");
            email.append("</head>");
            email.append("<body>");
            email.append("    <div class=\"header\">");
            email.append("        <h1>Bem-vindo!</h1>");
            email.append("    </div>");
            email.append("    <div class=\"content\">");
            email.append("        <p>Olá, " + apelido + "!</p>");
            email.append("        <p>Estamos enviando esse e-mail pois seu cadastro no <strong>GameboXed</strong> foi realizado com sucesso.</p>");
            email.append("        <p>Divirta-se descobrindo novos jogos e avaliando suas experiências!</p>");
            email.append("        <p style=\"margin-top: 100px;\">Atenciosamente,</p>");
            email.append("        <p><strong>Equipe GameboXed</strong></p>");
            email.append("    </div>");
            email.append("</body>");
            email.append("</html>");
            helper.setText(email.toString(), true);
            mailSender.send(mensagemCadastro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarEmailRedefinirSenha(String destinatario, String token) {
        try {
            MimeMessage mensagemSenha = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagemSenha, true);
            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject("Código para redefinição de senha");

            StringBuilder email = new StringBuilder();
            email.append("<!DOCTYPE html>");
            email.append("<html lang=\"pt-BR\">");
            email.append("<head>");
            email.append("    <meta charset=\"UTF-8\">");
            email.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            email.append("    <title>Redefinição de Senha</title>");
            email.append("    <style>");
            email.append("        body {");
            email.append("            font-family: Arial, sans-serif;");
            email.append("            background-color: #f0f0f0;");
            email.append("            margin: 0;");
            email.append("            padding: 0;");
            email.append("        }");
            email.append("        .header {");
            email.append("            background-color: #C1FF72;");
            email.append("            color: #13171E;");
            email.append("            padding: 20px;");
            email.append("            text-align: center;");
            email.append("        }");
            email.append("        .content {");
            email.append("            background-color: #ffffff;");
            email.append("            font-size: 16px;");
            email.append("            padding: 30px;");
            email.append("            border-radius: 10px;");
            email.append("            margin: 40px auto;");
            email.append("            width: 80%;");
            email.append("            max-width: 600px;");
            email.append("            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
            email.append("            text-align: center;");
            email.append("            color: #000000;");
            email.append("        }");
            email.append("        .token {");
            email.append("            display: inline-block;");
            email.append("            background-color: #C1FF72;");
            email.append("            color: #13171E;");
            email.append("            font-weight: bold;");
            email.append("            font-size: 20px;");
            email.append("            padding: 10px 20px;");
            email.append("            margin: 20px 0;");
            email.append("            border-radius: 8px;");
            email.append("            border: 2px solid #13171E;");
            email.append("        }");
            email.append("    </style>");
            email.append("</head>");
            email.append("<body>");
            email.append("    <div class=\"header\">");
            email.append("        <h1>Redefinição de senha</h1>");
            email.append("    </div>");
            email.append("    <div class=\"content\">");
            email.append("        <p>Olá!</p>");
            email.append("        <p>Você solicitou a redefinição de senha da sua conta no GameboXed.</p>");
            email.append("        <p>Utilize o código abaixo para concluir a redefinição:</p>");
            email.append("        <div class=\"token\">" + token + "</div>");
            email.append("        <p>Se você não reconhece essa solicitação, ignore este e-mail ou verifique a segurança da sua conta.</p>");
            email.append("        <p>Obrigado,</p>");
            email.append("        <p><strong>Equipe GameboXed</strong></p>");
            email.append("    </div>");
            email.append("</body>");
            email.append("</html>");
            helper.setText(email.toString(), true);
            mailSender.send(mensagemSenha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
