package com.geektrust.familytree.util;

public final class Constants {

    private Constants() {
    }

    public static final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";
    public static final String CHILD_ADDITION_FAILED = "CHILD_ADDITION_FAILED";
    public static final String CHILD_ADDITION_SUCCEEDED = "CHILD_ADDITION_SUCCEEDED";
    public static final String NONE = "NONE";

    //Relationships
    public static final String PATERNAL_UNCLE = "Paternal-Uncle";
    public static final String MATERNAL_UNCLE = "Maternal-Uncle";
    public static final String PATERNAL_AUNT = "Paternal-Aunt";
    public static final String MATERNAL_AUNT = "Maternal-Aunt";
    public static final String SISTER_IN_LAW = "Sister-In-Law";
    public static final String BROTHER_IN_LAW = "Brother-In-Law";
    public static final String SON = "Son";
    public static final String DAUGHTER = "Daughter";
    public static final String SIBLINGS = "Siblings";

    //Commands
    public static final String ADD_CHILD = "ADD_CHILD";
    public static final String GET_RELATIONSHIP = "GET_RELATIONSHIP";
    public static final String INVALID_COMMAND = "INVALID_COMMAND";
}
