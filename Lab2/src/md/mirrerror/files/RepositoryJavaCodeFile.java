package md.mirrerror.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepositoryJavaCodeFile extends RepositoryCodeFile {

    private static final Pattern JAVA_METHOD_PATTERN = Pattern.compile("\\s*\\w+\\s+\\w+\\s*\\(.*\\)\\s*\\{");

    public RepositoryJavaCodeFile(File file) {
        super(file);

        if(!getExtension().equalsIgnoreCase("java"))
            throw new IllegalArgumentException(getExtension() + " is not a java file.");
    }

    @Override
    public int countMethods() {
        int methodCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(super.getFile()))) {
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

        return -1;
    }

    @Override
    public int countClasses() {
        int classCount = 0;
        boolean insideClass = false;

        try (BufferedReader br = new BufferedReader(new FileReader(super.getFile()))) {
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

        return -1;
    }
}
