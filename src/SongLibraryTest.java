import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import edu.cnu.cs.gooey.Gooey;
import edu.cnu.cs.gooey.GooeyDialog;
import edu.cnu.cs.gooey.GooeyFrame;

public class SongLibraryTest {
@Test
public void startProgram(){
	Gooey.capture(new GooeyFrame(){
		@Override
		public void invoke(){
			SongLibrary.main(new String[] { } );
		}
		@Override
		public void test(JFrame frame){
			assertEquals("unexpected title","SongLibrary", frame.getTitle());
			
			JMenuBar menubar = Gooey.getMenuBar(frame);
			JMenu library = Gooey.getMenu(menubar, "SongLibrary");
			JMenu table = Gooey.getMenu(menubar, "Table");
			JMenuItem about = Gooey.getMenu(library, "About...");
			JMenuItem exit = Gooey.getMenu(library, "Exit");
			JMenuItem newTable = Gooey.getMenu(table, "New");
			JMenuItem open = Gooey.getMenu(table, "Open...");
			JMenuItem save = Gooey.getMenu(table, "Save As...");
			JButton add = Gooey.getButton(frame, "Add");
			JButton delete = Gooey.getButton(frame, "Delete");
			JTable tab = Gooey.getComponent(frame, JTable.class);
			DefaultTableModel model = new DefaultTableModel(0,4);
			tab.setModel(model);
			assertEquals( "incorrect numbers of rows",0, model.getRowCount());
			assertEquals("incorrect number of coloums", 4, model.getColumnCount());
			add.isEnabled();
			delete.isEnabled();
		}
	});
}

	@Test
	public void closeProgram(){
		Gooey.capture(new GooeyFrame(){
			@Override
			public void invoke(){
				SongLibrary.main(new String[] { } );
			}
			@Override
			public void test(JFrame frame){
				JMenuBar menubar = Gooey.getMenuBar(frame);
				JMenu library = Gooey.getMenu(menubar, "SongLibrary");
				JMenuItem exit = Gooey.getMenu(library, "Exit");
				
				assertTrue("JFrame should be displayed",frame.isShowing());
				Gooey.capture(new GooeyDialog(){
					@Override
					public void invoke(){
						exit.doClick();
					}
					@Override
					public void test(JDialog dialog){
					Gooey.getLabel(dialog, "Do you want to exit?");
					JButton yes = Gooey.getButton(dialog, "Yes");
					yes.doClick();
					assertFalse("JDialog should be hidden", dialog.isShowing());
					}
				});

			}
		});
	}
	
	@Test
	public void tryCloseProgram(){
		Gooey.capture(new GooeyFrame(){
			@Override
			public void invoke(){
				SongLibrary.main(new String[] { } );
			}
			@Override
			public void test(JFrame frame){
				JMenuBar menubar = Gooey.getMenuBar(frame);
				JMenu library = Gooey.getMenu(menubar, "SongLibrary");
				JMenuItem exit = Gooey.getMenu(library, "Exit");
				
				assertTrue("JFrame should be displayed",frame.isShowing());
				Gooey.capture(new GooeyDialog(){
					@Override
					public void invoke(){
						exit.doClick();
					}
					@Override
					public void test(JDialog dialog){
					Gooey.getLabel(dialog, "Do you want to exit?");
					JButton no = Gooey.getButton(dialog, "No");
					no.doClick();
					assertFalse("JDialog should be hidden", dialog.isShowing());
					assertTrue("Jframe should be showing", frame.isShowing());
					}
				});

			}
		});
	}
	@Test
	public void testAbout(){
		Gooey.capture(new GooeyFrame(){
			@Override
			public void invoke(){
				SongLibrary.main(new String[] { } );
			}
			@Override
			public void test(JFrame frame){
				JMenuBar menubar = Gooey.getMenuBar(frame);
				JMenu library = Gooey.getMenu(menubar, "SongLibrary");
				JMenuItem about = Gooey.getMenu(library, "About...");
				
				Gooey.capture(new GooeyDialog(){
					@Override
					public void invoke(){
						about.doClick();
					}
					@Override
					public void test(JDialog dialog){
						String name = "<html><b>SongLibrary</b> <br/> <p>by Sam Allison and Tyre King</p><html>";
						Gooey.getLabel(dialog, name);
						JButton ok = Gooey.getButton(dialog, "OK");
						ok.doClick();
					}
				});
				
			}
		});
	}
	@Test
	public void testLoading(){
		
	}
	@Test
	public void testingAddingRows(){
		Gooey.capture(new GooeyFrame(){
			@Override
			public void invoke(){
				SongLibrary.main(new String[] { } );
			}
			@Override
			public void test(JFrame frame){
				JTable tab = Gooey.getComponent(frame, JTable.class);
				JButton add = Gooey.getButton(frame, "Add");
				DefaultTableModel model = new DefaultTableModel(0,4);
				add.doClick();
				model = new DefaultTableModel(1,0);
				assertEquals("encorrect number of rows", 1,model.getRowCount());
			}
		});
	}
	
	
}
