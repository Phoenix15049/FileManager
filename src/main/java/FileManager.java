import javax.swing.text.Style;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        path = path+"\\"+FolderName;
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

    public void foldermove(File toDir, File currDir) {
        for (File file : currDir.listFiles()) {
            if (file.isDirectory()) {
                foldermove(toDir, file);
            } else {
                file.renameTo(new File(toDir, file.getName()));
            }
        }
        currDir.delete();
    }
    public boolean filemove(String sourcePath, String targetPath) {

        boolean fileMoved = true;

        try {

            Files.move(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {

            fileMoved = false;
            e.printStackTrace();
        }

        return fileMoved;
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

    public void FileList(File dir) {
        File[] files = dir.listFiles();
        for(File i : files){
            System.out.println(i.getName());
        }
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

    public String[] TypeCategorizer(String[] Types) {
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
        String[] temp = FolderNames.split(",");
        String[] Fnames = new String[temp.length -1];
        for(int i = 0 ; i < temp.length -1 ; i++){
            Fnames[i] = temp[i];
        }

        return  Fnames;
    }
    public String[] TypeCollect(File dir){
        File[] files = dir.listFiles();
        int arrsize = 0;
        String token = "";

        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0 ; i<files.length ; i++){
            try{
                token = files[i].getName().split("\\.")[2];
                arrsize ++;
                temp.add(token);

            }catch (ArrayIndexOutOfBoundsException e){

            }

        }
        String[] types = new String[arrsize];
        for(int i = 0 ; i<types.length ; i++){

            token = temp.get(i);
            types[i] = token;

        }
        return types;

    }

    public String[] TypeCollector(File dir){

        File[] files = dir.listFiles();
        int arrsize = 0;
        String token = "";

        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0 ; i<files.length ; i++){
            try{
                token = files[i].getName().split("\\.")[2];
                arrsize ++;
                temp.add(token);

            }catch (ArrayIndexOutOfBoundsException e){

            }

        }
        String[] types = new String[arrsize];
        for(int i = 0 ; i<types.length ; i++){

            token = temp.get(i);
            types[i] = token;



        }

        LinkedHashSet<String> RMsorted =
                new LinkedHashSet<String>(Arrays.asList(types));
        String[] STypes = RMsorted.toArray(new String[ RMsorted.size() ]);

        for(int i = 0 ; i < STypes.length ;i++){
            System.out.println(STypes[i]);
        }

        return STypes;

    }

    public String[] YearCollector(File dir){

        File[] files = dir.listFiles();
        int arrsize = 0;
        String token = "";
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0 ; i<files.length ; i++){
            try{
                token = files[i].getName().split("\\.")[1];
                arrsize ++;
                temp.add(token);

            }catch (ArrayIndexOutOfBoundsException e){

            }

        }

        String[] years = new String[arrsize];
        for(int i = 0 ; i<years.length ; i++){

            token = temp.get(i);
            years[i] = token;



        }

        LinkedHashSet<String> RMsorted =
                new LinkedHashSet<String>(Arrays.asList(years));
        String[] STypes = RMsorted.toArray(new String[ RMsorted.size() ]);

        return STypes;

    }

    public void FolderSorter(String path){
        File dir = new File(path);
        String[] folds = TypeCategorizer(TypeCollector(dir));
        String startpath  = path ;
        String endpath  = path ;
        for( int i = 0 ; i < folds.length ; i++){
            folder_create(path,folds[i]);
            endpath += "\\" + folds[i];
            System.out.println(folds[i]);
        }

    }

    public void CatSortEnd(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(int i = 0 ; i<files.length ; i++){

            if(files[i].getName().split("\\.").length == 3){
                String Types = files[i].getName().split("\\.")[2];
                String startpath = "";
                String endpath = "";
                if((Objects.equals(Types, "png"))||(Objects.equals(Types, "jpg"))||(Objects.equals(Types, "jpeg"))||(Objects.equals(Types, "gif"))){
                    startpath += path + "\\" + files[i].getName();
                    endpath += path + "\\" +"photo" +"\\" + files[i].getName();

                    filemove(startpath,endpath);
                }else if((Objects.equals(Types, "mp4"))||(Objects.equals(Types, "avl"))||(Objects.equals(Types, "mkv"))||(Objects.equals(Types, "mov"))){
                    startpath += path + "\\" + files[i].getName();
                    endpath += path + "\\" +"video" +"\\" + files[i].getName();

                    filemove(startpath,endpath);
                }else if((Objects.equals(Types, "wav"))||(Objects.equals(Types, "aiff"))){
                    startpath += path + "\\" + files[i].getName();
                    endpath += path + "\\" +"voice" +"\\" + files[i].getName();

                    filemove(startpath,endpath);
                }else if(Objects.equals(Types, "txt")){
                    startpath += path + "\\" + files[i].getName();
                    endpath += path + "\\" +"text" +"\\" + files[i].getName();

                    filemove(startpath,endpath);
                }else if(Objects.equals(Types, "pdf")){
                    startpath += path + "\\" + files[i].getName();
                    endpath += path + "\\" +"pdf" +"\\" + files[i].getName();

                    filemove(startpath,endpath);
                }


            }

        }

    }

    public void CatSorter(String path){
        File dir = new File(path);
        File[] years = dir.listFiles();
        String[] year = new String[years.length];
        for(int i = 0 ; i<years.length ; i++){
            year[i] = years[i].getName();
            FolderSorter(path+"\\"+year[i]);
        }

    }

    public void FolderCrawler(String path){

        File dir = new File(path);

        File[] files = dir.listFiles();

        for(int i = 0 ; i < files.length ; i++){
            String mainpath = "";
            mainpath = path+ "\\" + files[i].getName();
            System.out.println(mainpath);
            CatSortEnd(mainpath);
        }
    }

    public void YearCreator(String path){
        File dir = new File(path);
        String[] folds = YearCollector(dir);
        for( int i = 0 ; i < folds.length ; i++){
            folder_create(path,folds[i]);
            System.out.println(folds[i]);
        }
    }
    public void YearSorter(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        String[] years = YearCollector(new File(path));
        for(int i = 0 ; i < years.length ; i++){
            System.out.println(years[i]);
        }
        String token ="";
        int tokennum = 0;
        int tokennums[] = new int[files.length];
        ArrayList<File> mainfiles = new ArrayList<File>();
        for(int i = 0 ; i<files.length ; i++){
            if((files[i].getName().split("\\.").length == 3 )){
                mainfiles.add(files[i]);
            }
        }

        int size = mainfiles.size();
        for(int i = 0 ; i<size ; i++){
            System.out.println(mainfiles.get(i).getName());
        }

        for(int i = 0 ; i<size ; i++){
            token = mainfiles.get(i).getName().split("\\.")[1];
            System.out.println(token);
            for(int j = 0 ; j < years.length ; j++){
                if(Objects.equals(token, years[j])){
                    String startpath = path + "\\" + mainfiles.get(i).getName();
                    String endpath = path + "\\" + years[j] + "\\" + mainfiles.get(i).getName();
                    System.out.println(startpath);
                    System.out.println(endpath);
                    filemove(startpath,endpath);
                }
            }
        }
    }
}