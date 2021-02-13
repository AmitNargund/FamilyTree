package com.geektrust.familytree.util;

public class Assert {

    /**
     * Check if a String is blank or null
     *
     * @param input String which needs to be checked
     * @return boolean
     */
    public static boolean notNullNotBlank(String input) {
        if (null != input && !input.trim().equals(""))
            return true;

        return false;
    }

}
