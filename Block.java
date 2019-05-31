public class Block
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

	public void Merge(Block other)
	{

	}
	public Block Split()
	{
		return new Block();
	}

	private void Add(Block other) { }
	private void Multiply(Block other) { }
}
