package swingimageviewer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image currentImage;
    
    @Override
    public Image current() {
        return currentImage;
    }

    @Override
    public void show(Image image) {
        this.currentImage = image;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        if(currentImage == null)
            return;
        g.clearRect(0,0,this.getWidth(),this.getHeight());
        BufferedImage image = imageOf(currentImage);
        double ratio = 1;
        if(image.getWidth() > this.getWidth() || 
                image.getHeight() > this.getHeight()){
            double widthRatio = (double) image.getWidth() / this.getWidth();
            double heightRatio = (double) image.getHeight() / this.getHeight();
            ratio = widthRatio > heightRatio ? widthRatio : heightRatio;
        }
        int dimX = (int) (image.getWidth() / ratio);
        int dimY = (int) (image.getHeight() / ratio);
        int posX = (this.getWidth()-dimX)/2;
        int posY = (this.getHeight()-dimY)/2;
        g.drawImage(image, posX, posY, dimX, dimY, null);
    }

    private BufferedImage imageOf(Image image) {
        try {             
            return ImageIO.read(image.data());
        } catch (IOException ex) {
            System.out.println("Exception in SwingImageDisplay.imageOf");
            System.out.println(ex.getMessage());
            return null;
        }
   }
    
    
}
