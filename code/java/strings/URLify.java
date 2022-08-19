package code.java.strings;

public class URLify {

    static void encodeWhiteSpaces(String str, int len) {
        String replaceWhiteSpace = "%20";
        // Find White Space
        int whiteSpaceCount = 0;
        char[] str_array = str.toCharArray();
        for (char c : str_array) {
            if (c == ' ') {
                whiteSpaceCount++;
            }
        }
        System.out.println("Str_Array Length - " + str_array.length + ", Whitespace count - " + whiteSpaceCount);
        if (whiteSpaceCount % replaceWhiteSpace.length() != 0) {
            System.out.println("Couldn't encode");
        }
        StringBuilder modified_array = new StringBuilder();
        for (char c : str_array) {
            if (c == ' ' && (whiteSpaceCount > 0)) {
                modified_array.append(replaceWhiteSpace);
                whiteSpaceCount -= 3;
            } else {
                modified_array.append(c);
            }
        }
        String modifiedString = modified_array.toString();
        System.out.println("Actual String - " + str + ", Encoded String - " + modifiedString);
    }

    static void replaceSpaces(char[] str, int length) {
        int spaceCount = 0, newLength, i;
        for (i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        newLength = length + spaceCount * 2;
        for (i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[newLength - 1] = '0';
                str[newLength - 2] = '2';
                str[newLength - 3] = '%';
                newLength -= 3;
            } else {
                str[newLength - 1] = str[i];
                newLength -= 1;
            }
        }
        System.out.println("Modified String - " + new String(str));
    }

    public static void main(String[] args) {
        // encodeWhiteSpaces("Mr John Smith    ", 13);
        replaceSpaces("Mr John Smith    ".toCharArray(), 13);
    }

}
