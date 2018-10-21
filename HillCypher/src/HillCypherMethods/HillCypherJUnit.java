package HillCypherMethods;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HillCypherJUnit extends HillCypher {

    // Testing class for Hill Cypher methods

    @Test
    void EncryptWord()
    {
        // tests hill cypher with key 4 letters long
        HillCypher hc = new HillCypher("hill");
        assertEquals("apadjtftwlfj", hc.EncryptWord("shortexample"));

        // Tests hill cypher with key less than 4 letters
        HillCypher hc2 = new HillCypher("bee");
        assertEquals("ubevjcxoulbw", hc2.EncryptWord("shortexample"));

        // Tests hill cypher with key longer than 4 letters
        // Adds an 'a' a the end of "HiThere", since it is an odd number of letters
        HillCypher hc3 = new HillCypher("happy");
        assertEquals("xrdacdci", hc3.EncryptWord("HiThere"));

        // Testing a hillCypher with invalid key
        boolean thrown = false;

        try
        {
            HillCypher hc4 = new HillCypher("days");
        }catch(IllegalArgumentException e)
        {
            thrown = true;
        }

        assertEquals(true, thrown);


    }

    @Test
    void DecryptWord()
    {

        HillCypher hc = new HillCypher("hill");
        assertEquals("shortexample", hc.DecryptWord("apadjtftwlfj"));

        HillCypher hc2 = new HillCypher("bee");
        assertEquals("shortexample", hc2.DecryptWord("ubevjcxoulbw"));

        HillCypher hc3 = new HillCypher("happy");
        assertEquals("hitherea", hc3.DecryptWord("xrdacdci"));
    }
}
