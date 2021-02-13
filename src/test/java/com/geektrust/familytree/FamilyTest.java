package com.geektrust.familytree;


import com.geektrust.familytree.model.Family;
import com.geektrust.familytree.model.Member;
import com.geektrust.familytree.util.Constants;
import com.geektrust.familytree.util.FamilyInitializer;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class FamilyTest {

    private static Family family;

    @BeforeClass
    public static void before() {
        family = FamilyInitializer.initializeFamily();
    }

    @Test
    public void checkKingAndQueen() {
        assertEquals("King Shan", family.getKing().getName());
        assertEquals("Queen Anga", family.getQueen().getName());
    }

    @Test
    public void checkKingsChildren() {
        assertEquals(5, family.getQueen().getChildren().size());

        List<Member> children = Arrays.asList( Member.Builder.getInstance("Chit", "male").build(),
                Member.Builder.getInstance("Ish", "male").build(),
                Member.Builder.getInstance("Vich", "male").build(),
                Member.Builder.getInstance("Aras", "male").build(),
                Member.Builder.getInstance("Satya", "female").build());

        assertThat(children, containsInAnyOrder(hasProperty("name", is("Chit")),
                hasProperty("name", is("Ish")),
                hasProperty("name", is("Vich")),
                hasProperty("name", is("Aras")),
                hasProperty("name", is("Satya"))));
    }

    @Test
    public void checkAddChildSuccess() {
        Assert.assertEquals(Constants.CHILD_ADDITION_SUCCEEDED, family.addChild("Chitra", "Aria", "female"));
    }

    @Test
    public void checkAddChildPersonNotFound() {
        Assert.assertEquals(Constants.PERSON_NOT_FOUND, family.addChild("Pjali", "Srutak",  "Male"));
    }

    @Test
    public void checkAddChildFailed() {
        Assert.assertEquals(Constants.CHILD_ADDITION_FAILED, family.addChild("Asva", "Ameet",  "Male"));
    }


    @Test
    @Parameters({
            "Queen Anga, Son, Chit Ish Vich Aras",
            "King Shan, Son, Chit Ish Vich Aras",
            "Queen Anga, Daughter, Satya",
            "Satya, WIFE, NONE",
            "Dritha, Paternal-Uncle, Ish Vich Aras",
            "Ish, Paternal-Uncle, NONE",
            "Chit, Siblings,  Ish Vich Aras Satya",
            "Yodhan, Siblings,  NONE",
            "Lavnya, Maternal-Uncle, Ahit",
            "Chika, Maternal-Uncle, NONE",
            "Kriya, Paternal-Aunt, Atya",
            "Yodhan, Maternal-Aunt, Tritha",
            "Laki, Maternal-Aunt, Aria",
            "Satvy, Sister-In-Law, Atya",
            "Arit, Brother-In-Law, Ahit",
            "Ameet, Siblings, PERSON_NOT_FOUND",
            "Arit, SON-In-Law, NONE",
    })
    public void getRelationShip(String memberName, String relationship, String expectedResult ) {
        assertEquals(expectedResult, family.getRelationShip(memberName, relationship));
    }
}
