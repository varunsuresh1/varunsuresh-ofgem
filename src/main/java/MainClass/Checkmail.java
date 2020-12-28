package MainClass;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class Checkmail {


    private boolean textIsHtml = false;


    private String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String) p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }
        if (p.isMimeType("multipart/alternative")) {
            Multipart mp = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }
        return null;
    }

    public boolean searchEmail(String userName, String password, final String subjectKeyword,
                               final String bodySearchText) throws IOException {
        Properties properties = new Properties();
        boolean val = false;
        // server setting
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", 993);
        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));
        Session session = Session.getDefaultInstance(properties);
        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);
            System.out.println("Connected to Email server�.");
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
            int MailCount = folderInbox.getMessageCount();
            // create a search term for all unseen messages
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            // create a search term for all recent messages
            Flags recent = new Flags(Flags.Flag.RECENT);
            FlagTerm recentFlagTerm = new FlagTerm(recent, false);
            SearchTerm searchTerm = new OrTerm(unseenFlagTerm, recentFlagTerm);
            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchTerm);

            for (int i = foundMessages.length; i > (foundMessages.length - MailCount); i--) {
                Message message = foundMessages[i - 1];
                message.getSubject();
                if (message.getSubject() == null) {
                    continue;
                }
                if (!message.getSubject().equals(subjectKeyword)) {
                    message.setFlag(Flags.Flag.FLAGGED, true);
                    Folder folderInbox1 = store.getFolder("Temp");
                    folderInbox.copyMessages(foundMessages, folderInbox1);
                }
                if (message.getSubject().equalsIgnoreCase(subjectKeyword)) {
                    //getText(message).matches(bodySearchText);
                    folderInbox.getMessageCount();
                    message.setFlag(Flags.Flag.DELETED, true);

                }
            }
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
        return val;
    }

    public void MailCheck() {
        String userName = "vsureshofgem@gmail.com";
        String password = "Password@123";
        Checkmail searcher = new Checkmail();
        String subjectKeyword = "Ofgem Automation Test";
        String bodySearchText = "Hello Ofgem";
        try {
            searcher.searchEmail(userName, password, subjectKeyword, bodySearchText);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
