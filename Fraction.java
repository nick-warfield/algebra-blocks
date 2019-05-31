public class Fraction implements Comparable<Fraction>
{
	// it might be better to represent this as a prime factorization
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
	public Fraction(Fraction frac)
	{
		numerator = frac.Numerator();
		denominator = frac.Denominator();
	}

	public int Numerator() { return numerator; }
	public int Denominator() { return denominator; }
	public void Numerator(int num) { numerator = num; }
	public void Denominator(int denom) { denominator = denom; }

	public int compareTo(Fraction other)
	{
		if (denominator == other.Denominator())
		{
			return numerator - other.Numerator();
		}
		Fraction scaled = new Fraction(this);
		scaled.Scale(other.Denominator());
		other.Scale(this.Denominator());
		return scaled.compareTo(other);
	}

	public void Simplify() { }
	public void Scale(int scaleFactor)
	{
		numerator *= scaleFactor;
		denominator *= scaleFactor;
	}

	public void Add(Fraction other)
	{
		if (denominator == other.Denominator())
		{
			numerator += other.Numerator();
		}
		else
		{
			this.Scale(other.Denominator());
			other.Scale(this.Denominator());
			this.Add(other);
		}
	}
	public void Multiply(Fraction other)
	{
		numerator *= other.Numerator();
		denominator *= other.Denominator();
	}
}
