import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Challenge_2 {

    public static void main(String[] args) {
        // Runtime of the code
        long startTime = System.currentTimeMillis();

        // Creating the file system
        FileSystem fileSystem = createFileSystem(new File("input.txt"));

        // Going through the whole file system to get the smallest directory that can free up the needed space (which is 30,000,000 - used space)
        int result = fileSystemSearch(fileSystem, Integer.MAX_VALUE, 30000000 - (70000000 - fileSystem.getSize()));

        System.out.println("The smallest directory that would free up 30,000,000 is: " + result);

        long endTime = System.currentTimeMillis();
        System.out.println("Total runTime: " + (endTime - startTime) + "ms");
    }

    // Recursive way of getting the smallest directory that that can free up the needed space, going through the file system structure
    private static int fileSystemSearch(FileSystem fileSystem, int value, int neededSpace) {
        if(!fileSystem.getNextLayer().isEmpty()) {
            // Going through folders in the next layer
            for (Map.Entry fileSystems : fileSystem.getNextLayer().entrySet()) {
                return fileSystemSearch((FileSystem) fileSystems.getValue(), value, neededSpace);
            }
        } else {
            // Changing value only when the next layer is empty -> assuring the value is changed only once
            if (fileSystem.getSize() >= neededSpace && fileSystem.getSize() < value)
                value = fileSystem.getSize();

            // When a folder has been inspected, it is deleted from the file system until we reach the root folder ("/")
            if (fileSystem.getFile().equals("/")) {
                return value;
            } else {
                fileSystem.getPrevious().deleteFromFileSystem(fileSystem.getFile());
                return fileSystemSearch(fileSystem.getPrevious(), value, neededSpace);
            }
        }
        return value;
    }

    // Create a FileSystem from an input File
    private static FileSystem createFileSystem(File input) {
        try {
            Scanner scanner = new Scanner(input);

            // The initial FileSystem
            FileSystem fileSystem = new FileSystem("root", null);
            fileSystem.addFileSystem(new FileSystem("/", fileSystem));

            // Creating the FileSystem structure
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineParsed = line.split(" ");

                // Going through the first string encountered
                switch (lineParsed[0]) {
                    case "$" -> {
                        // Processing a command (only a cd is interesting there, since a ls result is recognizable -> not beginning with $)
                        if (lineParsed[1].equals("cd")) {
                            if (lineParsed[2].equals("..")) {
                                if (fileSystem != null && fileSystem.getPrevious() != null)
                                    fileSystem = fileSystem.getPrevious();
                            } else {
                                // Creating the associated FileSystem
                                if (fileSystem != null && fileSystem.findFileSystem(lineParsed[2]) != null)
                                    fileSystem = fileSystem.findFileSystem(lineParsed[2]);
                            }
                        }
                    }
                    case "dir" -> {
                        // Processing a ls content
                        if (fileSystem != null)
                            fileSystem.addFileSystem(new FileSystem(lineParsed[1], fileSystem));
                    }
                    default -> {
                        // Processing a new file size
                        if (fileSystem != null) {
                            fileSystem.changeFileSystemSize(fileSystem.getSize() + Integer.parseInt(lineParsed[0]));
                            FileSystem tempFileSystem = fileSystem;
                            while(!tempFileSystem.getFile().equals("/")){
                                tempFileSystem = tempFileSystem.getPrevious();
                                tempFileSystem.changeFileSystemSize(tempFileSystem.getSize() + Integer.parseInt(lineParsed[0]));
                            }
                        }
                    }
                }
            }
            scanner.close();

            // Going back to the root of the file structure
            while(!fileSystem.getFile().equals("/")) {
                fileSystem = fileSystem.getPrevious();
            }

            return fileSystem;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
