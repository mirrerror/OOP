package md.mirrerror.files;

import java.io.File;

public abstract class RepositoryCodeFile extends TextRepositoryFile {
    public RepositoryCodeFile(File file) {
        super(file);
    }

    public abstract int countMethods();

    public abstract int countClasses();
}
