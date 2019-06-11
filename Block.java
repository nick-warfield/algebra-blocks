package algebrablocks;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.event.MouseInputAdapter;
import org.apache.commons.math3.fraction.Fraction;
// source: http://commons.apache.org/proper/commons-math/download_math.cgi
// docs : http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/index.html

public class Block extends JPanel implements MouseListener
{
	private Fraction coeff;
	private String variable;
	private JLabel label;

	private final void init(java.awt.Color color)
	{
		setEnabled(true);
		setBackground(color);
		setMaximumSize(new java.awt.Dimension(50, 50));
		label = new JLabel(coeff.toString() + variable);
		add(label, BorderLayout.CENTER);
		addMouseListener(this);
/*		addMouseListener(new MouseInputAdapter()
			{
				public void MousePressed(MouseEvent e)
				{
					System.out.println("Pressed!");
					setVisible(false);
				}
				public void MouseReleased(MouseEvent e)
				{
					setVisible(true);
				}
			});
*/
		setVisible(true);
	}
   
	public void mousePressed(MouseEvent e)
	{
		setVisible(false);
	}
	public void mouseReleased(MouseEvent e)
	{
		setVisible(true);
	}
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }

	public Block()
	{
		coeff = new Fraction(1);
		variable = "";
		init(java.awt.Color.CYAN);
	}
    public Block(Fraction Coefficient) throws IllegalArgumentException
	{
		if (Coefficient == null) { throw new IllegalArgumentException(); }
		coeff = Coefficient;
		variable = "";
		init(java.awt.Color.CYAN);
    }
    public Block(Fraction Coefficient, String Variable) throws IllegalArgumentException
	{
		if (Variable == null || Coefficient == null)
		{
			throw new IllegalArgumentException();
		}
		coeff = Coefficient;
		variable = Variable;
		init(java.awt.Color.GREEN);
    }
	public Block(Block blk)
	{
		coeff = blk.getCoefficient();
		variable = blk.getVariable();
		init(blk.getBackground());
	}

	public Fraction getCoefficient() { return coeff; }
	public String getVariable() { return variable; }
    
	public void setCoefficient(Fraction Coefficient)
			throws IllegalArgumentException
	{
		if (Coefficient == null) { throw new IllegalArgumentException(); }
		coeff = Coefficient;
	}
	public void setVariable(String Variable)
		throws IllegalArgumentException
	{
		if (Variable == null) { throw new IllegalArgumentException(); }
		variable = Variable;
	}

	public Block add(Block other)
	{
		if (other == null) { throw new IllegalArgumentException(); }
		if (getVariable() != other.getVariable())
		{
			throw new ArithmeticException("Cannot combine unlike terms");
		}

		Block blk = new Block(this);
		blk.setCoefficient(
				blk.getCoefficient().add(other.getCoefficient()));
		return blk;
	}
	public Block multiply(Block other)
	{
		if (other == null) { throw new IllegalArgumentException(); }
		Block blk = new Block(this);
		blk.setCoefficient(
				blk.getCoefficient().multiply(other.getCoefficient()));
		blk.setVariable(blk.getVariable() + other.getVariable());
		return blk;
	}

	public String toString()
	{
		return coeff.toString() + variable;
	}
}
