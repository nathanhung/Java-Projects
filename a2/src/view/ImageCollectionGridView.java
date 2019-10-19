import javax.swing.*;
import java.awt.*;

import model.*;

class ImageCollectionGridView extends JPanel implements IView {

    private ImageCollectionModel model;



    public ImageCollectionGridView(ImageCollectionModel model) {
        super();
        this.model = model;
        this.setLayout(new FlowLayout());

        JPanel card2 = new JPanel();
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));

        // add image info and rating widget
        JButton rating_button2 = new JButton("rating widget");
        JLabel image_info1 = new JLabel("image info");
        card2.add(rating_button2);
        card2.add(image_info1);

        // add icon
        ImageIcon image = new ImageIcon("images/bunny.jpg");
        JLabel image2_panel = new JLabel(image);
        card2.add(image2_panel);

        this.add(card2);

    }

    public void updateView() {
        // iterate through the models list of image models and display them
    }
}