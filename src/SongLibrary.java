import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.Box;
//import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
//import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SongLibrary extends JFrame{
	
	private final String [] HEADERS = {"Song", "Artist", "Album", "Year"};
    private Object [][] DATA = {};
    
	public SongLibrary(){
        super("SongLibrary");
        setLayout(new BorderLayout());
        
        JMenuBar menubar = new JMenuBar();
        add(BorderLayout.NORTH, menubar);
        
        Box panel = Box.createVerticalBox();
        JButton added = new JButton("Add");
        JButton delete = new JButton("Delete");
        panel.add(added);
        panel.add(delete);
        panel.add(Box.createVerticalBox());
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
        
        
        about.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(SongLibrary.this, new JLabel("<html><b>SongLibrary</b> <br/> <p>by Sam Allison and Tyre King</p><html>"), "About", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                askForClosing();
            }
        });
        addWindowListener(new WindowAdapter(){
           @Override
           public void windowClosing(WindowEvent e){
               askForClosing();
           }
        });
        
        
        
        // this is for the saved songs in text files
        SwingUtilities.invokeLater( new Runnable(){
            @Override
           public void run(){
                JFileChooser chooser = new JFileChooser();
                int result = chooser.showSaveDialog(SongLibrary.this);
                if(result== JFileChooser.APPROVE_OPTION){
                    File file = chooser.getSelectedFile(); 
                    //TODO save table data to file
                }
            }
        });
        
        
        
        JTable table = new JTable( DATA, HEADERS );
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(BorderLayout.CENTER, scrollPane);
        
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    protected void askForClosing() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to exit?");
        if(result == JOptionPane.YES_OPTION){
            dispose();
        }
    }
    
    
    
    
    
    
    
    
    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame f = new SongLibrary();
                f.setVisible(true);
                
            }
        });
    }
    
    
}
