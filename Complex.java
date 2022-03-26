import java.lang.Math;

public class Complex
{
    private double r;
    private double i;

    public Complex(double real, double imag)
    {
        this.r = real;
        this.i = imag;
    }

    public String toString()
    {
        //puts the number in the form a+bi
        String sign = "";
        if(this.i >= 0)
        {
            sign = "+";
        }
        return this.r + sign + this.i + "i";
    }

    public double real()
    {
        //returns the real component of the number
        return this.r;
    }

    public double imag()
    {
        //returns the imaginary component of the number
        return this.i;
    }

    public double mag()
    {
        //returns the magnitude of the vector
        return Math.sqrt(Math.pow(this.r, 2) + Math.pow(this.i, 2));
    }

    public double angle()
    {
        //returns the angle of the vector
        return Math.atan(this.i / this.r);
    }

    public Complex conj()
    {
        //returns the complex conjugate of the number
        return new Complex(this.r, -this.i);
    }

    public Complex recip()
    {
        //returns the complex reciprocal of the number
        double mag = Math.pow(this.mag(), 2);
        Complex conj = this.conj();
        return new Complex(conj.real()/mag, conj.imag()/mag);
    }

    public boolean equals(Complex other)
    {
        return (this.r == other.real()) && (this.i == other.imag());
    }

    public Complex add(Complex addend)
    {
        //returns the sum of two complex addends
        return new Complex(this.r + addend.real(), this.i + addend.imag());
    }

    public Complex sub(Complex subtrahend)
    {
        //returns the result of the minuend minus the subtrahend
        return new Complex(this.r - subtrahend.real(), this.i - subtrahend.imag());
    }

    public Complex mult(Complex factor)
    {
        //returns the product of two complex factors
        double real = (this.r * factor.real()) - (this.i * factor.imag());
        double imag = (this.r * factor.imag()) + (this.i * factor.real());
        return new Complex(real, imag);
    }

    public Complex div(Complex divisor)
    {
        //returns the quotient of the numerator divided by the divisor
        return this.mult(divisor.recip());
    }

    public Complex scale(double scaleFactor)
    {
        //returns the complex number scaled by some factor
        return new Complex(scaleFactor * this.r, scaleFactor * this.i);
    }

    public Complex exp()
    {
        //returns the result of e^(a+bi)
        return new Complex(Math.exp(this.r) * Math.cos(this.i), Math.exp(this.r) * Math.sin(this.i));
    }

    public Complex ln()
    {
        //returns the result of ln|a+bi|
        return new Complex(Math.log(this.mag()), this.angle());
    }

    public Complex log(double base)
    {
        //returns the result of log base b of |a+bi|
        return this.ln().scale(1 / Math.log(base));
    }

    public Complex pow(Complex exponent)
    {
        //returns the result of (a+bi)^(c+di)
        return exponent.mult(this.ln()).exp();
    }

    public Complex sin()
    {
        //returns the result of sin(a+bi)
        return new Complex(Math.sin(this.r) * Math.cosh(this.i), Math.cos(this.r) * Math.sinh(this.i));
    }

    public Complex cos()
    {
        //returns the result of cos(a+bi)
        return new Complex(Math.cos(this.r) * Math.cosh(this.i), -Math.sin(this.r) * Math.sinh(this.i));
    }

    public Complex tan()
    {
        //returns the result of tan(a+bi)
        return this.sin().div(this.cos());
    }
}
