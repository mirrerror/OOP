package entities;

import java.io.File;
import java.time.LocalDate;

public class Repository {
    private final File directory;
    private LocalDate latestSnapshotDate;

    public Repository(File directory, LocalDate latestSnapshotDate) {
        this.directory = directory;
        this.latestSnapshotDate = latestSnapshotDate;
    }

    public void commit() {
        latestSnapshotDate = LocalDate.now();
    }

    public void printInfo(File file) {

    }

    public LocalDate getLatestSnapshotDate() {
        return latestSnapshotDate;
    }

    public void setLatestSnapshotDate(LocalDate latestSnapshotDate) {
        this.latestSnapshotDate = latestSnapshotDate;
    }

    public File getDirectory() {
        return directory;
    }
}
