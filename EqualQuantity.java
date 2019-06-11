package algebrablocks;

import java.util.Vector;
import javax.swing.JLabel;
import java.lang.UnsupportedOperationException;
import algebrablocks.Quantity;
import algebrablocks.AdditionQuantity;

import org.apache.commons.math3.fraction.Fraction;

public class EqualQuantity extends Quantity
{
	public EqualQuantity(Quantity lhs, Quantity rhs)
	{
		super();
		subQuantities = new Vector<Quantity>();
		subQuantities.add(lhs);
		subQuantities.add(rhs);
	}

	public final void Place()
	{
		removeAll();

		subQuantities.get(0).Place();
		add(subQuantities.get(0));
		add(new JLabel(" = "));
		subQuantities.get(1).Place();
		add(subQuantities.get(1));
	}

	protected final void merge(Block dest, Block src)
		throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
	
	protected final void promote(Block blk, Quantity qnt)
	{
		Quantity other = subQuantities.firstElement();
		if (qnt == other) { other = subQuantities.get(1); }
		if (qnt instanceof AdditionQuantity)
		{
			if (other instanceof AdditionQuantity)
			{
				qnt.delete(blk);
				blk.setCoefficient(blk.getCoefficient().multiply(-1));
				other.insert(blk);
			}
		}
	}

	protected final void demote(Block blk, Quantity qnt)
	{

	}
}
