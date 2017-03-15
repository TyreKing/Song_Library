import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.cnu.cs.gooey.Gooey;
import edu.cnu.cs.gooey.GooeyDialog;
import edu.cnu.cs.gooey.GooeyFrame;

public class SongLibraryTest {
@Test
public void startProgram(){
	Gooey.capture(new GooeyFrame(){
		@Override
		public void invoke(){
			SongLibrary.main(new String[]{});
		}
		@Override
		public void test(JFrame frame){
			assertEquals("unexpected title","SongLibrary", frame.getTitle());
			
			JMenuBar main = Gooey.getMenuBar(frame);
			JMenu library = Gooey.getMenu(main, "SongLibrary");
			JMenu table = Gooey.getMenu(main, "Table");
			JMenuItem about = Gooey.getMenu(library, "About...");
			JMenuItem exit = Gooey.getMenu(library, "Exit");
			JMenuItem newTable = Gooey.getMenu(table, "New");
			JMenuItem open = Gooey.getMenu(table, "Open...");
			JMenuItem save = Gooey.getMenu(table, "Save as...");
			
			Gooey.capture(new GooeyDialog(){
				@Override
				public void invoke(){
					about.doClick();
				}
				@Override
				public void test(JDialog dialog){
					JTextField input = Gooey.getComponent(dialog, JTextField.class);
					input.setText("SongLibrary"  + "by Sam Allison and Tyre King");
					JButton ok = Gooey.getButton(dialog, "Ok");
				}
			});
		}
	});
}
}
