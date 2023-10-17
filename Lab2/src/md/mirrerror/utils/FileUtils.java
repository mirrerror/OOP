package md.mirrerror.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {

    public static Set<File> getAllFilesFromDirectory(File directory) {
        Set<File> result = new HashSet<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory.toPath())) {
            for (Path file : stream) result.add(file.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getFileExtension(File file) {
        String[] splittedFileName = file.getName().split("\\.");
        return splittedFileName[splittedFileName.length - 1];
    }

}
