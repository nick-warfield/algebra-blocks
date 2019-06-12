package algebrablocks;

import java.util.Vector;
import javax.swing.JLabel;
import algebrablocks.Quantity;
import org.apache.commons.math3.fraction.Fraction;

public class AdditionQuantity extends Quantity
{
	public AdditionQuantity()
	{
		super();
		blocks = new Vector<Block>();
		subQuantities = new Vector<Quantity>();
	}

	public final void Place()
	{
		removeAll();

		Block blk = new Block();
		blk.Parent(this);
		add(new Block(blk));

		add(blocks.get(0));
		for (int i = 1; i < blocks.size(); i++)
		{
			add(new JLabel(" + "));
			add(blocks.get(i));
		}
		add(new Block(blk));
	}

	protected final void merge(Block dest, Block src)
	{
		if (dest.getCoefficient() != src.getCoefficient())
		{
			throw new ArithmeticException("Cannot combine unlike terms");
		}
		dest.setCoefficient(dest.getCoefficient().add(src.getCoefficient()));
		blocks.remove(src);
	}
	
	protected final void promote(Block blk, Quantity qnt)
	{

	}

	protected final void demote(Block blk, Quantity qnt)
	{

	}
}
