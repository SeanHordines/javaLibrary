import java.lang.Math;
import java.util.Arrays;
//import java.util.String;

public class Matrix
{
    private int row;
    private int col;
    private double[][] m;
    private double[][] e;
    private double q;

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

    public Matrix scale(double s)
    {
        Matrix out = new Matrix(this.row, this.col, 0.0);
        for(int i = 0; i < out.row; i++)
        {
            for(int j = 0; j < out.col; j++)
            {
                out.m[i][j] = s*this.m[i][j];
            }
        }
        return out;
    }

    public void swapRow(int a, int b)
    {
        double[] temp = this.m[a];
        this.m[a] = this.m[b];
        this.m[b] = temp;
    }

    public Matrix echelon()
    {
        //copy values of m into e
        this.e = new double[this.row][this.col];
        for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.col; j++)
            {
                this.e[i][j] = this.m[i][j];
            }
        }

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
                double scalar = 1/this.e[i][i];
                for(int k = i; k < this.col; k++)
                {
                    this.e[j][k] = this.e[j][k] - (scalar * this.e[i][k]);
                }
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

    public Matrix transpose()
    {
        Matrix out = new Matrix(this.col, this.row, 0.0);
        for(int i = 0; i < out.row; i++)
        {
            for(int j = 0; j < out.col; j++)
            {
                out.m[i][j] = this.m[j][i];
            }
        }
        return out;
    }

    public Matrix invert()
    {
        //set up copy of matrix augmented witht he identity matrix
        Matrix left = new Matrix(this.col, this.row, 0.0);
        Matrix right = new Matrix(this.col, this.row, 0.0);
        for(int i = 0; i < left.row; i++)
        {
            for(int j = 0; j < left.col; j++)
            {
                left.m[i][j] = this.m[i][j];
                if(i == j)
                {
                    right.setVal(i, j, 1.0);
                }
            }
        }

        //put into echelon form
        for(int i = 0; i < left.row; i++)
        {
            //if current row begins with 0, swap it
            if(left.m[i][i] == 0)
            {
                int l = 0;
                for(int j = i+1; j < left.row; j++)
                {
                    if(left.m[j][0] != 0)
                    {
                        l = j;
                        break;
                    }
                }
                if(l == 0)
                {
                    return null;
                }
                left.swapRow(i, l);
                right.swapRow(i, l);
            }

            //scale the row so the first term = 1.0
            if(left.m[i][i] != 1.0)
            {
                double scalar = 1.0/left.m[i][i];
                for(int j = 0; j < left.col; j++)
                {
                    left.m[i][j] *= scalar;
                    right.m[i][j] *= scalar;
                }
            }

            //substract row from others to make ith term = 0.0
            for(int j = i+1; j < left.row; j++)
            {
                double scalar = 1/left.m[i][i];
                for(int k = i; k < left.col; k++)
                {
                    left.m[j][k] = left.m[j][k] - (scalar * left.m[i][k]);
                    right.m[j][k] = right.m[j][k] - (scalar * right.m[i][k]);
                }
            }
        }

        //reduce to identity
        for(int i = 0; i < left.row; i++)
        {
            for(int j = i+1; j < left.col; j++)
            {
                double scalar = 1.0/left.m[i][j];
                for(int k = j; k < left.col; k++)
                {
                    left.m[i][k] -= scalar * left.m[j][k];
                    right.m[i][k] -= scalar * right.m[i][k];
                }
            }
        }
        //System.out.println(left);
        //System.out.println(right);
        return right;
    }

    public Matrix mult(Matrix other)
    {
        if(this.col == other.row)
        {
            Matrix out = new Matrix(this.row, other.col, 0.0);
            for(int i = 0; i < this.row; i++)
            {
                for(int j = 0; j < other.col; j++)
                {
                    for(int k = 0; k < this.col; k++)
                    {
                        out.m[i][j] += this.m[i][k] * other.m[k][j];
                    }
                }
            }
            return out;
        }
        else
        {
            System.out.println("Matrices do not have matching dimensions");
            return null;
        }
    }
}
