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

    private static final Pattern PYTHON_CLASS_PATTERN = Pattern.compile("^\\s*class\\s+\\w+.*:");
    private static final Pattern PYTHON_FUNCTION_PATTERN = Pattern.compile("^\\s*def\\s+\\w+\\s*\\(");
    private static final Pattern JAVA_METHOD_PATTERN = Pattern.compile("\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{");

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

    public static int[] getImageSize(File file) {
        int[] size = new int[2];

        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                size[0] = image.getWidth();
                size[1] = image.getHeight();
            } else {
                System.out.println("Failed to read the image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size;
    }

    public static int countLinesInFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;
            while (br.readLine() != null) {
                lineCount++;
            }
            return lineCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int countWordsInFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int wordCount = 0;

            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);
                wordCount += tokenizer.countTokens();
            }

            return wordCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int countCharactersInFile(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            int characterCount = 0;

            while (fileReader.read() != -1) {
                characterCount++;
            }

            return characterCount;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int countClassesInFile(File file) {
        int classCount = 0;

        if(getFileExtension(file).equalsIgnoreCase("py")) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    Matcher matcher = PYTHON_CLASS_PATTERN.matcher(line);
                    if (matcher.find()) {
                        classCount++;
                    }
                }

                return classCount;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(getFileExtension(file).equalsIgnoreCase("java")) {
            boolean insideClass = false;

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    if (line.contains("class ")) {
                        classCount++;
                        insideClass = true;
                    } else if (insideClass && line.contains("{")) {
                        int openBraceCount = 1;
                        int closeBraceCount = 0;

                        while (openBraceCount != closeBraceCount) {
                            line = br.readLine();
                            if (line == null)
                                break;

                            if (line.contains("{"))
                                openBraceCount++;

                            if (line.contains("}"))
                                closeBraceCount++;
                        }

                        insideClass = false;
                    }
                }

                return classCount;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }

    public static int countMethodsInFile(File file) {
        int methodCount = 0;

        if(getFileExtension(file).equalsIgnoreCase("py")) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    Matcher matcher = PYTHON_FUNCTION_PATTERN.matcher(line);
                    if (matcher.find()) {
                        methodCount++;
                    }
                }

                return methodCount;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(getFileExtension(file).equalsIgnoreCase("java")) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    Matcher matcher = JAVA_METHOD_PATTERN.matcher(line);
                    if (matcher.find()) {
                        methodCount++;
                    }
                }

                return methodCount;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }
}
