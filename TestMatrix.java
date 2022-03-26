import java.util.*;

public class TestMatrix
{
    public static void main(String[] args)
    {
        Matrix m1 = new Matrix(new double[][]{{1.0, 1.0, 1.0}, {3.0, 2.0, 1.0}, {9.0, 4.0, 1.0}});
        System.out.println("Matrix 1 =\n" + m1);
        System.out.println("Matrix 1 echelon =\n" + m1.echelon());
        System.out.println("Matrix 1 determinate =\n" + m1.det());

        Matrix m2 = new Matrix(new double[][]{{1.0, 1.0, 1.0}, {2.0, 2.0, 2.0}, {3.0, 3.0, 3.0}});
        System.out.println("Matrix 2 =\n" + m2);
        System.out.println("Matrix product =\n" + m1.mult(m2));

    }
}
