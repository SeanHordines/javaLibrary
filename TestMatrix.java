import java.util.*;

public class TestMatrix
{
    public static void main(String[] args)
    {
        double[][] temp = {{1.0, 1.0, 1.0}, {3.0, 2.0, 1.0}, {9.0, 4.0, 1.0}};
        Matrix m = new Matrix(temp);
        System.out.println(m);
        System.out.println(m.echelon());
        System.out.println(m.det());
    }
}
