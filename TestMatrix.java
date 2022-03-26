import java.util.*;

public class TestMatrix
{
    public static void main(String[] args)
    {
        Matrix m = new Matrix(3, 3, 0.0);
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                m.setVal(i, j, Math.pow(i+1, 2) + 2*j + (i*j) +i);
            }
        }
        m.setVal(1, 1, -5);
        System.out.println(m.toString());
        System.out.println(m.det());
        System.out.println(new Matrix(m.echelon()).toString());
    }
}
