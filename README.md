# HillCypher

This is a functional hill cipher. To encrypt words, instantiate a HillCypher object with a key word. Then,
call the EncryptWord method on the HillCypher object, with the word you wish to encrypt as a parameter. 
To decrypt a message, call the DecryptWord method on the same HillCypher object, since the unique key
is necessary to decrypt the message. Note, encryption requires an even number of letters in the input word,
so a random letter will be added to the end of any word that is odd, so don't be surprised when you
encrypt "hiThere" and get back "hitherex".

Only takes in letters of the alphabet, no other characters are allowed.
