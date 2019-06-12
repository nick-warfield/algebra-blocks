package algebrablocks;

import javax.swing.border.LineBorder;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import algebrablocks.Block;

public abstract class Quantity extends JPanel
{
	protected Quantity parent;
	protected Vector<Block> blocks;
	protected Vector<Quantity> subQuantities;

	public Quantity()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setEnabled(true);
		setVisible(true);
	}
	
	public abstract void Place();

	public final void Merge(Block destination, Block source)
			throws IllegalArgumentException
	{
		destination.setBorder(new LineBorder(destination.getBackground()));
		source.setBorder(new LineBorder(source.getBackground()));
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
		blk.setBorder(new LineBorder(blk.getBackground()));
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
		blk.setBorder(new LineBorder(blk.getBackground()));
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

	public final void Parent(Quantity qnt) { parent = qnt; }
	public final Quantity Parent() { return parent; }
	public final void insert(Block blk) { blk.Parent(this); blocks.add(blk); }
	public final void insert(Quantity qnt) { qnt.Parent(this); subQuantities.add(qnt); }
	public final void delete(Block blk) { blocks.remove(blk); }
	public final void delete(Quantity qnt) { subQuantities.remove(qnt); }
}
