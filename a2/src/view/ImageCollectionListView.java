import javax.swing.*;
import java.awt.*;

import model.*;

class ImageCollectionListView extends JPanel implements IView {

    private ImageCollectionModel model;

    public ImageCollectionListView(ImageCollectionModel model) {
        super();
        this.model = model;
        this.setLayout(new FlowLayout());
    }

    public void updateView() {

    }
}