import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SongLibrary extends JFrame{
    public SongLibrary(){
        super("SongLibrary");
        setLayout(new BorderLayout());
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
