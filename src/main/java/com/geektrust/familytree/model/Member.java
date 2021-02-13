package com.geektrust.familytree.model;

import com.geektrust.familytree.util.Assert;

import java.util.List;

public class Member {

    private String name;

    private Gender gender;

    private Member spouse;

    private Member mother;

    private Member father;

    private List<Member> children;

    private Member() {
    }

    public enum Gender {
        MALE,
        FEMALE
    }

    public static class Builder {
        private String name;
        private Gender gender;
        private Member spouse;
        private Member mother;
        private Member father;

        private Builder() {
        }

        public static Builder getInstance(String name, String gender) {
            Builder builder = new Builder();
            builder.setName(name);
            builder.setGender(gender);

            return builder;
        }

        public Builder setName(String name) {
            if (Assert.notNullNotBlank(name))
                this.name = name;
            else
                throw new IllegalArgumentException("Member should have a valid name");

            return this;
        }

        public Builder setGender(String gender) {
            if (gender.equalsIgnoreCase("MALE"))
                this.gender = Gender.MALE;
            else if (gender.equalsIgnoreCase("FEMALE"))
                this.gender = Gender.FEMALE;
            else
                throw new IllegalArgumentException("Provide a valid gender");

            return this;
        }

        public Builder setMother(Member mother) {
            this.mother = mother;
            return this;
        }

        public Builder setFather(Member father) {
            this.father = father;
            return this;
        }

        public Member build() {
            Member member = new Member();
            member.name = this.name;
            member.gender = this.gender;
            member.mother = this.mother;
            member.father = this.father;

            return member;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Member getSpouse() {
        return spouse;
    }

    public void setSpouse(Member spouse) {
        this.spouse = spouse;
    }

    public Member getMother() {
        return mother;
    }

    public void setMother(Member mother) {
        this.mother = mother;
    }

    public Member getFather() {
        return father;
    }

    public void setFather(Member father) {
        this.father = father;
    }

    public List<Member> getChildren() {
        if (this.gender == Gender.FEMALE)
            return children;
        else if (this.spouse != null)
            return this.spouse.getChildren();
        else
            return null;
    }

    public void setChildren(List<Member> children) {
        if (this.gender == Gender.FEMALE)
            this.children = children;
        else if (this.spouse != null)
            this.spouse.setChildren(children);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}