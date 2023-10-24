package md.mirrerror.files;

import md.mirrerror.utils.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RepositoryImageFile implements IRepositoryFile {

    private File file;

    public RepositoryImageFile(File file) {
        this.file = file;

        if(!getExtension().equalsIgnoreCase("png") && !getExtension().equalsIgnoreCase("jpg"))
            throw new IllegalArgumentException(getExtension() + " is not a picture file.");
    }

    public int[] getImageDimensions() {
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
