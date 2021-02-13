package com.geektrust.familytree.util;

import com.geektrust.familytree.model.Family;

public class FamilyInitializer {

    /**
     * Initialize the family
     *
     * @return {@link Family}
     */
    public static Family initializeFamily() {
        Family family = new Family("King Shan", "Queen Anga");

        family.addChild("Queen Anga", "Chit", "Male");
        family.addChild("Queen Anga", "Ish", "Male");
        family.addChild("Queen Anga", "Vich", "Male");
        family.addChild("Queen Anga", "Aras", "Male");
        family.addChild("Queen Anga", "Satya", "Female");

        family.addSpouse("Chit", "Amba", "Female");
        family.addSpouse("Vich", "Lika", "Female");
        family.addSpouse("Aras", "Chitra", "Female");
        family.addSpouse("Satya", "Vyan", "Male");

        family.addChild("Amba", "Dritha", "Female");
        family.addChild("Amba", "Tritha", "Female");
        family.addChild("Amba", "Vritha", "Male");


        family.addSpouse("Dritha", "Jaya", "Male");
        family.addChild("Dritha", "Yodhan", "Male");

        family.addChild("Lika", "Vila", "Female");
        family.addChild("Lika", "Chika", "Female");

        family.addChild("Chitra", "Jnki", "Female");
        family.addChild("Chitra", "Ahit", "Male");

        family.addSpouse("Jnki", "Arit", "Male");
        family.addChild("Jnki", "Laki", "Male");
        family.addChild("Jnki", "Lavnya", "Female");

        family.addChild("Satya", "Asva", "Male");
        family.addChild("Satya", "Vyas", "Male");
        family.addChild("Satya", "Atya", "Female");

        family.addSpouse("Asva", "Satvy", "Female");
        family.addChild("Satvy", "Vasa", "Male");

        family.addSpouse("Vyas", "Krpi", "Female");
        family.addChild("Krpi", "Kriya", "Male");
        family.addChild("Krpi", "Krithi", "Female");

        return family;
    }
}
