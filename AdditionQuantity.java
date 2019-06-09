package algebrablocks;

import algebrablocks.Quantity;
import org.apache.commons.math3.fraction.Fraction;

public class AdditionQuantity extends Quantity
{
	protected final void merge(Block dest, Block src)
	{
		if (dest.getCoefficient() != src.getCoefficient())
		{
			throw new ArithmeticException("Cannot combine unlike terms");
		}
		dest.setNumber(dest.getNumber().add(other.getNumber()));
		blocks.remove(src);
	}
	
	protected final void promote(Block blk, Quantity qnt)
	{

	}

	protected final void demote(Block blk, Quantity qnt)
	{

	}
}
