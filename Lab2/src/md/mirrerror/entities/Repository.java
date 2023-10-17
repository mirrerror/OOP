package md.mirrerror.entities;

import md.mirrerror.Main;
import md.mirrerror.files.*;
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
            RepositoryFile repositoryFile = new RepositoryFile(file);
            RepositoryImageFile repositoryImageFile;
            RepositoryCodeFile repositoryCodeFile = null;

            String extension = repositoryFile.getExtension();
            BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);

            System.out.printf("Information about \"%s\":%nExtension: %s%nCreated at: %s%nUpdated at: %s%n",
                    file.getName(), extension, attributes.creationTime(), attributes.lastModifiedTime());

            if(extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
                repositoryImageFile = new RepositoryImageFile(file);
                int[] imageSize = repositoryImageFile.getImageDimensions();
                System.out.printf("Image dimensions: %dx%d%n", imageSize[0], imageSize[1]);
            }

            if(extension.equalsIgnoreCase("txt"))
                System.out.printf("Line count: %d%nWord count: %d%nCharacter count: %d%n",
                        repositoryFile.countLines(), repositoryFile.countWords(), repositoryFile.countCharacters());

            if(extension.equalsIgnoreCase("py")) repositoryCodeFile = new RepositoryPythonCodeFile(file);
            if(extension.equalsIgnoreCase("java")) repositoryCodeFile = new RepositoryJavaCodeFile(file);

            if(repositoryCodeFile != null)
                System.out.printf("Line count: %d%nClass count: %d%nMethod count: %d%n",
                        repositoryCodeFile.countLines(), repositoryCodeFile.countClasses(), repositoryCodeFile.countMethods());
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

    private Set<String> getCurrentFiles() {
        Set<String> currentFiles = new HashSet<>();
        for(File file : FileUtils.getAllFilesFromDirectory(directory)) currentFiles.add(file.getName());
        return currentFiles;
    }

    public Set<String> getCreatedFiles() {
        Set<String> currentFiles = getCurrentFiles();
        Set<String> lastFiles = new HashSet<>(latestSnapshotFiles);

        Set<String> newFiles = new HashSet<>();
        for(String file : currentFiles)
            if(!lastFiles.contains(file))
                newFiles.add(file);

        return newFiles;
    }

    public Set<String> getDeletedFiles() {
        Set<String> currentFiles = getCurrentFiles();
        Set<String> lastFiles = new HashSet<>(latestSnapshotFiles);

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
