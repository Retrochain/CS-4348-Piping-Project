import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Calendar;

public class Logger {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        //After getting the filename, create an editable file
        FileWriter fileWriter = new FileWriter(str);
        boolean tf = true;
        Calendar now = Calendar.getInstance();
        
        while(tf){
            //Take the next string to log in the file
            str = in.nextLine();
            //If the next string isn't quit, log the string in the file
            if(!str.equalsIgnoreCase("quit")){
                //Split the string into two, isolating the first word
                String[] arr = str.split(" ", 2);
                //Display the date, time and place the first word in bracket
                fileWriter.write(now.get(Calendar.YEAR) + "-" + now.get(Calendar.MONTH) + "-" + now.get(Calendar.DAY_OF_MONTH)
                        + " " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + " [" + arr[0] + "] " + arr[1] + "\n");
            } else {
                //If the string is quit, close the file and the loop
                fileWriter.close();
                tf = false;
            }
        }
    }
}
