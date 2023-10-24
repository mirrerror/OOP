package md.mirrerror.tasks;

import md.mirrerror.Main;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class FileCheckTask extends TimerTask {

    private Timer fileCheckTimer;
    private final Set<String> sentCreated;
    private final Set<String> sentDeleted;
    private final Set<String> sentModified;

    public FileCheckTask() {
        sentCreated = new HashSet<>();
        sentModified = new HashSet<>();
        sentDeleted = new HashSet<>();
    }

    public void initialize() {
        fileCheckTimer = new Timer("RepositoryFilesCheckTimer");
        fileCheckTimer.schedule(this, 5000L, 5000L);
    }

    public void clearCache() {
        sentCreated.clear();
        sentDeleted.clear();
        sentModified.clear();
    }

    @Override
    public void run() {
        Main.getRepository().getCreatedFiles().stream()
                .filter(entry -> !sentCreated.contains(entry))
                .forEach(entry -> {
                    System.out.println("A new file has been created: " + entry);
                    sentCreated.add(entry);
                });

        Main.getRepository().getDeletedFiles().stream()
                .filter(entry -> !sentDeleted.contains(entry))
                .forEach(entry -> {
                    System.out.println("A file has been deleted: " + entry);
                    sentDeleted.add(entry);
                });

        Main.getRepository().getModifiedFiles().stream()
                .filter(entry -> !sentModified.contains(entry))
                .forEach(entry -> {
                    System.out.println("A file has been modified: " + entry);
                    sentModified.add(entry);
                });
    }

    public Timer getFileCheckTimer() {
        return fileCheckTimer;
    }
}
