import java.io.File;

public class Main {
    public static void main(String[] args) {
        FileManager f = new FileManager();
        f.folder_create("G:\\","Folder");
        f.move(new File("G:\\Folder"), new File("G:\\Folder1"));




    }

}
