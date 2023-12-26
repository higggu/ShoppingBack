package com.go.together.Util;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {

    static final String smtp_host = "smtp.gmail.com";
    static final int smtp_port = 465;  // TLS : 587, SSL : 465
//465는 일반적으로 SSL(Secure Sockets Layer)을 사용하는 SMTP(Simple Mail Transfer Protocol) 서버의 포트 번호입니다.
// SSL은 데이터를 암호화하여 안전한 통신을 제공하는 보안 프로토콜 중 하나입니다.

    public static void Send(String userEmail,String randomCode, String myEmail, String myPw) throws Exception {
        System.out.println("전송할 이메일 주소" + userEmail );
        System.out.println("id" + myEmail + "password" + myPw);
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", smtp_host);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail, myPw);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myPw));

            // 받는 이메일
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(userEmail)
            );

            // 제목
            message.setSubject("비밀번호 코드입니다..");

            // 내용
            message.setText("보안을 위해 비밀번호를 변경하는 메일입니다.\n\n " +
                    "무작위로 생성된 인증번호를 보내드렸습니다. 아래의 인증번호를 사용하여 비밀번호를 변경해주세요.\n.\n\n" +
                    "인증번호 : "+randomCode);

            // 발송
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}