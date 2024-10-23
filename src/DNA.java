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


        int[] map = new int[128];
        map['A'] = 0;
        map['C'] = 1;
        map['G'] = 2;
        map['T'] = 3;

        int length = STR.length();

        int str = 0;


        for (int i = 0; i < length; i++) {
            str = (str * 4 + map[STR.charAt(i)]);
        }


        int seqHash = 0;
        for (int i = 0; i < length; i++) {
            seqHash = (seqHash * 4 + map[sequence.charAt(i)]);

        }
        // Compare first
        int maxCount = 0;
        int count = 0;
        if (seqHash == str) {
            count += 1;
            maxCount += 1;
        }

        for (int i = length; i < sequence.length(); i++) {
            seqHash = singleHash(seqHash, map[sequence.charAt(i-length)],map[sequence.charAt(i)],length);
            String test = sequence.substring(i-length,i);
            if(seqHash == str) {
                count++;
                if(count > maxCount) {
                    maxCount = count;
                }
            }
            else
            {
                count = 0;
            }

        }


        return maxCount;
    }
    public static int singleHash(int seqHash,int lastletter, int newLetter, int length)
    {
        seqHash -= (lastletter << ((length - 1) * 2));
        seqHash = seqHash << 2;
        seqHash += newLetter;

        return seqHash;
    }


}
