import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;

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

    public void printFiles(File[] files) {
        for (File file : files) {
            long m = getFileCreationEpoch(file);
            Instant instant = Instant.ofEpochMilli(m);
            LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            System.out.println(date+" - "+file.getName());
        }
    }

    public void sortFilesByDateCreated (File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            public int compare (File f1, File f2) {
                long l1 = getFileCreationEpoch(f1);
                long l2 = getFileCreationEpoch(f2);
                return Long.valueOf(l1).compareTo(l2);
            }
        });
    }

    public long getFileCreationEpoch (File file) {
        try {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(),
                    BasicFileAttributes.class);
            return attr.creationTime()
                    .toInstant().toEpochMilli();
        } catch (IOException e) {
            throw new RuntimeException(file.getAbsolutePath(), e);
        }
    }
    public void sorter(File dir) {
        File[] files = dir.listFiles();
        System.out.println("-- printing files before sort --");
        printFiles(files);
        sortFilesByDateCreated(files);
        System.out.println("-- printing files after sort --");
        printFiles(files);
    }


}
