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
        for(String entry : Main.getRepository().getCreatedFiles()) {
            if(sentCreated.contains(entry)) continue;
            System.out.println("A new file has been created: " + entry);
            sentCreated.add(entry);
        }
        for(String entry : Main.getRepository().getDeletedFiles()) {
            if(sentDeleted.contains(entry)) continue;
            System.out.println("A file has been deleted: " + entry);
            sentDeleted.add(entry);
        }
        for(String entry : Main.getRepository().getModifiedFiles()) {
            if(sentModified.contains(entry)) continue;
            System.out.println("A file has been modified: " + entry);
            sentModified.add(entry);
        }
    }

    public Timer getFileCheckTimer() {
        return fileCheckTimer;
    }
}
