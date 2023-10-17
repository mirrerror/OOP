package md.mirrerror.files;

import java.io.File;

public abstract class RepositoryCodeFile extends RepositoryFile {

    private File file;

    public RepositoryCodeFile(File file) {
        super(file);
        this.file = file;
    }

    public abstract int countMethods();

    public abstract int countClasses();

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }
}
