package HillCypherMethods;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MatrixJUnit extends Matrix {

    // Testing for matrix methods that were implemented for
    // the hill cypher class.

    @Test
    void Determinant()
    {
        // Testing for correct determinant value
        Matrix m = new Matrix(new int[][] {{1,0},{0,1}});
        assertEquals(1, Determinant(m));

        // Testing for correct determinant value
        Matrix m2 = new Matrix(new int[][] {{2,1},{1,2}});
        assertEquals(3, Determinant(m2));

        // Testing a matrix that has the wrong dimensions
        Matrix m3 = new Matrix(new int[][] {{1,2,3},{1,2,3}});

        boolean thrown = false;

        try
        {
            int det = Determinant(m3);
        }
        catch(IllegalArgumentException e)
        {
            thrown = true;
        }

        assertEquals(true, thrown);

    }

    @Test
    void InverseKey()
    {
        // Testing for a matrix that has an inverse
        Matrix A = new Matrix(new int[][] {{7,8},{11,11}});
        Matrix B = new Matrix(new int[][] {{25,22},{1,23}});

        assertEquals(B, InverseKey(A));

        // Testing for a matrix that does Not have an inverse
        Matrix C = new Matrix(new int[][] {{0,1},{0,0}});
        boolean errorThrown = false;

        try
        {
            Matrix D = InverseKey(C);
        }
        catch(IllegalArgumentException e)
        {
            errorThrown = true;
        }

        assertEquals(true, errorThrown);

    }

    @Test
    void MatrixToArr()
    {
        // Test checking if you get the same array out
        // as you put in
        int [][] test = new int[][] {{1,2},{3,4}};
        Matrix A = new Matrix(test);

        int[][] result = MatrixToArr(A);
        assertEquals(true, Arrays.deepEquals(test, result));


    }

}
