import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

public class ImageCollectionModel {
    /* A list of the model's views. (grid and list COLLECTION view) */
    public ArrayList<IView> collectionViews = new ArrayList<IView>();

    // list of individual models for each respective image
    public ArrayList<ImageModel> imageModels = new ArrayList<ImageModel>();

    public void addView(IView view) {
        this.collectionViews.add(view);
        view.updateView();
    }

    public int filterRating = 0;

    // upload an image, save it to array of imagemodels, send update to collection views
    public void handleLoadClick() {
        // open file viewer
        //System.out.println("load clicked");
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG, JPG, GIF", "png", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            // make a method that adds imageModels
            File[] files = chooser.getSelectedFiles();
            if (files.length > 0) {
                addImageModels(files, 0);
            }
        }

        //update views notify observers
    }

    public void addImageModels(File[] files, int userRating) {
        BasicFileAttributes attr = null;
        Path filepath;
        Date date;
        for (int i = 0; i < files.length; i++) {
            try {
                filepath = files[i].toPath();
                attr = Files.readAttributes(filepath, BasicFileAttributes.class);
                date = new Date(attr.creationTime().to(TimeUnit.MILLISECONDS));
                //System.out.println(date.getDate());
                Calendar creationDate = Calendar.getInstance(); // use calendar since Date.getDate() deprec.
                creationDate.setTime(date);
                //System.out.println(creationDate.get(Calendar.DAY_OF_MONTH) + "/" + creationDate.MONTH + creationDate.YEAR);
                //System.out.println(filepath.toString() + "\n" + creationDate.getTime());
                // create image model for each image uploaded
                ImageModel imageModel = new ImageModel(filepath, creationDate, userRating, this);
                // add to array
                imageModels.add(imageModel);
            } catch (IOException exception) {
                System.out.println("error when loading file");
            }
        }
        notifyObservers();
    }

    public void handleClearClick() {
        imageModels.clear();
        notifyObservers();
    }

    public void handleFilterUpdate(int newFilterRating) {
        this.filterRating = newFilterRating;
        notifyObservers();
    }

    public void handleImageRatingUpdate() {
        notifyObservers();
    }

    private void notifyObservers() {
        for (IView view : this.collectionViews) {
            view.updateView(); // update the grid and list collection views
        }
    }
}