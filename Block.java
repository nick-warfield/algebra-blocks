public class Block implements Comparable<Block>
{
	private int position = 0;
	private String coefficient = null;
	private Fraction number;
	private Fraction exponent;

	public Block()
	{
		number = new Fraction(0);
		exponent = new Fraction(1);
	}
	public Block(Fraction num)
	{
		number = num;
		exponent = new Fraction(1);
	}
	public Block(Fraction num, int pos)
	{
		number = num;
		exponent = new Fraction(1);
		position = pos;
	}

	public int Position() { return position; }
	public Fraction Number() { return number; }
	public Fraction Exponent() { return exponent; }
	public String Coefficient() { return coefficient; }

	public void Position(int pos) { position = pos; }
	public void Number(Fraction num) { number = num; }
	public void Exponent(Fraction exp) { exponent = exp; }
	public void Coefficient(String coeff) { coefficient = coeff; }

	public Block Split()
	{
		return new Block();
	}

	public int compareTo(Block other)
	{
		return position - other.Position();
	}

	public void Add(Block other)
	{
		int cmp = 0;
		if (coefficient == null)
		{
			if (other.Coefficient() != null) { cmp = -1; }
		}
		else
		{
			cmp = coefficient.compareTo(other.Coefficient());
		}

		if (cmp == 0)
		{
			number.Add(other.Number());
		}
		else
		{
			System.out.println("Error: Cannot combine unlike terms");
		}

	}
	public void Multiply(Block other) { }

	public String toString()
	{
		String s = number.toString();
		if (coefficient != null) { s += coefficient; }
		return s;
	}
}
