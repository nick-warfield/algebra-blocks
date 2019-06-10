package algebrablocks;

import javax.swing.JFrame;
import java.awt.Color;
import algebrablocks.*;

public class App
{
	public static void main(String[] params)
	{
		JFrame jeff = makeFrame();
	}

	private static JFrame makeFrame()
	{
		JFrame jeff = new JFrame("Algebra Blocks!");
		jeff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jeff.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jeff.setUndecorated(true);
		jeff.setResizable(false);

		Block blk = new Block();
		blk.setLocation(720, 480);
		blk.setBackground(Color.BLUE);
		blk.setVisible(true);

		jeff.getContentPane().add(new Block());

		jeff.setVisible(true);
		return jeff;
	}
}
