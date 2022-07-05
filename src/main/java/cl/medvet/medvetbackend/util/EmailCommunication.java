package cl.medvet.medvetbackend.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

public class EmailCommunication {

    public static int sendMail(String addressee, String matter, String body, List<String> pathName) {

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
        Message message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee));   //Se podrían añadir varios de la misma manera
            message.setSubject(matter);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (pathName.size() > 0){

                for ( String path : pathName ) {

                    messageBodyPart = new MimeBodyPart();
                    String fn = path;
                    DataSource source = new FileDataSource(fn);
                    messageBodyPart.setDataHandler(new DataHandler(source));

                    String[] nameForDocument = fn.split("/");

                    messageBodyPart.setFileName(nameForDocument[nameForDocument.length-1]);
                    multipart.addBodyPart(messageBodyPart);

                }


            }

            message.setContent(multipart);


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
