public class TestComplex
{
    public static void main(String[] args)
    {
        Complex first = new Complex(3, 4);
        System.out.println(first);
        Complex second = new Complex(-5, 2);
        System.out.println(second);
        System.out.println("First complex");
        System.out.println("================");
        System.out.println("Value: " + first);
        System.out.println("Real: " + first.real());
        System.out.println("Imag: " + first.imag());
        System.out.println("Mag: " + first.mag());
        System.out.println("Angle: " + first.angle());
        System.out.println();
        System.out.println("Conj: " + first.conj());
        System.out.println("Recip: " + first.recip());
        System.out.println();
        System.out.println("First = Second: " + first.equals(second));
        System.out.println("First + Second: " + first.add(second));
        System.out.println("First - Second: " + first.sub(second));
        System.out.println("First * Second: " + first.mult(second));
        System.out.println("First / Second: " + first.div(second));
        System.out.println();
        System.out.println("Scaled by 5: " + first.scale(5));
        System.out.println("e^x: " + first.exp());
        System.out.println("Natural log: " + first.ln());
        System.out.println("log base 3: " + first.log(3));
        System.out.println("First^Second: " + first.pow(second));
        System.out.println();
        System.out.println("sin(First): " + first.sin());
        System.out.println("cos(First): " + first.cos());
        System.out.println("tan(First): " + first.tan());

    }
}
