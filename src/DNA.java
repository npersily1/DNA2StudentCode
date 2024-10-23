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


        // Quick lookup for character values  STR length
        int[] map = new int[128];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;


        int length = STR.length();

        final int transferConstant = (1 << (length * 2 - 2)) - 1;

        // Horners method to find the hash value of the STR and the first possible sequence
        int str = 0;

        for (int i = 0; i < length; i++) {
            str = (str << 2) + map[STR.charAt(i)];
        }

        int seqHash = 0;

        for (int i = 0; i < length; i++) {
            seqHash = (seqHash << 2) + map[sequence.charAt(i)];

        }
        // Compare the first sequence to STR
        int maxCount = 0;
        int count = 0;
        if (seqHash == str) {
            count += 1;
            maxCount += 1;
        }
        // For every letter in the sequence starting where the first possible STR is
        for (int i = length; i < sequence.length(); i++) {
            String s = sequence.substring(i - length, i);
            // Get its hash value
            seqHash = singleHash(seqHash, map[sequence.charAt(i)], transferConstant);
            System.out.println(s + "  " + seqHash);
            // Reigning champ to keep track of count
            if (seqHash == str) {
                count++;
                i += length - 1;
                if (count > maxCount) {
                    maxCount = count;
                }
                seqHash = 0;
                for (int j = 0; j < length; j++) {
                    seqHash += (seqHash << 2) + map[sequence.charAt(j + i)];
                }


            } else {
                count = 0;
            }

        }


        return maxCount;
    }

    // Method to return new hash if the box is moved
    // Subtract the value of the last letter, shift it by 2 bits, and add the new letter.
    public static int singleHash(int seqHash, int newLetter, int transferConstant) {
        seqHash = seqHash & transferConstant;
        seqHash = seqHash << 2;
        seqHash += newLetter;

        return seqHash;
    }

}
