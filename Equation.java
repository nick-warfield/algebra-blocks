import java.util.Vector;

public class Equation
{
	private Vector<Block> blocks;

	public void CreateBlock(Block b)
	{

	}
	public void DestroyBlock(Block b)
	{

	}

	public Block Select(int pos)
	{
		Block selected = null;
		for (Block b : blocks)
		{
			if (b.Position() == pos) { selected = b; }
		}
		return selected;
	}
	public void Deselect() { }
}
