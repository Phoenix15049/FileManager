import java.io.IOException;
import java.io.FileWriter;
public class FileManager {
    public String Fileroot;


    public void file_create(String Filename , String FileType){
        String FileFullName = Filename + "." + FileType;
        try {
            FileWriter myWriter = new FileWriter(FileFullName);
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


}
