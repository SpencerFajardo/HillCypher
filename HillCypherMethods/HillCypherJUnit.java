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
        String wordToEncrypt = "shortexample";
        String encryptedWord = hc.EncryptWord(wordToEncrypt);
        String unEncryptedWord = hc.DecryptWord(encryptedWord);
        assertEquals(true, unEncryptedWord.equals(wordToEncrypt));

        // Tests hill cypher with key less than 4 letters
        HillCypher hc2 = new HillCypher("bee");
        assertEquals("ubevjcxoulbw", hc2.EncryptWord("shortexample"));

        // Tests hill cypher with key longer than 4 letters
        // Adds a random letter at the end of "HiThere", since it is an odd number of letters
        // we will check if the unencrypted word minus the last character is equal to the
        // word to encrypt in lower case
        HillCypher hc3 = new HillCypher("happy");
        wordToEncrypt = "HiThere";
        encryptedWord = hc3.EncryptWord(wordToEncrypt);
        unEncryptedWord = hc3.DecryptWord(encryptedWord);
        assertEquals(true, unEncryptedWord.substring(0,unEncryptedWord.length() -1).equals(wordToEncrypt.toLowerCase()));

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
        String wordToEncrypt = "shortexample";
        String encryptedWord = hc.EncryptWord(wordToEncrypt);
        String unEncryptedWord = hc.DecryptWord(encryptedWord);
        assertEquals(true, unEncryptedWord.equals(wordToEncrypt));

        HillCypher hc2 = new HillCypher("bee");
        encryptedWord = hc.EncryptWord(wordToEncrypt);
        unEncryptedWord = hc.DecryptWord(encryptedWord);
        assertEquals(true, unEncryptedWord.equals(wordToEncrypt));

        // Testing an input word with odd number of letters.
        // If you input an odd number of letters, a random letter will
        // be added to the end of the word, since the key
        // requires an even number of letters to work.
        // Thus, we will check if the unEncryptedWord minus the last letter
        // matches the wordToEncrypt
        HillCypher hc3 = new HillCypher("happy");
        wordToEncrypt = "hithere";
        encryptedWord = hc3.EncryptWord(wordToEncrypt);
        unEncryptedWord = hc3.DecryptWord(encryptedWord);
        assertEquals(true, unEncryptedWord.substring(0,unEncryptedWord.length() - 1).equals(wordToEncrypt));
    }
}
