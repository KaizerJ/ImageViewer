package persistence;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import model.Image;

public class FileImageLoader implements ImageLoader{
    private final File[] files;
    
    public FileImageLoader(File folder) {
        files = folder.listFiles(imageType());
    }
    
    
    @Override
    public Image loadImages() {
        return imageAt(0);
    }

    private FileFilter imageType() {
        return new FileFilter(){
            private final String[] acceptedFormats = {".JPG",".JPEG",".PNG"};
            
            @Override
            public boolean accept(File pathname) {
                for (String format : acceptedFormats) {
                    if(pathname.getName().toUpperCase().endsWith(format))
                        return true;
                }
                return false;
            }
        };
    }

    private Image imageAt(int i) {
        return new Image(){
            @Override
            public String name() {
                return files[i].getName();
            }

            @Override
            public InputStream data() {
                try {
                    return new BufferedInputStream(new FileInputStream(files[i]));
                } catch (FileNotFoundException ex) {
                    return null;
                }
            }

            @Override
            public Image nextImage() {
                return (i == files.length -1) ? imageAt(0) : imageAt(i+1);
            }

            @Override
            public Image prevImage() {
                return (i == 0) ? imageAt(files.length-1) : imageAt(i-1);
            }
            
        };
    }
}
