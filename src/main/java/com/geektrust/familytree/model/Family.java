package com.geektrust.familytree.model;

import com.geektrust.familytree.util.Constants;
import com.geektrust.familytree.util.FamilyHelper;

public class Family {

    private Member king;

    private Member queen;

    private FamilyHelper familyHelper;

    public Family(String kingName, String queenName) {

        this.king = Member.Builder.getInstance(kingName, "male").build();
        this.queen = Member.Builder.getInstance(queenName, "female").build();
        this.king.setSpouse(this.queen);
        this.queen.setSpouse(this.king);

        this.familyHelper = new FamilyHelper(this);
    }

    public Member getKing() {
        return king;
    }

    public void setKing(Member king) {
        this.king = king;
    }

    public Member getQueen() {
        return queen;
    }

    public void setQueen(Member queen) {
        this.queen = queen;
    }

    public String addChild(String motherName, String childName, String gender) {
        return familyHelper.addChild(motherName, childName, gender);
    }

    public Member searchMember(String memberName) {
        return familyHelper.searchMember(memberName);
    }

    public void addSpouse(String memberName, String spouseName, String gender) {
        familyHelper.addSpouse(memberName, spouseName, gender);
    }

    public String getRelationShip(String memberName, String relationShip) {
        Member member = searchMember(memberName);
        if (null == member) {
            return Constants.PERSON_NOT_FOUND;
        } else {
            switch (relationShip) {
                case Constants.PATERNAL_UNCLE:
                    return familyHelper.getPaternalUncle(member);
                case Constants.MATERNAL_UNCLE:
                    return familyHelper.getMaternalUncles(member);
                case Constants.PATERNAL_AUNT:
                    return familyHelper.getPaternalAunts(member);
                case Constants.MATERNAL_AUNT:
                    return familyHelper.getMaternalAunts(member);
                case Constants.SISTER_IN_LAW:
                    return familyHelper.getSistersInLaw(member);
                case Constants.BROTHER_IN_LAW:
                    return familyHelper.getBrothersInLaw(member);
                case Constants.SON:
                    return familyHelper.getSons(member);
                case Constants.DAUGHTER:
                    return familyHelper.getDaughters(member);
                case Constants.SIBLINGS:
                    return familyHelper.getSiblings(member);
                default:
                    return Constants.NONE;
            }

        }
    }
}
