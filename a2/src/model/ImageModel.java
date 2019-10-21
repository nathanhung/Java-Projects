import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;
import javax.swing.event.*;


public class ImageModel {

    // path to image
    public Path filePath;

    // creation date
    public Calendar creationDate;
    // user rating
    public int userRating = 0;

    public ImageCollectionModel model;


    public ImageGridView imageGridView;
    public ImageListView imageListView;

    public ImageModel(Path filePath, Calendar creationDate, int userRating, ImageCollectionModel model) {
        this.filePath = filePath;
        this.creationDate = creationDate;
        this.userRating = userRating;
        this.model = model;
        // set the imageView (create both image views when model is created
        this.imageGridView = new ImageGridView(this);
        this.imageListView = new ImageListView(this);
    }

    public void handleImageClick() {
        JFrame newFrame = new JFrame();

        //newFrame.setLayout(new BoxLayout(newFrame, BoxLayout.Y_AXIS));
        //ImageIcon image = new ImageIcon()
        JLabel imageLabel = new JLabel(new ImageIcon(this.filePath.toString()));
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.add(imageLabel);

        JSlider slider = new JSlider(0, 5, this.userRating);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1);
        slider.setSnapToTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider)e.getSource();
                // update the user rating for this image
                handleUserRatingUpdate(s.getValue());
                //label.setText("slider " + s.getValue());
            }
        });
        imagePanel.add(slider);

        newFrame.add(imagePanel);
        newFrame.pack();
        newFrame.setDefaultCloseOperation(newFrame.HIDE_ON_CLOSE);
        newFrame.setVisible(true);
    }



    public void handleUserRatingUpdate(int newUserRating) {
        this.userRating = newUserRating;
        this.imageGridView.slider.setValue(newUserRating);
        this.imageListView.slider.setValue(newUserRating);
        this.model.handleImageRatingUpdate();
    }
/*
    public void addView(IView view) {
        this.imageViews.add(view);
        // update the view to current state of the model
        view.updateView();
    }
    */


}