package org.com.dm.util;
import javax.mail.Authenticator; 
import javax.mail.PasswordAuthentication; 

//code for MyAuthenticator class 

public class MailAuthenticator extends Authenticator 
{ 
@Override 
protected PasswordAuthentication getPasswordAuthentication() 
{ 
return new PasswordAuthentication("magia.software@gmail.com", 
"xxx"); 
} 
}
