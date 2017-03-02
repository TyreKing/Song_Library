import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SongLibrary extends JFrame{
    public SongLibrary(){
        super("SongLibrary");
        setLayout(new BorderLayout());
        
        JMenuBar menubar = new JMenuBar();
        add(BorderLayout.NORTH, menubar);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        add(BorderLayout.EAST,panel);

        JButton added = new JButton("Add");
        JButton delete = new JButton("Delete");
        panel.add(added);
        panel.add(delete);
        add(BorderLayout.EAST,panel);
        
        
        
        JMenu Library = new JMenu("SongLibrary");
        menubar.add(Library);
        JMenuItem about = Library.add("About...");
        Library.addSeparator();
        JMenuItem exit = Library.add("Exit");
        
        JMenu table = new JMenu("Table");
        menubar.add(table);
        JMenuItem New = table.add("New");
        table.addSeparator();
        JMenuItem open = table.add("Open...");
        table.addSeparator();
        JMenuItem saveAs = table.add("Save As...");
        
        
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
        //table = new JTable( DATA, HEADERS );
        //table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        //table.setFillsViewportHeight(true);
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    protected void askForClosing() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to exit");
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
