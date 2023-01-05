import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class FileManager {
    public String FileRoot;

    public void PrintArray(String[] array){
        for (String i : array) {
            System.out.println(i);
        }
    }
    public void PrintArray(int[] array){
        for (int i : array) {
            System.out.println(i);
        }
    }



    public void file_create(String Path ,String Filename , String FileType){
        String FileFullName = Path +"\\"+ Filename + "." + FileType;
        try {
            FileWriter myWriter = new FileWriter(FileFullName);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void file_delete(String Path, String Filename , String FileType){
        String FileFullName = Path +"\\" +Filename + "." + FileType;
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
        currDir.delete();
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

    public void DateSorter(File dir) {
        File[] files = dir.listFiles();
        File[] sortedfiles = new File[files.length];
        String token ="";
        int tokennum = 0;
        int tokennums[] = new int[files.length];
        for(int i = 0 ; i<files.length ; i++){
            token = files[i].getName().split("\\.")[1];
            tokennum = Integer.parseInt(token);
            tokennums[i] = tokennum;
        }
        int unsortedTN[] = new int[files.length];
        for(int i = 0 ; i<files.length ; i++){
            unsortedTN[i] = tokennums[i];
        }

        Arrays.sort(tokennums);
        String counter = "";

        for(int i = 0 ; i<files.length ; i++){
            for( int j = 0 ; j<files.length ; j++){
               if(tokennums[i] == unsortedTN[j]){
                   String jc = "" + j;
                   if(!counter.contains(jc)){
                       counter += j+ ",";
                       break;
                   }
               }
            }
        }
        counter += "999";
        String[] indexcount = counter.split(",");
        int[] index = new int[files.length];
        for(int i = 0 ; i<files.length ; i++){
            index[i] = Integer.parseInt(indexcount[i]);
        }
        for(int i = 0 ; i<files.length ; i++){
            sortedfiles[i] = files[index[i]];
        }
        System.out.println("-- printing files before sort --");
        printFiles(files);

        System.out.println("-- printing files after sort --");
        printFiles(sortedfiles);
    }
    public void TypeSorter(File dir) {
        File[] files = dir.listFiles();
        File[] sortedfiles = new File[files.length];
        System.out.println("-- printing files before sort --");
        printFiles(files);
        String token = "";
        String[] types = new String[files.length];
        String[] unsortedtypes = new String[files.length];
        for(int i = 0 ; i<files.length ; i++){
            token = files[i].getName().split("\\.")[2];
            types[i] = token;
            unsortedtypes[i] = token;
        }

        Arrays.sort(types);

        String counter = "";

        for(int i = 0 ; i<files.length ; i++){
            for( int j = 0 ; j<files.length ; j++){
                if(types[i] == unsortedtypes[j]){
                    String jc = "" + j;
                    if(!counter.contains(jc)){
                        counter += j+ ",";
                        break;
                    }
                }
            }
        }
        counter += "999";

        String[] indexcount = counter.split(",");
        int[] index = new int[files.length];
        for(int i = 0 ; i<files.length ; i++){
            index[i] = Integer.parseInt(indexcount[i]);
        }
        for(int i = 0 ; i<files.length ; i++){
            sortedfiles[i] = files[index[i]];
        }

        System.out.println("-- printing files after sort --");
        printFiles(sortedfiles);
    }

    public void  NameSorter(File dir) {
        File[] files = dir.listFiles();
        System.out.println("-- printing files before sort --");
        printFiles(files);
        Arrays.sort(files);
        System.out.println("-- printing files after sort --");
        printFiles(files);
    }

    public void TypeCategorizer(String[] Types) {
        String FolderNames = "";
        for(int i = 0 ; i<Types.length ; i++){
            if((Objects.equals(Types[i], "png"))||(Objects.equals(Types[i], "jpg"))||(Objects.equals(Types[i], "jpeg"))||(Objects.equals(Types[i], "gif"))){
                FolderNames += "photo,";
            }else if((Objects.equals(Types[i], "mp4"))||(Objects.equals(Types[i], "avl"))||(Objects.equals(Types[i], "mkv"))||(Objects.equals(Types[i], "mov"))){
                FolderNames += "video,";
            }else if((Objects.equals(Types[i], "wav"))||(Objects.equals(Types[i], "aiff"))){
                FolderNames += "voice,";
            }else if(Objects.equals(Types[i], "txt")){
                FolderNames += "text,";
            }else if(Objects.equals(Types[i], "pdf")){
                FolderNames += "pdf,";
            }
        }
        FolderNames += "999";
        System.out.println(FolderNames);
    }

    public String[] TypeCollector(File dir){

        File[] files = dir.listFiles();
        String token = "";
        String[] types = new String[files.length];
        for(int i = 0 ; i<files.length ; i++){
            token = files[i].getName().split("\\.")[2];
            types[i] = token;
        }
        LinkedHashSet<String> RMsorted =
                new LinkedHashSet<String>(Arrays.asList(types));
        String[] STypes = RMsorted.toArray(new String[ RMsorted.size() ]);

        return STypes;

    }

    public void FolderSorter(File dir){

    }

}