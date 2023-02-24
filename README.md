# FileManager

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

```



## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
