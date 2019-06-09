package algebrablocks;

import java.util.Vector;
import java.lang.UnsupportedOperationException;
import algebrablocks.Quantity;
import algebrablocks.AdditionQuantity;

import org.apache.commons.math3.fraction.Fraction;

public class EqualQuantity extends Quantity
{
	public EqualQuantity(Quantity lhs, Quantity rhs)
	{
		subQuantities = new Vector<Quantity>();
		subQuantities.add(lhs);
		subQuantities.add(rhs);
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
				qnt.remove(blk);
				blk.setNumber(blk.getNumber().multiply(-1));
				other.add(blk);
			}
		}
	}

	protected final void demote(Block blk, Quantity qnt)
	{

	}
}
