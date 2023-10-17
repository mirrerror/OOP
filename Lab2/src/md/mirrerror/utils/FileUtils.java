package md.mirrerror.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
