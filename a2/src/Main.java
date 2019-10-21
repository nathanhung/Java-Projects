import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.View;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

import view.*;
import model.*;

public class Main {
    public static void main(String[] args) {
        new LayoutFrame();
    }
}

class LayoutFrame {
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
        JPanel main_panel_wrapper = new JPanel();
        main_panel.setLayout(new BorderLayout());
/*
        JPanel tabs_panel = new JPanel();
        tabs_panel.setLayout(new FlowLayout());
        tabs_panel.setPreferredSize(new Dimension(400,5100));
        tabs_panel.add(tabs);


 */
        //frame.add(main_panel);



        // add panels to the main_panel
        main_panel.add(toolbar, BorderLayout.NORTH);
        main_panel.add(tabs, BorderLayout.CENTER);

        // add scroll to the tab only (CENTER of border layout)
        JScrollPane jp = new JScrollPane(tabs, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jp.getVerticalScrollBar().setUnitIncrement(15); // speedup scrolling

        //frame.add(jp);
        main_panel.add(jp, BorderLayout.CENTER);

        // add main_panel to frame
        frame.add(main_panel);
        // set window behaviour and display it
        frame.setResizable(true);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setMinimumSize(new Dimension(600, 600));
        // TODO: set a minimum size
        frame.pack();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // close operation
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                // write to text file before closing
                try {
                    PrintWriter writer = new PrintWriter("saved_images.txt", "UTF-8");
                    writer.print(""); // clear the content of file
                    for (int i = 0; i < imageCollectionModel.imageModels.size(); i++) {
                        writer.println(imageCollectionModel.imageModels.get(i).filePath.toString());
                        writer.println(imageCollectionModel.imageModels.get(i).userRating);
                    }
                    writer.close();
                } catch (IOException ex) {
                    System.out.println("failed to save images on close");
                }
                System.exit(0);//cierra aplicacion
            }
        });

        // on open, load images
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                System.out.println("this window was opened for the first time");
                File file = new File("saved_images.txt");
                boolean exists = file.exists();
                if (exists = true) {
                    System.out.println("loading saved images");
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            //System.out.println(line);
                            File[] fileArr = new File[1];
                            File imageFile = new File(line);
                            fileArr[0] = imageFile;
                            line = br.readLine(); // read next line which is the user rating
                            if (line != null) {
                                // exception
                                imageCollectionModel.addImageModels(fileArr, Integer.parseInt(line));
                            }
                        }
                    } catch (IOException ex) {

                    }
                }
            }
        });
    }
}






