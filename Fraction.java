package algebrablocks;

import java.lang.Exception.*;

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
	public Fraction(int num, int denom) throws ArithmeticException
	{
		if (denom == 0) { throw new ArithmeticException("Denominator cannot be 0"); }
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
	public void Denominator(int denom) throws ArithmeticException
	{
		if (denom == 0) { throw new ArithmeticException("Denominator cannot be 0"); }
		denominator = denom;
	}

	// rewrite
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

	public void Simplify()
	{
		
	}

	public Fraction Scale(int scaleFactor) throws ArithmeticException
	{
		return new Fraction(Numerator() * scaleFactor, Denominator() * scaleFactor);
	}

	public static Fraction Add(Fraction lhs, Fraction rhs)
	{
		Fraction lTemp = lhs, rTemp = rhs;
		if (lTemp.Denominator() != rTemp.Denominator())
		{
			lTemp = lTemp.Scale(rTemp.Denominator());
			rTemp = rTemp.Scale(lTemp.Denominator());
		}
		lTemp.Numerator(lTemp.Numerator() + rTemp.Numerator());
		return lTemp;
	}

	public static Fraction Multiply(Fraction lhs, Fraction rhs)
	{
		return new Fraction(lhs.Numerator() * rhs.Numerator(), lhs.Denominator() * rhs.Denominator());
	}
	
	public String toString()
	{
		String s = Integer.toString(numerator);
		if (denominator != 1 && numerator != 0)
		{
			s += '/' + Integer.toString(denominator);
		}
		return s;
	}
}
