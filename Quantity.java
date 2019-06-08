package algebrablocks;

import algebrablocks.Block;
import algebrablocks.RULES;

// a quantity can have 2 or 0 children, never 1

public class Quantity
{
	private Block blk = null;
	private Quantity left = null, right = null;
	private RULES rule = RULES.UNASSIGNED;

	public Block getBlock() { return blk; }
	public Quantity getLeft() { return left; }
	public Quantity getRight() { return right; }
	public RULES getRule() { return rule; }

	public void setBlock(Block Blk) { blk = Blk; }
	public void setLeft(Quantity Left) { left = Left; }
	public void setRight(Quantity Right) { right = Right; }
	public void setRule(RULES Rule) { rule = Rule; }
}
