import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;


import model.*;

public class ImageListView extends JPanel implements IView {

    //boolean isGridView = true; // need this?

    ImageModel imageModel;

    public JSlider slider;


    ImageListView(ImageModel imageModel) {
        super();
        this.imageModel = imageModel;
        // build an image card based on the model metadata

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400,350));
        this.setMaximumSize(new Dimension(550,450));
        this.setMinimumSize(new Dimension(400,300));

        JPanel image_panel = new JPanel();
        // add image:
        try {
            // add image icon (IMPLEMENTATION 2)
            ImageIcon originalImage = new ImageIcon(this.imageModel.filePath.toString());

            int originalWidth = originalImage.getIconWidth();
            int originalHeight = originalImage.getIconHeight();

            Image scaled = originalImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledImage = new ImageIcon(scaled);
            JLabel image = new JLabel(scaledImage);

            image_panel.add(image);
            image.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                    imageModel.handleImageClick();
                }
            });
        } catch (Exception e) {
            System.out.println("failed to resize image");
        }

        JPanel info_panel = new JPanel();
        info_panel.setLayout(new BoxLayout(info_panel, BoxLayout.Y_AXIS));
        // add image info and rating widget

        JLabel fileName = new JLabel(this.imageModel.filePath.getFileName().toString());
        info_panel.add(fileName);
        JLabel creationDate = new JLabel((this.imageModel.creationDate.get(Calendar.MONTH) + 1) + "/"
                + this.imageModel.creationDate.get(Calendar.DAY_OF_MONTH) + "/"
                + this.imageModel.creationDate.get(Calendar.YEAR));
        info_panel.add(creationDate);

        // add rating slider
        this.slider = new JSlider(0, 5, imageModel.userRating);
        this.slider.setPaintTicks(true);
        this.slider.setPaintLabels(true);
        this.slider.setMajorTickSpacing(1);
        this.slider.setSnapToTicks(true);
        this.slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider)e.getSource();
                // update the user rating for this image
                imageModel.handleUserRatingUpdate(s.getValue());
                //label.setText("slider " + s.getValue());
            }
        });
        info_panel.add(this.slider);

        JButton clear_button = new JButton("Clear");
        clear_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                slider.setValue(0);
                imageModel.handleUserRatingUpdate(0);
            }
        });
        info_panel.add(clear_button);
        //info_panel.add(ratingSlider);

        this.add(image_panel);
        this.add(image_panel, BorderLayout.WEST);
        this.add(info_panel, BorderLayout.CENTER);
    }
    public void updateView() {

    }
}