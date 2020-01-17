package swingimageviewer;

import control.Command;
import control.NextImageCommand;
import control.PreviousImageCommand;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Image;
import persistence.FileImageLoader;
import view.ImageDisplay;

public class ImageViewer extends JFrame {
    
    private ImageDisplay imageDisplay;
    private final Map<String,Command> commands = new HashMap<>();
    
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Jonay\\Pictures\\Vicente\\Fotos Milanuncios");
        FileImageLoader imageLoader = new FileImageLoader(file);
        Image image = imageLoader.loadImages();
        new ImageViewer().getImageDisplay().show(image);
    }

    public ImageViewer() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState( JFrame.MAXIMIZED_BOTH ); //|JFrame.MAXIMIZED_BOTH
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imageDisplay());
        addCommands();
        this.getContentPane().add(toolBar(),BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private ImageDisplay getImageDisplay() {
        return this.imageDisplay;
    }

    private Component toolBar() {
        JPanel panel = new JPanel();
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
    }

    private JButton prevButton() {
        JButton button = new JButton("<");
        button.addActionListener( 
                e -> {
                    this.commands.get("PrevImage").execute();
                }
        );
        return button;
    }

    private JButton nextButton() {
        JButton button = new JButton(">");
        button.addActionListener(
                e -> {
                    this.commands.get("NextImage").execute();
                }
        );
        return button;
    }

    private void addCommands() {
        this.commands.put("PrevImage", new PreviousImageCommand(this.imageDisplay));
        this.commands.put("NextImage", new NextImageCommand(this.imageDisplay));
    }

    private JPanel imageDisplay() {
        SwingImageDisplay sid = new SwingImageDisplay();
        this.imageDisplay = sid;
        return sid;
    }

}