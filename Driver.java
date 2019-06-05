import java.util.Vector;

public class Driver
{
	public static void main(String[] args)
	{
		Vector<Block> blk = new Vector<Block>();
		blk.add(new Block(new Fraction(), -2));
		blk.get(0).Coefficient("x");
		blk.add(new Block(new Fraction(-2), -1));
		blk.add(new Block(new Fraction(5), 1));

		Equation eq = new Equation(blk);
		System.out.println(eq.toString());

		eq.Move(-1, 2);
		System.out.println(eq.toString());
		
		eq.Merge(1, 2);
		System.out.println(eq.toString());
	}
}
