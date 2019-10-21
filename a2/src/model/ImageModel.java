import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;


public class ImageModel {

    // path to image
    public Path filePath;

    // creation date
    public Calendar creationDate;
    // user rating
    public int userRating = 0;


    public ImageGridView imageGridView;
    public ImageListView imageListView;

    public ImageModel(Path filePath, Calendar creationDate, int userRating) {
        this.filePath = filePath;
        this.creationDate = creationDate;
        this.userRating = userRating;
        // set the imageView (create both image views when model is created
        this.imageGridView = new ImageGridView(this);
        this.imageListView = new ImageListView(this);
    }

    public void handleImageClick() {
        JFrame newFrame = new JFrame();

        //ImageIcon image = new ImageIcon()
        JLabel imageLabel = new JLabel(new ImageIcon(this.filePath.toString()));
        JPanel imagePanel = new JPanel();
        imagePanel.add(imageLabel);

        newFrame.add(imagePanel);
        newFrame.pack();
        newFrame.setDefaultCloseOperation(newFrame.HIDE_ON_CLOSE);
        newFrame.setVisible(true);
    }
/*
    public void addView(IView view) {
        this.imageViews.add(view);
        // update the view to current state of the model
        view.updateView();
    }
    */


}