package md.mirrerror.files;

import md.mirrerror.utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class GeneralRepositoryFile implements IRepositoryFile {

    private File file;

    public GeneralRepositoryFile(File file) {
        this.file = file;
    }

    public int countLines() {
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

    public int countWords() {
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

    public int countCharacters() {
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

    public String getExtension() {
        return FileUtils.getFileExtension(file);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
