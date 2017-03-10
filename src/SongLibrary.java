import java.awt.BorderLayout;
import java.awt.Dimension;
// import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.Box;
// import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
// import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SongLibrary extends JFrame {

    private final String[] HEADERS = { "Song", "Artist", "Album", "Year" };
    // may not need this
    //private Object[][] DATA = {};
     private ArrayList<String []> DATA = new ArrayList<String []>();

    public SongLibrary() {
        super("SongLibrary");

        // create the application
        setLayout(new BorderLayout());

        // creates the menu bar
        JMenuBar menubar = new JMenuBar();
        add(BorderLayout.NORTH, menubar);

        // adds buttons to menu bar
        Box panel = Box.createVerticalBox();
        JButton added = new JButton("Add");
        JButton delete = new JButton("Delete");
        panel.add(added);
        panel.add(delete);
        panel.add(Box.createVerticalBox());
        // adds a border to the right side
        add(BorderLayout.EAST, panel);

        JMenu Library = new JMenu("SongLibrary");
        menubar.add(Library);
        JMenuItem about = Library.add("About...");
        Library.addSeparator();
        JMenuItem exit = Library.add("Exit");

        JMenu tables = new JMenu("Table");
        menubar.add(tables);
        JMenuItem New = tables.add("New");
        tables.addSeparator();
        JMenuItem open = tables.add("Open...");
        tables.addSeparator();
        JMenuItem saveAs = tables.add("Save As...");

        JFileChooser chooser = new JFileChooser();

        // JTable table = new JTable( DATA, HEADERS );
        // table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        // table.setFillsViewportHeight(true);
        // JScrollPane scrollPane = new JScrollPane(table);
        // add(BorderLayout.CENTER, scrollPane);
        DefaultTableModel tablemodel = new DefaultTableModel(HEADERS, 0);
        JTable table = new JTable(tablemodel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(BorderLayout.CENTER, scrollPane);

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SongLibrary.this,
                        new JLabel(
                                "<html><b>SongLibrary</b> <br/> <p>by Sam Allison and Tyre King</p><html>"),
                        "About", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askForClosing();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                askForClosing();
            }
        });
        // the saveAs button has a action listener
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveAs) {
                    int result = chooser.showSaveDialog(SongLibrary.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        // TODO NEED FIGURE OUT HOW TO PLACE THE TEXT DOC IN THE
                        // APPLICATION
                    }
                }
            }
        });
        // the open button has a action listener
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == open) {
                    int result = chooser.showOpenDialog(SongLibrary.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        try {
                            FileReader readme = new FileReader(file.getPath());
                            Scanner in = new Scanner(readme);
                            String nl;
                            while ((nl = in.nextLine()) != null) {

                                DATA.add(nl.split(","));

                            }
                        }
                        catch (Exception e1) {
                            if(e.getClass().equals(NoSuchElementException.class)){
                                System.err.println("end of file");
                            }else{
                                e1.printStackTrace();
                            }
                            
                        }

                    }
                }
            }
        });

        // // this is for the saved songs in text files
        // SwingUtilities.invokeLater( new Runnable(){
        // @Override
        // public void run(){
        // JFileChooser chooser = new JFileChooser();
        // int result = chooser.showSaveDialog(SongLibrary.this);
        // if(result== JFileChooser.APPROVE_OPTION){
        // File file = chooser.getSelectedFile();
        // //TODO save table data to file
        // }
        // }
        // });

        // JTable table = new JTable( DATA, HEADERS );
        // table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        // table.setFillsViewportHeight(true);
        // JScrollPane scrollPane = new JScrollPane(table);
        // add(BorderLayout.CENTER, scrollPane);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    protected void askForClosing() {
        int result = JOptionPane.showConfirmDialog(this,
                "Do you want to exit?");
        if (result == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new SongLibrary();
                f.setVisible(true);

            }
        });
    }

}
