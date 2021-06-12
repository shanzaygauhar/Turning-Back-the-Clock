import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.File;

//Date Format: MM/DD/YYYY
//24 Hrs Time HH:MM:SS
//Name of the file = filename.txt

public class setDate {

    public void SetDate(String newDate, String Time, String filename) throws IllegalArgumentException,ParseException,NullPointerException {
        //Setting our required dateFormat.
        //Conversion of String to Date Object using parsing
        SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        
            String ModifiedDate = newDate + " " + Time;

            //Gets Current Date
            Date now = new Date();
            dateformat.format(now);
            System.out.println("Now: " + now);

            //Checks  out of bound dates
            dateformat.setLenient(false);
		try{
            //using parse function to extract time and date from string
            Date modifiedDate = dateformat.parse(ModifiedDate);
            System.out.println("ModifiedDate: " + modifiedDate);

            //Before setting the date check if the ModifiedDate is not greater than current date.
            try{
                if(modifiedDate.compareTo(now) > 0)
                    throw new IllegalArgumentException("The inputted date is in future");
                //String Location = "D:\\Users\\Dell\\IdeaProjects\\L174236_A1_Q3\\src\\" + filename; //update this to make it work on your own.
                try {
                    File modifyFile = new File(filename);
                    if(modifyFile.exists()){
                        if(modifyFile.setLastModified(modifiedDate.getTime())){ //returns true if operation successful
                            System.out.println();
                            System.out.println(("Last Modified Date Set"));
                        }
                        else{
                            System.out.println("Last Modified Date Not Set");
                        }
                    }
                    else{
                        System.out.println("File does not exists");
                    }
                }catch(NullPointerException empty){ //if the filename path is null or incorrect
                    System.out.println("Failed to create File object not created");
                }
            }catch(IllegalArgumentException e){
                System.out.println(e);
                System.out.println("Program Terminating");

            }
        } catch (ParseException ex) {
            System.out.println("Failed to parse string to create Date object.\nPossible reasons");
            System.out.println("Incorrect Date Format");
            System.out.println("Incorrect Month or Day");
            System.out.println("Program Terminating");
        }
    }

    public static void main(String[] args) {
       //checks if number of arguments is 3 or not
        int numberOfArguments = args.length;
        if(numberOfArguments > 3){
            System.out.println("Warning: The number of arguments is greater than 3");
        }
        if(numberOfArguments >= 3){
            ReverseDate obj = new ReverseDate();
            try {
                obj.SetDate(args[0], args[1], args[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("The number of arguments should be 3");
            throw new IllegalArgumentException("The number of arguments should be 3");
        }
    }
}