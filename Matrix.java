import java.lang.Math;
import java.util.Arrays;
//import java.util.String;

public class Matrix
{
    private int row;
    private int col;
    private double[][] m;
    private double[][] e;
    double q;

    public Matrix(int r, int c, double d)
    {
        this.row = r;
        this.col = c;
        this.m = new double[r][c];
        for (double[] row: this.m)
        {
            Arrays.fill(row, d);
        }
    }

    public Matrix(double[][] a)
    {
        this.m = a;
        this.row = this.m.length;
        this.col = this.m[0].length;
    }

    public double[][] getMatrix()
    {
        return this.m;
    }

    public void setMatrix(double[][] a)
    {
        this.m = a;
        this.row = this.m.length;
        this.col = this.m[0].length;
    }

    public String toString()
    {
        String out = "";
        for(int i = 0; i < this.row; i++)
        {
            String temp = "";
            for(int j = 0; j < this.col; j++)
            {
                temp = temp + this.m[i][j] + " ";
            }
            out = out + temp.substring(0, temp.length()-1) + "\n";
        }
        out = out.substring(0, out.length()-1);
        return out;
    }

    public double getVal(int i, int j)
    {
        return this.m[i][j];
    }

    public void setVal(int i, int j, double val)
    {
        this.m[i][j] = val;
    }

    public void swapRow(int a, int b)
    {
        double[] temp = this.m[a];
        this.m[a] = this.m[b];
        this.m[b] = temp;
    }

    public Matrix echelon()
    {
        this.e = this.m;
        this.q = 1.0;
        //begin row reduction
        for(int i = 0; i < this.row; i++)
        {
            //if current row begins with 0, swap it
            if(this.e[i][i] == 0)
            {
                int l = 0;
                for(int j = i+1; j < this.row; j++)
                {
                    if(this.e[j][0] != 0)
                    {
                        l = j;
                        break;
                    }
                }
                if(l == 0)
                {
                    return null;
                }
                swapRow(i, l);
                q *= -1;
            }

            // //scale the row so the first term = 1.0
            // if(this.e[i][i] != 1.0)
            // {
            //     double scalar = 1.0/this.e[i][i];
            //     for(int j = 0; j < this.col; j++)
            //     {
            //         this.e[i][j] *= scalar;
            //     }
            //     q *= 1.0/scalar;
            // }

            //substract row from others to make ith term = 0.0
            for(int j = i+1; j < this.row; j++)
            {
                double[] temp = this.e[j];
                double scalar = temp[i]/this.e[i][i];
                for(int k = i; k < this.col; k++)
                {
                    temp[k] = this.e[j][k] - (scalar * this.e[i][k]);
                }
                this.e[j] = temp;
            }
        }
        return new Matrix(this.e);
    }

    public double det()
    {
        //det(m) = q * det(echelon form)

        if(this.e == null)
        {
            echelon();
        }

        //check if matrix is square
        if(this.row != this.col)
        {
            System.out.println("Matrix is not square");
            return 0.0;
        }

        double diag = 1.0;
        for(int i = 0; i < this.row; i++)
        {
            diag *= this.e[i][i];
        }
        return q * diag;
    }
}
