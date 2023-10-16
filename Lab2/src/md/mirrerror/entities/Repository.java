package md.mirrerror.entities;

import md.mirrerror.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    }

    public void printInfo(File file) {
        System.out.println("Information about \"" + file.getName() + "\":");
    }

    public void printStatus() {
        System.out.println("Created the snapshot at: " + latestSnapshotDateTime);
        try {
            Set<String> currentFiles = new HashSet<>();
            Set<String> lastFiles = new HashSet<>(latestSnapshotFiles);

            for(File file : FileUtils.getAllFilesFromDirectory(directory)) currentFiles.add(file.getName());

            Set<String> newFiles = new HashSet<>();
            for(String file : currentFiles)
                if(!lastFiles.contains(file))
                    newFiles.add(file);
            for(String entry : newFiles) System.out.println(entry + " - new file");

            Set<String> deletedFiles = new HashSet<>();
            for(String file : lastFiles)
                if(lastFiles.contains(file) && !currentFiles.contains(file))
                    deletedFiles.add(file);
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
