import java.util.Scanner;

public class Encryption {
    public static String encrypt(String encryptText, String key){
        char[] encryptArr = encryptText.toCharArray();
        char[] newKeyArr = new char[encryptArr.length];
        char[] encryptedMsg = new char[encryptArr.length];
        int j = 0;
    
        //Set the key to the same length as the encryption text
        for(int i = 0; i < encryptArr.length; i++) {
            if(j == key.length( )) {
                j = 0;
            }
            newKeyArr[ i ] = key.charAt(j);
            j++;
        }
    
        //Encrypt the string using extended key
        for(int c = 0; c < encryptArr.length; c++){
            encryptedMsg[c] = (char) (((encryptArr[c] + newKeyArr[c]) % 26) + 'A');
        }
    
        //Return the new encrypted string
        return new String(encryptedMsg);
    }
    
    public static String decrypt(String decryptText, String key){
        char[] decryptArr = decryptText.toCharArray();
        char[] newKeyArr = new char[decryptArr.length];
        char[] decryptedMsg = new char[decryptArr.length];
        int j = 0;
        
        //Set the key to the same length as the decryption text
        for(int i = 0; i < decryptArr.length; i++) {
            if(j == key.length( )) {
                j = 0;
            }
            newKeyArr[ i ] = key.charAt(j);
            j++;
        }
        
        //Decrypt the string using extended key
        for(int c = 0; c < decryptArr.length; c++){
            decryptedMsg[c] = (char) ((((decryptArr[c] - newKeyArr[c]) + 26) % 26) + 'A');
        }
        
        //Return the new decrypted string
        return new String(decryptedMsg);
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String passkey = " ";
        boolean x = true;
        
        while(x) {
            //Get the input from the user
            String cmds = in.nextLine();
            //Split the input into two parts, isolating the first word
            String[] arr = cmds.split(" ", 2);
            
            //If the command isnt to quit
            if(! arr[0].equalsIgnoreCase("quit")) {
                //If asked, set the password
                if(arr[0].equalsIgnoreCase("passkey")) {
                    passkey = arr[1].toUpperCase();
                    System.out.println("RESULT");
                }//If asked, encrypt the string
                else if(arr[0].equalsIgnoreCase("encrypt")) {
                    //If passkey is not set, display error
                    if(passkey.charAt(0) == ' ') {
                        System.out.println("ERROR Password not set");
                    }//Else encrypt the string using the encryption function
                    else{
                        String encryptStr = arr[1].toUpperCase();
                        System.out.println("RESULT " + encrypt(encryptStr, passkey));
                    }
                }//If asked, decrypt the string
                else if(arr[0].equalsIgnoreCase("decrypt")) {
                    //If passkey is not set, display error
                    if(passkey.charAt(0) == ' ') {
                        System.out.println("ERROR Password not set");
                    }//Else decrypt the string using the decryption function
                    else{
                        String decryptStr = arr[1].toUpperCase();
                        System.out.println("RESULT " + decrypt(decryptStr, passkey));
                    }
                }//Else display error as the command doesn't exist
                else{
                    System.out.println( "ERROR Command not found" );
                }
            }//If command is quit, end the while loop
            else{
                x = false;
            }
        }
    }
}
