import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import model.*;

public class Toolbar extends JPanel implements IView{
    private ImageCollectionModel model;

    /*
    private JButton load_button;
    private JButton clear_button;
    private JLabel rating_widget;


     */

    // toolbar code
    public Toolbar(ImageCollectionModel model) {
        super();
        this.model = model;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // add toolbar components:
        JLabel title_label = new JLabel("Fotag!");
        this.add(title_label);
        // add glue
        this.add(Box.createHorizontalGlue());


        //ImageIcon quitIcon = new ImageIcon("images/bunny.jpg");
        JButton load_button = new JButton("Load"); // TODO get icon
        load_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                model.handleLoadClick();
            }
        });
        this.add(load_button);

        JButton clear_button = new JButton("Clear");
        clear_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                model.handleClearClick();
            }
        });
        this.add(clear_button);

        JLabel filterLabel = new JLabel("Filter by");
        this.add(filterLabel);

        // filter slider
        JSlider ratingSlider = new JSlider(0, 5, 0);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setSnapToTicks(true);
        ratingSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider)e.getSource();
                // update the user rating for this image
                model.handleFilterUpdate(s.getValue());
                //label.setText("slider " + s.getValue());
            }
        });
        this.add(ratingSlider);

    }

    public void updateView() {

    }
}