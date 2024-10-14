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
        map['A'] = 0b00000000;
        map['C'] = 0b00000001;
        map['G'] = 0b00000010;
        map['T'] = 0b00000011;

        int length = STR.length();

        int str = 0;

        for (int i = 0; i < length; i++) {
            str = str | map[STR.charAt(i)];
            str = str << 2;
        }
        int temp = 0;
        for (int i = 0; i < length; i++) {
            temp = temp | map[STR.charAt(i)];
            temp = temp << 2;
        }
        int maxCount = 0;
        int count = 0;
        if (temp == str) {
            count += 1;
            maxCount += 1;
        }

        for (int i = length; i < sequence.length(); i++) {
            temp = temp << 2;
            temp = temp | map[sequence.charAt(i)];
            if(temp == str) {
                maxCount = Math.max(maxCount, ++count);
                temp = temp << (length - 2);
            }
            else {
                count = 0;
            }
        }


        return maxCount;
    }


}
