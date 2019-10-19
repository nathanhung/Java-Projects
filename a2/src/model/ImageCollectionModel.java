import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ImageCollectionModel {
    /* A list of the model's views. (grid and list COLLECTION view) */
    private ArrayList<IView> views = new ArrayList<IView>();

    // list of individual models for each respective image
    private ArrayList<ImageModel> imageModels = new ArrayList<ImageModel>();

    public ImageCollectionModel() {

    }


    public void addView(IView view) {
        this.views.add(view);
        view.updateView();
    }

    // upload an image, save it to array of imagemodels, send update to collection views
    public void handleLoadClick() {
        // open file viewer
        System.out.println("load clicked");
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG, JPG, GIF", "png", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            //System.out.println(chooser.getSelectedFile().getAbsoluteFile());
            // TODO: LOOP IT
            Path file = chooser.getSelectedFile().toPath();
            BasicFileAttributes attr = null;
            try {
                attr = Files.readAttributes(file, BasicFileAttributes.class);
            } catch (IOException exception) {

            }
            System.out.println("creationTime: " + attr.creationTime());

            // create a new model for the images

        }

        //update views notify observers
    }

    public void addModel(ImageModel model) {
        this.imageModels.add(model);
        notifyObservers();
    }

    private void notifyObservers() {
        for (IView view : this.views) {
            view.updateView(); // update the grid and list collection views
        }
    }
}