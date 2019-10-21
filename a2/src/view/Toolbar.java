import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

        JButton load_button = new JButton("Load"); // TODO get icon
        load_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                model.handleLoadClick();
            }
        });

        JButton clear_button = new JButton("Clear");
        clear_button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {        // use anonymous inner class instead of MyMouseAdapter class
                model.handleClearClick();
            }
        });

        JLabel rating_widget = new JLabel("Rating Widget"); // todo
        this.add(load_button);
        this.add(clear_button);
        this.add(rating_widget);

    }

    public void updateView() {

    }
}