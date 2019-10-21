import javax.swing.*;
import java.awt.*;

import model.*;

class ImageCollectionGridView extends JPanel implements IView {

    private ImageCollectionModel model;



    public ImageCollectionGridView(ImageCollectionModel model) {
        super();
        this.model = model;
        this.setLayout(new WrapLayout(WrapLayout.LEADING));

    }

    public void updateView() {
        // iterate through the models list of image models and display them
        this.removeAll();
        this.revalidate();
        this.repaint();
        // loop through images in model and display them
        for (int i = 0; i < this.model.imageModels.size(); i++) {
            if (this.model.imageModels.get(i).userRating >= this.model.filterRating) {
                this.add(this.model.imageModels.get(i).imageGridView);
            }
        }
    }
}