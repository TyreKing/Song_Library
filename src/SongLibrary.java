import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.TextField;
// import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//import java.util.Vector;

import javax.swing.Box;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.swing.JTable;
//import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SongLibrary extends JFrame {

    private final String[] HEADERS = { "Song", "Artist", "Album", "Year" };

    
     private Object[][] DATA = {};
     private BufferedReader reader;
     private DefaultTableModel tablemodel;
      
     private Object [] newRow = new Object [4];
  
    public SongLibrary() {
        setTitle("SongLibrary");
        

     
        // create the application
        setLayout(new BorderLayout());

        // creates the menu bar
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        add(BorderLayout.NORTH, menubar);

        // adds buttons to menu bar
        Box panel = Box.createVerticalBox();
        JButton added = new JButton("Add");
        JButton delete = new JButton("Delete");
        panel.add(added);
        panel.add(delete);
        panel.add(Box.createVerticalBox());
        //adds a border to the right side
        add(BorderLayout.EAST,panel);
        

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

        JTable table = new JTable(DATA, HEADERS);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(BorderLayout.CENTER, scrollPane);
        tablemodel = new DefaultTableModel(0,4);
        tablemodel.setColumnIdentifiers(HEADERS);
        table.setModel(tablemodel);

    	if(table.getRowCount() == 0){
    		delete.setEnabled(false);
    	}

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SongLibrary.this,
                        new JLabel(
                                "<html><b>SongLibrary</b> <br/> <p>by Sam Allison and Tyre King</p><html>"),
                        "About", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        
        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int result =  JOptionPane.showConfirmDialog(SongLibrary.this, "Clear all table data?");
                if(result == JOptionPane.YES_OPTION){
                    tablemodel.setRowCount(0);
                }
                setTitle("SongLibrary");
                delete.setEnabled(false);
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
       
        added.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablemodel.addRow(newRow);
                delete.setEnabled(true);
            }
        });
        
        JFileChooser chooser1 = new JFileChooser();
        
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table.getSelectedRow()!= -1){
                    tablemodel.removeRow(table.getSelectedRow());
                }
                else if(table.getSelectedRow()== -1 ){
                    JOptionPane.showMessageDialog(SongLibrary.this, "No row selected", "Message", JOptionPane.OK_OPTION);
                }
                if(table.getRowCount() == 0){
                	delete.setEnabled(false);
                }
                
            }
        });      
        
       
        // the saveAs button has a action listener
       saveAs.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent d){
               if(d.getSource() == saveAs){
                   int result = chooser1.showSaveDialog(SongLibrary.this);
                 if(result== JFileChooser.APPROVE_OPTION){
                     File file = chooser1.getSelectedFile();
                     String line;
                     int rowcount = tablemodel.getRowCount();
                     try {
                        BufferedWriter wrote = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                        for(int row = 0; row < rowcount; row++){
                           for(int col = 0; col < tablemodel.getColumnCount() ;col++){
                               line = (String) tablemodel.getValueAt(row, col);
                               if(col!= 3){
                                   line += ",";
                               }else{
                                   line+="\n";
                               }
                               wrote.write(line);
                               //System.out.println(line);
                           }
                           
                        }
                        wrote.close();
                        setTitle("SongLibrary"+file.getAbsolutePath());
                    }
                    catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Buffered writer issue");
                        e.printStackTrace();
                    }
               }
           }
           }
       });

        // the open button has a action listener
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete.setEnabled(false);
                if (e.getSource() == open) {
                    int result = chooser.showOpenDialog(SongLibrary.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        String line;
                        tablemodel.setRowCount(0);
                         setTitle("SongLibrary ["+ file.getAbsolutePath()+"]");
                        
                        try {
                            
                            reader = new BufferedReader(new FileReader(file));
                            while((line = reader.readLine())!=null){
                                tablemodel.addRow(line.split(","));
                            }
                            reader.close();
                            
                            
                        }
                        catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Buffered reader issue");
                        }
                       	if(table.getRowCount() == 0){
                    		delete.setEnabled(false);
                    	}
                       	else
                        delete.setEnabled(true);
                       

                    }
                }
            }
        });
     
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
