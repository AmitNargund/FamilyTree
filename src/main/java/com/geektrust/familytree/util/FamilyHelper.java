package com.geektrust.familytree.util;

import com.geektrust.familytree.model.Family;
import com.geektrust.familytree.model.Member;
import com.geektrust.familytree.model.Member.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FamilyHelper {

    public static final String DELIMITER_JOINER = " ";
    private static final String BLANK_STRING = "";

    private Family family;

    public FamilyHelper(Family family) {
        this.family = family;
    }

    /**
     * Add a child to given motherName
     *
     * @param motherName Name of the child's mother
     * @param childName  Name of the child
     * @param gender     Child's gender
     * @return Message
     */
    public String addChild(String motherName, String childName, String gender) {

        Member mother = searchMember(motherName);
        if (mother == null) {
            return Constants.PERSON_NOT_FOUND;
        } else if (mother.getGender() != Gender.FEMALE) {
            return Constants.CHILD_ADDITION_FAILED;
        }

        Member child = Member.Builder.getInstance(childName, gender).setMother(mother).setFather(mother.getSpouse()).build();
        if (null == mother.getChildren()) {
            List<Member> children = new ArrayList<>();
            children.add(child);
            mother.setChildren(children);
        } else {
            mother.getChildren().add(child);
        }
        return Constants.CHILD_ADDITION_SUCCEEDED;
    }

    /**
     * Search a member
     *
     * @param memberName Name of the member to be searched
     * @return {@link Member}
     */
    public Member searchMember(String memberName) {
        if (Assert.notNullNotBlank(memberName))
            return searchMember(memberName, family.getKing());

        return null;
    }

    /**
     * Search a member
     *
     * @param memberName Name of the member to be searched
     * @param rootNode   root node
     * @return {@link Member}
     */
    private Member searchMember(String memberName, Member rootNode) {

        if (rootNode.getName().equals(memberName))
            return rootNode;
        else if (rootNode.getSpouse() != null && rootNode.getSpouse().getName().equals(memberName))
            return rootNode.getSpouse();
        else if (rootNode.getChildren() != null) {
            Member person = null;
            for (Member child : rootNode.getChildren()) {
                person = searchMember(memberName, child);
                if (person != null)
                    break;
            }
            return person;
        } else
            return null;
    }

    /**
     * Add a spouse
     *
     * @param memberName Name of the member to whom spouse should be added
     * @param spouseName name of the spouse to be added
     * @param gender     gender of the spouse
     */
    public void addSpouse(String memberName, String spouseName, String gender) {
        Member member = searchMember(memberName);
        if (null != member) {
            Member spouse = Member.Builder.getInstance(spouseName, gender).build();
            member.setSpouse(spouse);
            spouse.setSpouse(member);
        }
    }

    /**
     * Find paternal uncles of a member
     *
     * @param member Name of member whose paternal uncles needs to be searched
     * @return Name of Paternal uncles
     */
    public String getPaternalUncle(Member member) {
        if (member != null && null != member.getFather() && null != member.getFather().getMother()) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member uncle : member.getFather().getMother().getChildren()) {
                if (uncle.getGender() == Gender.MALE && uncle != member.getFather())
                    joiner.add(uncle.getName());
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

    /**
     * Find maternal uncles of a member
     *
     * @param member Name of the member whose maternal uncles need to be searched
     * @return Name of Maternal uncles
     */
    public String getMaternalUncles(Member member) {
        if (member != null && null != member.getMother() && null != member.getMother().getMother()) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member uncle : member.getMother().getMother().getChildren()) {
                if (uncle.getGender() == Gender.MALE)
                    joiner.add(uncle.getName());
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

    /**
     * Find the Paternal aunts of a member
     *
     * @param member Name of the member whose paternal aunts need to be searched
     * @return Name of Paternal aunts
     */
    public String getPaternalAunts(Member member) {
        if (member != null && null != member.getFather() && null != member.getFather().getMother()) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member aunt : member.getFather().getMother().getChildren()) {
                if (aunt.getGender() == Gender.FEMALE)
                    joiner.add(aunt.getName());
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

    /**
     * Find the Maternal aunts of a member
     *
     * @param member Name of the member whose maternal aunts need to be searched
     * @return Name of Maternal aunts
     */
    public String getMaternalAunts(Member member) {
        if (member != null && null != member.getMother() && null != member.getMother().getMother()) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member aunt : member.getMother().getMother().getChildren()) {
                if (aunt.getGender() == Gender.FEMALE && aunt != member.getMother())
                    joiner.add(aunt.getName());
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

    /**
     * Find the sisters in law of a member
     *
     * @param member Name of the member whose sisters in law need to be searched
     * @return Name of sisters in law
     */
    public String getSistersInLaw(Member member) {

        StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);

        //spouse sisters
        if (null != member && null != member.getSpouse() && null != member.getSpouse().getMother()) {

            for (Member spouseSibling : member.getSpouse().getMother().getChildren()) {
                if (spouseSibling != member.getSpouse() && spouseSibling.getGender() == Gender.FEMALE)
                    joiner.add(spouseSibling.getName());
            }
        }
        //wives of siblings
        if (null != member && null != member.getMother()) {
            for (Member sibling : member.getMother().getChildren()) {
                if (sibling != member && null != sibling.getSpouse() && sibling.getSpouse().getGender() == Gender.FEMALE) {
                    if (sibling.getSpouse() != null) {
                        joiner.add(sibling.getSpouse().getName());
                    }
                }

            }
        }

        return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
    }

    /**
     * Find the brothers in law of a member
     *
     * @param member Name of the member whose brothers in law need to be searched
     * @return Name of brothers in law
     */
    public String getBrothersInLaw(Member member) {

        StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);

        // Spouse’s brothers
        if (null != member && null != member.getSpouse() && null != member.getSpouse().getMother()) {
            for (Member spouseSibling : member.getSpouse().getMother().getChildren()) {
                if (spouseSibling != member.getSpouse() && spouseSibling.getGender() == Gender.MALE)
                    joiner.add(spouseSibling.getName());
            }
        }

        // Husbands of siblings
        if (null != member && null != member.getMother()) {
            for (Member sibling : member.getMother().getChildren()) {
                if (sibling != member && null != sibling.getSpouse() && sibling.getSpouse().getGender() == Gender.MALE) {
                    if (sibling.getSpouse() != null) {
                        joiner.add(sibling.getSpouse().getName());
                    }
                }
            }
        }

        return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
    }

    /**
     * Find the sons of a member
     *
     * @param member Name of the member whose sons need to be searched
     * @return Name of sons
     */
    public String getSons(Member member) {
        if (null != member && member.getChildren() != null) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member child : member.getChildren()) {
                if (child.getGender() == Gender.MALE) {
                    joiner.add(child.getName());
                }
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

    /**
     * Find the daughters of a member
     *
     * @param member Name of the member whose daughters need to be searched
     * @return Name of daughters
     */
    public String getDaughters(Member member) {
        if (null != member && member.getChildren() != null) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member child : member.getChildren()) {
                if (child.getGender() == Gender.FEMALE) {
                    joiner.add(child.getName());
                }
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

    /**
     * Find the siblings of a member
     *
     * @param member Name of the member whose siblings need to be searched
     * @return Name of siblings
     */
    public String getSiblings(Member member) {
        if (null != member && null != member.getMother()) {
            StringJoiner joiner = new StringJoiner(DELIMITER_JOINER);
            for (Member sibling : member.getMother().getChildren()) {
                if (member != sibling) {
                    joiner.add(sibling.getName());
                }
            }
            return joiner.toString().equals(BLANK_STRING) ? Constants.NONE : joiner.toString();
        }

        return Constants.NONE;
    }

}
