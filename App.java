package algebrablocks;

import javax.swing.*;
import java.awt.*;
import algebrablocks.*;

import org.apache.commons.math3.fraction.Fraction;

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

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		AdditionQuantity a1 = new AdditionQuantity();
		a1.insert(new Block(new Fraction(1), "x"));
		a1.insert(new Block(new Fraction(8)));

		AdditionQuantity a2 = new AdditionQuantity();
		a2.insert(new Block(new Fraction(15)));

		EqualQuantity eq = new EqualQuantity(a1, a2);

		JPanel mainPane = new JPanel(new BorderLayout());
		mainPane.setEnabled(true);
		mainPane.add(eq, BorderLayout.CENTER);
		eq.Place();

		jeff.setContentPane(mainPane);
		jeff.setEnabled(true);
		jeff.setVisible(true);
		return jeff;
	}
}
