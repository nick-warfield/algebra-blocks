public class Fraction
{
	private int numerator = 1;
	private int denominator = 1;

	public Fraction()
	{
		numerator = 1;
		denominator = 1;
	}
	public Fraction(int num)
	{
		numerator = num;
		denominator = 1;
	}
	public Fraction(int num, int denom)
	{
		numerator = num;
		denominator = denom;
	}
	public Fraction(float num)
	{

	}

	public int Numerator() { return numerator; }
	public int Denominator() { return denominator; }
	public void Numerator(int num) { numerator = num; }
	public void Denominator(int denom) { denominator = denom; }

	public void Simplify() { }
	public void Scale(float scaleFactor)
	{

	}

	public void Add(Fraction other)
	{
		if (denominator == other.Denominator())
		{
			numerator += other.Numerator();
		}
	}
	public void Multiply(Fraction other)
	{
		numerator *= other.Numerator();
		denominator *= other.Denominator();
	}
}
