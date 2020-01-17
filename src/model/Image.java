package model;

import java.io.InputStream;

public interface Image {
    public String name();
    public InputStream data();
    public Image nextImage();
    public Image prevImage();
}
