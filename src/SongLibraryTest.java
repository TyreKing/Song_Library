import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.junit.Test;

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
			//dont know why this does not like assertEquals
			assertEquals("unexpected title","SongLibrary", frame.getTitle());
			
			JMenuBar main = Gooey.getMenuBar(frame);
			JMenu library = Gooey.getMenu(main, "SongLibrary");
			JMenu table = Gooey.getMenu(main, "Table");
			JMenuItem about = Gooey.getMenu(library, "About...");
			JMenuItem exit = Gooey.getMenu(library, "Exit");
			JMenuItem newTable = Gooey.getMenu(table, "New");
			JMenuItem open = Gooey.getMenu(table, "Open...");
			JMenuItem save = Gooey.getMenu(library, "Save as...");
			

			
		}
	});
}
}
