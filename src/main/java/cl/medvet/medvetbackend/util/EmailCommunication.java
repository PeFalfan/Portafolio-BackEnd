package cl.medvet.medvetbackend.util;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailCommunication {

    public static int sendMail(String addressee, String matter, String body) {

        // portafolio.medvet@gmail.com
        // porta123456
        String sender = "portafolio.medvet";
        String password = "snaljmuxnpzunlpg";

        int response = 0;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", sender);
        properties.put("mail.smtp.clave", "porta123456");    //La clave de la cuenta
        properties.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        properties.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        properties.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee));   //Se podrían añadir varios de la misma manera
            message.setSubject(matter);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", sender, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            response = 1;

        }

        catch (Exception me) {
            me.printStackTrace();   //Si se produce un error
            response = 0;
        }

        return response;

    }
}
