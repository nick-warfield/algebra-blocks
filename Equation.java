package algebrablocks;

import java.util.Vector;
import algebrablocks.Block;
import algebrablocks.Quantity;

public class Equation
{
	private Quantity root;

	public Equation()
	{
		root = new Quantity();
		root.setLeft(new Quantity());
		root.setRight(new Quantity());
		root.setRule(RULES.EQUAL);
	}
	public Equation(String problem)
	{
		root = new Quantity();
		root.setLeft(new Quantity());
		root.setRight(new Quantity());
		root.setRule(RULES.EQUAL);

		// do this later, make some assumptions to get it up fast
	}

	public void CreateBlock(Block blk, int xPos)
	{
		createBlk(blk, root, xPos);
	}
	private void createBlk(Block blk, Quantity qnt, int xPos)
	{
		if (qnt.getBlock() != null)
		{
			if (qnt.getBlock().getX() - xPos 
		}
		createBlk(blk, qnt.getLeft(), xPos);
		createBlk(blk, qnt.getRight(), xPos);
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
