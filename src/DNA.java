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
        final int RADIX = 4;

        int length = STR.length();

        // Horners method to find the hash value of the STR and the first possible sequence
        int str = 0;

        for (int i = 0; i < length; i++) {
            str = (str * RADIX + map[STR.charAt(i)]);
        }

        int seqHash = 0;

        for (int i = 0; i < length; i++) {
            seqHash = (seqHash * RADIX + map[sequence.charAt(i)]);

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
            // Get its hash value
            seqHash = singleHash(seqHash, map[sequence.charAt(i - length)], map[sequence.charAt(i)], length);
            String test = sequence.substring(i - length, i);
            // Reigning champ to keep track of count
            if (seqHash == str) {
                count++;
                if (count > maxCount) {
                    maxCount = count;
                }
            } else {
                count = 0;
            }

        }


        return maxCount;
    }

    // Method to return new hash if the box is moved
    // Subtract the value of the last letter, shift it by 2 bits, and add the new letter.
    public static int singleHash(int seqHash, int lastletter, int newLetter, int length) {
//        seqHash -= (lastletter << ((length - 1) * 2));
        seqHash = seqHash << (32 - (2 * length));
        seqHash = seqHash >> (32 - (2 * length)) - 2;
        seqHash += newLetter;

        return seqHash;
    }


}
