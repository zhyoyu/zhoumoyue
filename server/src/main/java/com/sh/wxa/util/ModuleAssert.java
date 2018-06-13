package com.sh.wxa.util;

public class ModuleAssert {

    public static void assertTrue(boolean condition) {
        assertTrue(condition, null);
    }

    public static void assertTrue(boolean condition, String prompt) {
        if(!condition) {
            throwException(null, ErrorType.SERVER, prompt);
        }
    }

    public static void assertTrue(boolean condition, String openId, ErrorType type, String prompt, String... promptArgs) {
        if (!condition) {
            throwException(openId, type, prompt, promptArgs);
        }
    }

    public static void assertNotNull(Object obj, String prompt) {
        if(obj == null) {
            throwException(null, ErrorType.SERVER, prompt);
        }
    }



    private static void throwException(String openId, ErrorType type, String prompt, String... promptArgs) {
        throw new ModuleException(openId, type, prompt, promptArgs);
    }

}
