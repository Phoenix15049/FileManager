import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class FileManager {
    public String Fileroot;


    public void file_create(String Filename , String FileType){
        String FileFullName = Filename + "." + FileType;
        try {
            FileWriter myWriter = new FileWriter(FileFullName);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void file_delete(String Filename , String FileType){
        String FileFullName = Filename + "." + FileType;
        File myObj = new File(FileFullName);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public void folder_create(String Path,String FolderName){
        String path = Path;
        //Using Scanner class to get the folder name from the user
        path = path+FolderName;
        //Instantiate the File class
        File f1 = new File(path);
        //Creating a folder using mkdir() method
        boolean bool = f1.mkdir();
        if(bool){
            System.out.println("Folder is created successfully");
        }else{
            System.out.println("Error Found!");
        }

    }

    public void folder_delete(String Path){
        File myObj = new File(Path);
        if (myObj.delete()) {
            System.out.println("Deleted the folder: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the folder.");
        }
    }

    public void move(File toDir, File currDir) {
        for (File file : currDir.listFiles()) {
            if (file.isDirectory()) {
                move(toDir, file);
            } else {
                file.renameTo(new File(toDir, file.getName()));
            }
        }
    }


}
