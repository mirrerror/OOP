package md.mirrerror.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepositoryPythonCodeFile extends RepositoryCodeFile {

    private static final Pattern PYTHON_CLASS_PATTERN = Pattern.compile("^\\s*class\\s+\\w+.*:");
    private static final Pattern PYTHON_FUNCTION_PATTERN = Pattern.compile("^\\s*def\\s+\\w+\\s*\\(");

    public RepositoryPythonCodeFile(File file) {
        super(file);

        if(!getExtension().equalsIgnoreCase("py"))
            throw new IllegalArgumentException(getExtension() + " is not a python file.");
    }

    @Override
    public int countMethods() {
        int methodCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(super.getFile()))) {
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

        return -1;
    }

    @Override
    public int countClasses() {
        int classCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(super.getFile()))) {
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

        return -1;
    }

}
