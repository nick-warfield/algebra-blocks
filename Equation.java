package algebrablocks;

import javax.swing.JPanel;
import java.util.Vector;
import org.apache.commons.math3.fraction.Fraction;
import algebrablocks.Block;
import algebrablocks.Quantity;

public class Equation extends JPanel
{
	private Quantity root;

	public Equation()
	{
		AdditionQuantity lhs = new AdditionQuantity();
		AdditionQuantity rhs = new AdditionQuantity();
		lhs.add(new Block(true, new Fraction(1), "x"));
		lhs.add(new Block(true, new Fraction(7)));
		rhs.add(new Block(false, new Fraction(8)));
		root = new EqualQuantity(lhs, rhs);
	}

}

/*
public class Equation
{
	private Vector<Block> blocks;

	public Equation(Vector<Block> Blocks)
	{
		blocks = Blocks;
	}

	public void CreateBlock(Block b)
	{

	}
	public void DestroyBlock(Block b)
	{

	}

	public void Move(int start, int end)
	{
		Block b = get(start);
		if (get(end) == null)
		{
			if (start * end < 0)
			{
				b.getNumber().multiply(new Fraction(-1));
			}
			b.Position(end);
		}
		else
		{
			System.out.println("Error: Space Occupied");
		}
	}

	public void Merge(int source, int destination)
	{
		Block src = get(source), dest = get(destination);
		if (source * destination > 0 && src != null && dest != null)
		{
			dest.Add(src);
			blocks.remove(source);
		}
		else
		{
			System.out.println("Error: blocks must be on same side and valid positions");
		}
	}

	private Block get(int pos)
	{
		Block selected = null;
		for (Block b : blocks)
		{
			if (b.Position() == pos) { selected = b; }
		}
		return selected;
	}

	public String toString()
	{
		blocks.sort(null);
		String s = blocks.get(0).toString();
		for (int i = 1; i < blocks.size(); i++)
		{
			s += blocks.get(i - 1).Position() * blocks.get(i).Position() < 0 ? " = " : " + ";
			s += blocks.get(i).toString();
		}
		return s;
	}
}
*/
