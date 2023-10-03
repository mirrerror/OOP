package md.mirrerror.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private final File loggingFile;

    public Logger(File loggingFile) {
        this.loggingFile = loggingFile;
        initialize();
    }

    private void initialize() {
        try {
            if(!loggingFile.exists()) loggingFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String text) {
        log("info", text);
    }

    public void warning(String text) {
        log("warning", text);
    }

    public void error(String text) {
        log("error", text);
    }

    private void log(String type, String text) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(loggingFile, true));
            bw.write("[" + LocalDateTime.now() + "] " + type.toUpperCase() + ": " + text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getLoggingFile() {
        return loggingFile;
    }
}
