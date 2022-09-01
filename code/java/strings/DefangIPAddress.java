package code.java.strings;

import java.util.function.BiConsumer;

/**
 * https://leetcode.com/problems/defanging-an-ip-address/
 */
public class DefangIPAddress {

    private static String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public static void main(String[] args) {
        BiConsumer<String, String> logger = (input, result) -> System.out.println("IpAddress - " + input + ", Modified - " + result);
        //
        String address = "1.1.1.1";
        String result = defangIPaddr(address);
        logger.accept(address, result);
        //
        address = "255.100.50.0";
        result = defangIPaddr(address);
        logger.accept(address, result);
    }

}
