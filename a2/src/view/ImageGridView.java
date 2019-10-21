import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;


import model.*;

public class ImageGridView extends JPanel implements IView {

    //boolean isGridView = true; // need this?

    public ImageModel imageModel;


    public ImageGridView(ImageModel imageModel) {
        super();

        // TODO: BUILD AN IMAGE CARD BASED ON THE IMAGE MODEL metadata

        this.imageModel = imageModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setSize(100,100);
        this.setPreferredSize(new Dimension(350,350));
        this.setMaximumSize(new Dimension(600,600));
        this.setMinimumSize(new Dimension(250,250));

/*
        // add image icon
        ImageIcon originalImage = new ImageIcon(this.imageModel.filePath.toString());

        //JLabel image = new JLabel(image);

        int originalWidth = originalImage.getIconWidth();
        int originalHeight = originalImage.getIconHeight();
        System.out.println(originalWidth);
        System.out.println(originalHeight);

        Dimension scaledDimension = getScaledDimension(new Dimension(originalHeight, originalWidth),
                new Dimension(250,250));
        //int scaledWidth = originalWidth / (originalWidth / 250);
        //int scaledHeight = originalHeight / (originalHeight / 250);

        System.out.println(scaledDimension.width);
        System.out.println(scaledDimension.height);
        Image scaled = originalImage.getImage().getScaledInstance(
                scaledDimension.width, scaledDimension.height, Image.SCALE_SMOOTH);


        ImageIcon scaledImage = new ImageIcon(scaled);
        JLabel image = new JLabel(scaledImage);
        this.add(image);


         */

        try {
            File input = new File(this.imageModel.filePath.toString());
            BufferedImage buffImage = ImageIO.read(input);
            BufferedImage resized = resize(buffImage, 200, 200);
            String resizeImgFilePath = this.imageModel.filePath.toString();
            //System.out.println(resizeImgFilePath);
            String fileExtension = "";
            int i = this.imageModel.filePath.toString().lastIndexOf('.');
            if (i > 0) {
                fileExtension = this.imageModel.filePath.toString().substring(i + 1);
                //System.out.println(fileExtension);
            }
            File output = new File(resizeImgFilePath.substring(0,i)
                    + "-resized-" + "." + fileExtension);
            ImageIO.write(resized, "png", output);

            ImageIcon scaledImage = new ImageIcon(resizeImgFilePath.substring(0,i)
                    + "-resized-" + "." + fileExtension);
            //System.out.println(resizeImgFilePath.substring(0,i) + "-resized-" + "." + fileExtension);
            JLabel image = new JLabel(scaledImage);
            this.add(image);
            image.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                    imageModel.handleImageClick();
                }
            });
        } catch (IOException e) {
            System.out.println("failed to resize image");
        }

        // add image info and rating widget
        JLabel fileName = new JLabel(this.imageModel.filePath.getFileName().toString());
        this.add(fileName);
        JLabel creationDate = new JLabel((this.imageModel.creationDate.get(Calendar.MONTH) + 1) + "/"
                + this.imageModel.creationDate.get(Calendar.DAY_OF_MONTH) + "/"
                + this.imageModel.creationDate.get(Calendar.YEAR));
        this.add(creationDate);

        JButton rating_widget = new JButton("rating widget");
        this.add(rating_widget);



        /*
        System.out.println((this.imageModel.creationDate.get(Calendar.MONTH) + 1) + "/"
                + this.imageModel.creationDate.get(Calendar.DAY_OF_MONTH) + "/"
                + this.imageModel.creationDate.get(Calendar.YEAR));
*/

        //creationDate.get(Calendar.DAY_OF_MONTH)
    }

    // used for scaling image
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    // used for scaling image
    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    public void updateView() {

    }
}