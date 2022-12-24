import java.util.HashMap;

// Data structure of a file system
public class FileSystem {
    private final String file;
    private final FileSystem previous;
    private final HashMap<String, FileSystem> nextLayer = new HashMap<>();
    private int size;

    // Constructor
    public FileSystem(String fileSystemName, FileSystem previousFileSystem) {
        this.size = 0;
        this.file = fileSystemName;
        this.previous = previousFileSystem;
    }

    // Getters
    public int getSize() {
        return this.size;
    }

    public String getFile() {
        return file;
    }

    public FileSystem getPrevious() {
        return previous;
    }

    public HashMap<String, FileSystem> getNextLayer() {
        return nextLayer;
    }

    // Add a file system to the current file system layer
    public void addFileSystem(FileSystem fileSystem) {
        this.nextLayer.put(fileSystem.getFile(), fileSystem);
    }

    // Find a FileSystem in the next layer
    public FileSystem findFileSystem(String fileSystemName) {
        return this.nextLayer.get(fileSystemName);
    }

    // Delete a FileSystem from the next layer
    public void deleteFromFileSystem(String name) {
        this.nextLayer.remove(name);
    }

    // Change the size of the current fileSystem
    public void changeFileSystemSize(int size) {
        this.size = size;
    }
}