package algebrablocks;

import java.util.Vector;
import algebrablocks.Block;

public abstract class Quantity
{
	protected Vector<Block> blocks;
	protected Vector<Quantity> subQuantities;

	public final void Merge(Block destination, Block source)
			throws IllegalArgumentException
	{
		if (!blocks.contains(destination) || !blocks.contains(source))
		{
			throw new IllegalArgumentException("Can only Merge contained blocks");
		}
		merge(destination, source);
	}
	protected abstract void merge(Block dest, Block src);
	
	public final void Promote(Block blk, Quantity source)
			throws IllegalArgumentException
	{
		if (!subQuantities.contains(source))
		{
			throw new IllegalArgumentException("Can only Promote from " +
					"a direct sub-quantity source");
		}
		if (!source.contains(blk))
		{
			throw new IllegalArgumentException("Selected block must be " +
					"contained by the source sub-quantity");
		}
		promote(blk, source);
	}
	protected abstract void promote(Block blk, Quantity qnt);

	public final void Demote(Block blk, Quantity destination)
			throws IllegalArgumentException
	{
		if (!subQuantities.contains(destination))
		{
			throw new IllegalArgumentException("Can only Demote demote " +
					"into a direct sub-quantity");
		}
		if (!blocks.contains(blk))
		{
			throw new IllegalArgumentException("Selected block must be " +
					"contained by the parent quantity");
		}
		demote(blk, destination);
	}
	protected abstract void demote(Block blk, Quantity qnt);

	public final boolean contains(Block blk)
	{
		return blocks.contains(blk);
	}
	public final boolean contains(Quantity qnt)
	{
		return subQuantities.contains(qnt);
	}

	public final void add(Block blk) { blocks.add(blk); }
	public final void add(Quantity qnt) { subQuantities.add(qnt); }
	public final void remove(Block blk) { blocks.remove(blk); }
	public final void remove(Quantity qnt) { subQuantities.remove(qnt); }
}
