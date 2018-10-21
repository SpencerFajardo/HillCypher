package HillCypherMethods;

import java.util.ArrayList;

import static HillCypherMethods.Matrix.MatrixToArr;
import static HillCypherMethods.Matrix.InverseKey;

public class HillCypher
{
    private Matrix key;
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Default constructor for the Hill Cypher object
     */
    public HillCypher()
    {
        key = null;
    }

    /**
     * This is the constructor method for a hill cypher.
     * It takes in a string, and uses the first four letters as the
     * 2x2 matrix key. If the word is less than 4 letters, it will add letters
     * to fill up the matrix.
     * @param k       The word you want to be the key for the cypher
     */
    public HillCypher(String k)
    {
        // make the word lowercase
        k.toLowerCase();

        // the key can only be 4 letters long,
        while(k.length() < 4)
        {
            k = k + "b";
        }

        char[] word = k.toCharArray();




        int[][] init = new int[2][2];
        init[0][0] = alphabet.indexOf(word[0]);
        init[0][1] = alphabet.indexOf(word[1]);
        init[1][0] = alphabet.indexOf(word[2]);
        init[1][1] = alphabet.indexOf(word[3]);

        key = new Matrix(init);

        try
        {
            Matrix inverseKey = InverseKey(key);
        }catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Not a valid key, please try again");
        }


    }

    /**
     * This method takes in a word as a string, and returns an encrypted
     * word using the hill cypher's key.
     * @param word                      The word to encrypt,
     *                                  only takes in characters from the alphabet.
     * @return                          Returns an encrypted message.
     */
    public String EncryptWord(String word)
    {
        word = word.toLowerCase();

        ArrayList<Character> arr = new ArrayList<>();

        for(int i = 0; i < word.length(); i++)
        {
            arr.add(word.charAt(i));
        }

        if(arr.size() % 2 != 0)
        {
            arr.add('a');
        }

        int[][] init = new int[2][arr.size()/2];

        for(int i = 0; i < arr.size(); i+=2)
        {
            init[0][i/2] = alphabet.indexOf(arr.get(i));
        }

        for(int i = 1; i < arr.size(); i+=2)
        {
            init[1][i/2] = alphabet.indexOf(arr.get(i));
        }


        Matrix wordMat = new Matrix(init);

        Matrix encrypt = this.key.times(wordMat);

        init = MatrixToArr(encrypt);

        String secret = "";


        int alternate = 1;
        for(int i = 0; i < arr.size(); i++)
        {
            if(alternate == 0)
            {
                alternate = 1;
            }
            else
                {
                    alternate = 0;
                }
            secret = secret + alphabet.charAt(init[alternate][i/2]);
        }


        return secret;

    }

    /**
     * This method takes an encrypted word, and deciphers it
     * using the hill cypher's key.
     * @param word                 The word you wish to decrypt,
     *                             only takes in characters from the alphabet.
     * @return                     Returns the decrypted message.
     */
    public String DecryptWord(String word)
    {
        word = word.toLowerCase();

        ArrayList<Character> arr = new ArrayList<>();

        for(int i = 0; i < word.length(); i++)
        {
            arr.add(word.charAt(i));
        }

        if(arr.size() % 2 != 0)
        {
            arr.add('a');
        }

        int[][] init = new int[2][arr.size()/2];

        for(int i = 0; i < arr.size(); i+=2)
        {
            init[0][i/2] = alphabet.indexOf(arr.get(i));
        }

        for(int i = 1; i < arr.size(); i+=2)
        {
            init[1][i/2] = alphabet.indexOf(arr.get(i));
        }


        Matrix wordMat = new Matrix(init);

        Matrix inverseKey = InverseKey(this.key);
        Matrix encrypt = inverseKey.times(wordMat);

        init = MatrixToArr(encrypt);

        String secret = "";


        int alternate = 1;
        for(int i = 0; i < arr.size(); i++)
        {
            if(alternate == 0)
            {
                alternate = 1;
            }
            else
            {
                alternate = 0;
            }
            secret = secret + alphabet.charAt(init[alternate][i/2]);
        }


        return secret;
    }

    /**
     * This is a driver method, showing the utility of the Hill Cypher's
     * encrypt and decrypt methods.
     */
    public static void main(String[] args)
    {
        HillCypher hillCypher1 = new HillCypher("hill");
        System.out.println(hillCypher1.EncryptWord("shortExample"));
        System.out.println(hillCypher1.DecryptWord("APADJTFTWLFJ"));
    }
}
