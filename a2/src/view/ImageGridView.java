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

public class ImageGridView extends JPanel implements IView {

    public ImageModel imageModel;

    public JSlider slider;

    public ImageGridView(ImageModel imageModel) {
        super();

        // TODO: BUILD AN IMAGE CARD BASED ON THE IMAGE MODEL metadata

        this.imageModel = imageModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setSize(100,100);
        this.setPreferredSize(new Dimension(350,350));
        this.setMaximumSize(new Dimension(400,450));
        this.setMinimumSize(new Dimension(350,450));

        try {
            // add image icon (IMPLEMENTATION 2)
            ImageIcon originalImage = new ImageIcon(this.imageModel.filePath.toString());

            //JLabel image = new JLabel(image);

            int originalWidth = originalImage.getIconWidth();
            int originalHeight = originalImage.getIconHeight();
            //System.out.println(originalWidth);
            //System.out.println(originalHeight);

            Image scaled = originalImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledImage = new ImageIcon(scaled);
            JLabel image = new JLabel(scaledImage);
            this.add(image);

            image.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                    imageModel.handleImageClick();
                }
            });
        } catch (Exception e) {
            System.out.println("failed to resize image");
        }

        // add image info and rating widget
        JLabel fileName = new JLabel(this.imageModel.filePath.getFileName().toString());
        this.add(fileName);
        JLabel creationDate = new JLabel((this.imageModel.creationDate.get(Calendar.MONTH) + 1) + "/"
                + this.imageModel.creationDate.get(Calendar.DAY_OF_MONTH) + "/"
                + this.imageModel.creationDate.get(Calendar.YEAR));
        this.add(creationDate);

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
        this.add(this.slider);

        JButton clear_button = new JButton("Clear");
        clear_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                slider.setValue(0);
                imageModel.handleUserRatingUpdate(0);
            }
        });
        this.add(clear_button);
    }

    public void updateView() {

    }
}