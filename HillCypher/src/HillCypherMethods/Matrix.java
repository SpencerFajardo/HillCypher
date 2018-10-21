package HillCypherMethods;

public class Matrix {
    private int numRows;
    private int numColumns;
    private int data[][];
    
    /**
     * Default constructor method for the Matrix object.
     */
    public Matrix()
    {
        numRows = 0;
        numColumns = 0;
        data = null;
    }

    /**
     * Overloaded constructor method for the Matrix object.
     * Initialized with a two dimensional array with the data you wish
     * to be in the matrix.
     * @param d            the two dimensional array with the relevant data
     */
    public Matrix(int d[][])
    {
        numRows = d.length; // d.length is the number of 1D arrays in the 2D array
        if(numRows == 0)
            numColumns = 0;
        else
            numColumns = d[0].length; // d[0] is the first 1D array
        data = new int[numRows][numColumns]; // create a new matrix to hold the data
        // copy the data over
        for(int i=0; i < numRows; i++)
            for(int j=0; j < numColumns; j++)
                data[i][j] = d[i][j];
    }

    /**
     * This method takes in a matrix m, and returns a two dimensional array
     * holding the same data as the matrix. This is helpful when encrypting
     * and decrypting a message.
     * @param m         the matrix you wish to have as a two dimensional array
     * @return          returns a two dimensional array holding the same data as m
     */
    public static  int[][] MatrixToArr(Matrix m)
    {
        int[][] returnArr = new int[m.numRows][m.numColumns];
        for(int i = 0; i < m.numRows; i++)
        {
            for(int j = 0; j < m.numColumns; j++)
            {
                returnArr[i][j] = m.data[i][j];
            }
        }

        return returnArr;
    }

    @Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
    public boolean equals(Object o)
    {
        if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
            return false;
        Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

        if(this.toString().equals(o.toString()) == true)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
    public String toString()
    {
        String returnString = "";
        for(int i = 0; i <this.numRows; i++)
        {
            for(int j = 0; j < this.numColumns; j++)
            {
                returnString = returnString + data[i][j] + " ";
            }

            returnString = returnString + "\n";
        }
        return returnString;
    }

    /**
     * This method computes matrix multiplication. For the purpose of this cypher,
     * the matrix multiplication is then taken mod 26.
     * @param m          the matrix to be multiplied by this matrix.
     * @return           returns a new matrix that is the result of the multiplication.
     */
    public Matrix times(Matrix m)
    {
        // If the dimensions of the matrices are incompatible
        // for multiplication, return null
        if(this.numColumns != m.numRows)
        {
            return null;
        }

        // If the dimensions are compatible, compute the new matrix
        // that results from multiplication.
        Matrix multMatrix = new Matrix(new int[this.numRows][m.numColumns]);
        for(int i = 0; i < this.numRows; i++)
        {

            for(int j = 0; j < m.numColumns; j++)
            {
                int total = 0;
                for(int k = 0; k < this.numColumns; k++)
                {
                    total = total + this.data[i][k] * m.data[k][j];
                }
                multMatrix.data[i][j] = total % 26;
            }
        }

        return multMatrix;


    }

    /**
     * This method calculates the determinant of a 2x2 matrix.
     * If the matrix is not 2x2, it returns an error.
     * @param m      the 2x2 matrix we wish to compute the determinant of
     * @return       returns the double value of the determinant.
     **/
    public static int Determinant(Matrix m)
    {
        // If the dimensions are incorrect, throw an IllegalArgumentException
        if(m.numRows != m.numColumns || m.numRows != 2)
        {
            throw new IllegalArgumentException("Matrix is not 2x2");
        }

        // If the dimension match, calculate the determinant mod 26
        int result = (m.data[0][0] * m.data[1][1] - m.data[0][1] * m.data[1][0]) % 26;
        if (result < 0)
        {
            result = 26 - Math.abs(result);
        }
        return result;
    }

    /**
     * This method finds the inverse matrix of a 2x2 matrix, if one exists.
     * If the matrix is not invertible, it throws an IllegalArgumentException.
     * @param key       the matrix that needs to be inverted. In our case,
     *                  this matrix will always be the key for the cypher.
     * @return          returns a new matrix that is the inverse of key
     */
    public static Matrix InverseKey(Matrix key)
    {

        // Computes the determinant of key
        int det = Determinant(key);

        // this block of code checks for the multiplicative
        // inverse of the key matrix mod 26
        int multInverse = -1;
        for(int i = 1; i < 27; i++)
        {
            if( (i * det) % 26 == 1)
            {
                multInverse = i;
            }
        }

        // if the multiplicative inverse does not exist, throws an error
        if(multInverse == -1)
        {
            throw new IllegalArgumentException("Determinant does not have multiplicative Inverse");
        }

        // This block computes the adjugate matrix mod 26
        int a = key.data[0][0];
        int b = key.data[0][1];
        int c = key.data[1][0];
        int d = key.data[1][1];

        int newA = d % 26;
        int newB = -b % 26;
        if (newB < 0)
        {
            newB = 26 - Math.abs(newB);
        }
        int newC = -c % 26;
        if (newC < 0)
        {
            newC = 26 - Math.abs(newC);
        }
        int newD = a % 26;


        // this code multiplies the adjugate matrix by the
        // key matrix's multiplicative inverse mod 26
        int finalA = (newA * multInverse) % 26;
        int finalB = (newB * multInverse) % 26;
        int finalC = (newC * multInverse) % 26;
        int finalD = (newD * multInverse) % 26;

        // initialize a new inverse Matrix and return it
        int[][] init = new int[2][2];
        init[0][0] = finalA;
        init[0][1] = finalB;
        init[1][0] = finalC;
        init[1][1] = finalD;

        return new Matrix(init);

    }
}
