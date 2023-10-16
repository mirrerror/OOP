package md.mirrerror.entities;

import md.mirrerror.Main;
import md.mirrerror.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class Repository {
    private final File directory;
    private LocalDateTime latestSnapshotDateTime;
    private Set<String> latestSnapshotFiles;

    public Repository(File directory) {
        this.directory = directory;
        commit();
    }

    public void commit() {
        latestSnapshotDateTime = LocalDateTime.now();
        latestSnapshotFiles = new HashSet<>();
        for(File file : FileUtils.getAllFilesFromDirectory(directory)) latestSnapshotFiles.add(file.getName());
        Main.getFileCheckTask().clearCache();
    }

    public void printInfo(File file) {
        try {
            System.out.println("Information about \"" + file.getName() + "\":");

            String extension = FileUtils.getFileExtension(file);
            BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

            System.out.println("Extension: " + extension);
            System.out.println("Created at: " + attributes.creationTime());
            System.out.println("Updated at: " + attributes.lastModifiedTime());

            if(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
                int[] imageSize = FileUtils.getImageSize(file);
                System.out.println("Image dimensions: " + imageSize[0] + "x" + imageSize[1]);
            }

            if(extension.equalsIgnoreCase("txt")) {
                System.out.println("Line count: " + FileUtils.countLinesInFile(file));
                System.out.println("Word count: " + FileUtils.countWordsInFile(file));
                System.out.println("Character count: " + FileUtils.countCharactersInFile(file));
            }

            if(extension.equalsIgnoreCase("py") || extension.equalsIgnoreCase("java")) {
                System.out.println("Line count: " + FileUtils.countLinesInFile(file));
                System.out.println("Class count: " + FileUtils.countClassesInFile(file));
                System.out.println("Method count: " + FileUtils.countMethodsInFile(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStatus() {
        System.out.println("Created the snapshot at: " + latestSnapshotDateTime);
        try {
            Set<String> newFiles = getCreatedFiles();
            Set<String> deletedFiles = getDeletedFiles();

            for(String entry : newFiles) System.out.println(entry + " - new file");
            for(String entry : deletedFiles) System.out.println(entry + " - deleted file");

            for(File file : FileUtils.getAllFilesFromDirectory(directory)) {
                if(newFiles.contains(file.getName()) || deletedFiles.contains(file.getName())) continue;

                LocalDateTime fileLastModifiedDateTime =
                        LocalDateTime.ofInstant(Files.getLastModifiedTime(file.toPath()).toInstant(), ZoneId.systemDefault());
                System.out.println(file.getName() + " - " + ((fileLastModifiedDateTime.isAfter(latestSnapshotDateTime)) ? "modified" : "not modified"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getCreatedFiles() {
        Set<String> currentFiles = new HashSet<>();
        Set<String> lastFiles = new HashSet<>(latestSnapshotFiles);

        for(File file : FileUtils.getAllFilesFromDirectory(directory)) currentFiles.add(file.getName());

        Set<String> newFiles = new HashSet<>();
        for(String file : currentFiles)
            if(!lastFiles.contains(file))
                newFiles.add(file);

        return newFiles;
    }

    public Set<String> getDeletedFiles() {
        Set<String> currentFiles = new HashSet<>();
        Set<String> lastFiles = new HashSet<>(latestSnapshotFiles);

        for(File file : FileUtils.getAllFilesFromDirectory(directory)) currentFiles.add(file.getName());

        Set<String> deletedFiles = new HashSet<>();
        for(String file : lastFiles)
            if(lastFiles.contains(file) && !currentFiles.contains(file))
                deletedFiles.add(file);

        return deletedFiles;
    }

    public Set<String> getModifiedFiles() {
        Set<String> result = new HashSet<>();

        Set<String> newFiles = getCreatedFiles();
        Set<String> deletedFiles = getDeletedFiles();

        try {
            for(File file : FileUtils.getAllFilesFromDirectory(directory)) {
                if(newFiles.contains(file.getName()) || deletedFiles.contains(file.getName())) continue;

                LocalDateTime fileLastModifiedDateTime =
                        LocalDateTime.ofInstant(Files.getLastModifiedTime(file.toPath()).toInstant(), ZoneId.systemDefault());
                if(fileLastModifiedDateTime.isAfter(latestSnapshotDateTime)) result.add(file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public LocalDateTime getLatestSnapshotDateTime() {
        return latestSnapshotDateTime;
    }

    public void setLatestSnapshotDateTime(LocalDateTime latestSnapshotDateTime) {
        this.latestSnapshotDateTime = latestSnapshotDateTime;
    }

    public File getDirectory() {
        return directory;
    }
}
