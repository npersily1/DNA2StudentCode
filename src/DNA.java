/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [Noah]
 *</p>
 */

public class DNA {

    public static int STRCount(String sequence, String STR) {


        int[] map = new int[22];
        map['a'-'a'] = 0b00000000;
        map['c'-'a'] = 0b00000001;
        map['g'-'a'] = 0b00000010;
        map['t'-'a'] = 0b00000011;

        int length = STR.length();

        int temp = 0;

        for (int i = 0; i < length; i++) {
            temp = temp | map[STR.charAt(i) - 'a'];
            temp = temp << 2;
        }

        for (int i = 0; i < sequence.length(); i++) {

        }




        return 0;
    }








}
