import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{
    public static void main(String[] args) throws IOException, InterruptedException{
        ArrayList<String> history = new ArrayList <>();
        Scanner sys = new Scanner(System.in);
        String logfile = sys.nextLine();
        Process logger = Runtime.getRuntime().exec("E:\\bin\\java.exe \"-javaagent:E:\\IntelliJ IDEA Community Edition 2022.2.2\\lib\\idea_rt.jar=54127:E:\\IntelliJ IDEA Community Edition 2022.2.2\\bin\" -Dfile.encoding=UTF-8 -classpath \"E:\\CS4348Prjs\\CS 4348 Prj 1\\out\\production\\CS 4348 Prj 1\" Logger");
        Process encryption = Runtime.getRuntime().exec("E:\\bin\\java.exe \"-javaagent:E:\\IntelliJ IDEA Community Edition 2022.2.2\\lib\\idea_rt.jar=54127:E:\\IntelliJ IDEA Community Edition 2022.2.2\\bin\" -Dfile.encoding=UTF-8 -classpath \"E:\\CS4348Prjs\\CS 4348 Prj 1\\out\\production\\CS 4348 Prj 1\" Encryption");
        
        OutputStream logOutStream = logger.getOutputStream();
        InputStream encryptInStream = encryption.getInputStream();
        OutputStream encryptOutStream = encryption.getOutputStream();
        
        Scanner fromEncrypt = new Scanner(encryptInStream);
        PrintStream toEncrypt = new PrintStream(encryptOutStream);
        PrintStream toLog = new PrintStream(logOutStream);
        
        //Start logging in the new log file
        toLog.println(logfile);
        toLog.println("START Logging Started.");
        
        boolean trueFalse = true;
        
        while(trueFalse){
            //Display the menu
            System.out.println("---------------------------------------------------------");
            System.out.println("                           MENU");
            System.out.println("---------------------------------------------------------");
            System.out.println("   Password - Sets the password for encryption/decryption");
            System.out.println("   Encrypt  - Encrypts the provided string               ");
            System.out.println("   Decrypt  - Decrypts the provided string               ");
            System.out.println("   History  - Shows the history of the current run       ");
            System.out.println("   Quit     - Quits the program                          ");
            System.out.println("---------------------------------------------------------");
            
            String cmd = sys.nextLine();
            if(cmd.equalsIgnoreCase("password")){
                //Ask user if they want to use history
                System.out.println("Would you like to use history? (Y/N)");
                String historyInput = sys.nextLine();
                String passkey = " ";
                //If user selects yes, then display the history
                if(historyInput.equalsIgnoreCase("y") || historyInput.equalsIgnoreCase("yes")){
                    int y = 1;
                    System.out.println("---------------------------------------------------------");
                    System.out.println("                          HISTORY");
                    System.out.println("---------------------------------------------------------");
                    for(String i : history){
                        System.out.println(y++ + ". " + i);
                    }
                    //Add an extra entry, which upon being selected, brings the user back to entering string manually
                    int lastInt = y;
                    System.out.println(lastInt + ". (Exit History)");
                    
                    int historySelection = sys.nextInt();
                    if(historySelection != lastInt){
                        passkey = history.get(historySelection - 1);
                        sys.nextLine();
                    }else{
                        //Ask for the password manually
                        sys.nextLine();
                        System.out.print("Enter the password: ");
                        passkey = sys.nextLine();
                    }
                }else{
                    //Ask for the password for this session
                    System.out.print("Enter the password: ");
                    passkey = sys.nextLine();
                }
                //Log the password
                String passkeyLog = "SET_PASSWORD ▮▮▮▮▮▮";
                toLog.println(passkeyLog);
                
                //Set the password
                String encryptPassKey = "passkey " + passkey;
                toEncrypt.println(encryptPassKey);
                
                //Get result confirmation
                toEncrypt.flush();
                String fromEnRes = fromEncrypt.nextLine();
                System.out.println(fromEnRes);
                
                //Log result confirmation
                toLog.flush();
                toLog.println(fromEnRes + " Password set.");
            }
            else if(cmd.equalsIgnoreCase("encrypt")){
                //Ask user if they want to use history
                System.out.println("Would you like to use history? (Y/N)");
                String historyInput = sys.nextLine();
                String str = " ";
                //If user selects yes, then display the history
                if(historyInput.equalsIgnoreCase("y") || historyInput.equalsIgnoreCase("yes")){
                    int y = 1;
                    System.out.println("---------------------------------------------------------");
                    System.out.println("                          HISTORY");
                    System.out.println("---------------------------------------------------------");
                    for(String i : history){
                        System.out.println(y++ + ". " + i);
                    }
                    //Add an extra entry, which upon being selected, brings the user back to entering string manually
                    int lastInt = y;
                    System.out.println(lastInt + ". (Exit History)");
        
                    int historySelection = sys.nextInt();
                    if(historySelection != lastInt){
                        str = history.get(historySelection - 1);
                        sys.nextLine();
                    }else{
                        sys.nextLine();
                        System.out.print("Enter string to ENCRYPT: ");
                        str = sys.nextLine();
                    }
                }else{
                    //Ask for the string manually
                    System.out.print("Enter string to ENCRYPT: ");
                    str = sys.nextLine();
                }
                
                String passkey = "ENCRYPT " + str.toUpperCase();
                
                //Add encryption string to history
                history.add(str.toUpperCase());
                //Log the encrypt request
                toLog.println(passkey);
                //Send string to encryption program
                toEncrypt.println("encrypt " + str);
                
                //Display results of encryption
                toEncrypt.flush();
                String fromEnRes = fromEncrypt.nextLine();
                System.out.println(fromEnRes);
                
                //Log results
                toLog.flush();
                toLog.println(fromEnRes);
            }
            else if(cmd.equalsIgnoreCase("decrypt")){
                //Ask user if they want to use history
                System.out.println("Would you like to use history? (Y/N)");
                String historyInput = sys.nextLine();
                String str = " ";
                //If user selects yes, then display the history
                if(historyInput.equalsIgnoreCase("y") || historyInput.equalsIgnoreCase("yes")){
                    int y = 1;
                    System.out.println("---------------------------------------------------------");
                    System.out.println("                          HISTORY");
                    System.out.println("---------------------------------------------------------");
                    for(String i : history){
                        System.out.println(y++ + ". " + i);
                    }
                    //Add an extra entry, which upon being selected, brings the user back to entering string manually
                    int lastInt = y;
                    System.out.println(lastInt + ". (Exit History)");
        
                    int historySelection = sys.nextInt();
                    if(historySelection != lastInt){
                        str = history.get(historySelection - 1);
                        sys.nextLine();
                    }else{
                        sys.nextLine();
                        System.out.print("Enter string to DECRYPT: ");
                        str = sys.nextLine();
                    }
                }else{
                    //Ask for the string manually
                    System.out.print("Enter string to DECRYPT: ");
                    str = sys.nextLine();
                }
                
                String passkey = "DECRYPT " + str.toUpperCase();
    
                //Add decryption string to history
                history.add(str.toUpperCase());
                //Log the decryption request
                toLog.println(passkey);
                //Send the string to decryption program
                toEncrypt.println("decrypt " + str);
                
                //Display results of decryption
                toEncrypt.flush();
                String fromEnRes = fromEncrypt.nextLine();
                System.out.println(fromEnRes);
                
                //Log results
                toLog.flush();
                toLog.println(fromEnRes);
            }
            //Display the history recorded up to this point
            else if(cmd.equalsIgnoreCase("history")){
                int y = 1;
                System.out.println("---------------------------------------------------------");
                System.out.println("                          HISTORY");
                System.out.println("---------------------------------------------------------");
                for(String i : history){
                    System.out.println(y++ + ". " + i);
                }
                //Log history checking
                toLog.println("HISTORY Displayed");
            }
            else if(cmd.equalsIgnoreCase("quit")){
                //Send quit request to logger and encryption programs
                toLog.println("STOPPED Logging Stopped.");
                toLog.println(cmd.toUpperCase());
                toEncrypt.println(cmd.toUpperCase());
                System.out.println("Programs stopped, have a good day! :)");
                
                //End the while loop
                trueFalse = false;
            }
            else{
                //Display error for unknown commands
                System.out.println("ERROR Unknown command");
            }
        }
        //Flush the streams
        toLog.flush();
        toEncrypt.flush();
        
        //Close the programs
        toLog.close();
        toEncrypt.close();
        fromEncrypt.close();
    }
}