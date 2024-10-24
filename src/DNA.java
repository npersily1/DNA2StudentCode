/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 * </p>
 * <p>
 * Completed by: [Noah]
 * </p>
 */

public class DNA {

    public static int STRCount(String sequence, String STR) {


        // Quick lookup for character values in the STR and the length of the STR
        int[] map = new int[128];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;


        int length = STR.length();
        int sequenceLength = sequence.length();
        // Bit mask for rabin
        final int BIT_MASK = (1 << (length * 2 - 2)) - 1;

        // Horners method to find the hash value of the STR and the first possible sequence
        int str = 0;

        for (int i = 0; i < length; i++) {
            str = (str << 2) + map[STR.charAt(i)];
        }

        int seqHash = 0;

        for (int i = 0; i < length; i++) {
            seqHash = (seqHash << 2) + map[sequence.charAt(i)];

        }
        
        

        int maxCount = 0;
        int count = 0;

        // For every letter in the sequence starting where the first possible STR is
        for (int i = length; i < sequenceLength; i++) {

            // Reigning champ to keep track of count
            if (seqHash == str) {
                maxCount = Math.max(++count, maxCount);
                
                // Make a new hash via horners method and increment i so when the loop updates i length places will have been skipped
                seqHash = 0;
                i += length - 1;
                if(i > sequenceLength) {
                    return maxCount;
                }
                for (int j = length; j > 0; j--) {
                    seqHash = (seqHash << 2) + map[sequence.charAt(i - j + 1)];
                }


            } else {
                // If it is not a match set streak to zero and excecute Rabin Karp
                count = 0;
                seqHash = singleHash(seqHash, map[sequence.charAt(i)], BIT_MASK);
            }
        }
        
        // Check if the last sequence is a str and update if neccesary
        if(seqHash == str) {
            maxCount = Math.max(++count, maxCount);
        }


        return maxCount;
    }

    // Method to return new hash if the box is moved
    // Subtract the value of the last letter, shift it by 2 bits, and add the new letter.
    public static int singleHash(int seqHash, int newLetter, int BIT_MASK) {
        seqHash = seqHash & BIT_MASK;
        seqHash = seqHash << 2;
        seqHash += newLetter;

        return seqHash;
    }

}
