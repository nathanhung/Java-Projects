import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.text.View;

import view.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        new LayoutFrame();
    }
}

class LayoutFrame extends JFrame {
    public LayoutFrame() {
        // create a window
        JFrame frame = new JFrame("Fotag");
        //frame.setLayout(new BorderLayout());

        // create an ImageCollectionModel for the collection of ImageModel objects, and initialize it (TODO)
        ImageCollectionModel imageCollectionModel = new ImageCollectionModel();

        // create grid collection view and initialize it (TODO)
        ImageCollectionGridView imageCollectionGridView = new ImageCollectionGridView(imageCollectionModel);
        // tell model about the view (TODO)
        imageCollectionModel.addView(imageCollectionGridView);

        // create list collection view and initialize it (TODO)
        ImageCollectionListView imageCollectionListView = new ImageCollectionListView(imageCollectionModel);
        // tell model about the view (TODO)
        imageCollectionModel.addView(imageCollectionListView);

        // create a toolbar panel (refactored to a class) (TODO) (see main5.java)
        Toolbar toolbar = new Toolbar(imageCollectionModel);
        // tell model about the toolbar
        imageCollectionModel.addView(toolbar); // need this???

        // create tab panel to hold the imageCollectionGridView and imageCollectionListView
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Grid View", imageCollectionGridView);
        tabs.add("List View", imageCollectionListView);

        // create a layout panel to hold the toolbar, and tab (with imagecollection views)
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());

        // add panels to the main_panel
        main_panel.add(toolbar, BorderLayout.NORTH);
        main_panel.add(tabs, BorderLayout.CENTER);

        // add main_panel to frame
        frame.add(main_panel);
        // set window behaviour and display it
        frame.setResizable(true);
        frame.setSize(800, 600);
        // TODO: set a minimum size
        // frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}






