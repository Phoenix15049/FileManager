# Java FileManager

Basic functions needed to work with files in Java plus some additional features.

## Installation

Just download the file manager class and use its functions using the method I will explain.


## Usage
To use this program, after adding the file manager class, just call the function you need.
```Java

FileManager F = new FileManager();

// Creates a file in 'Path//Filename.Filetype' 
file_create(String Path ,String Filename , String FileType)

// Deletes the file in 'Path//Filename.Filetype' 
file_delete(String Path ,String Filename , String FileType)

// Creates a folder in 'Path//FolderName//' 
folder_create(String Path,String FolderName)

// Deletes the folder in 'Path//FolderName//'
folder_delete(String Path,String FolderName)

// Moves a folder from '//currDir' to '//toDir' directory
foldermove(File toDir, File currDir)

// Moves a file from '//sourcePath' to '//targetPath' (both path contains the file name and file type E.g targetpath = "C://testfile.jpg")
filemove(String sourcePath, String targetPath)

// It will print all files in the current directory ('files' is an array that is automatically filled by the FileManager class. To use, just enter 'files' in function)
printFiles(File[] files)

// It will sort files in the current directory by date of creation (Note : you can turn your String path to a File using this code : new File(String your path)  )
DateSorter(File dir)

// It will sort files in the current directory according to their type 
TypeSorter(File dir)

//It will sort files in the current directory according to their names (alphabet)
NameSorter(File dir)

// It prints the names of the files inside it in order
sorter(File dir)


```



## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
