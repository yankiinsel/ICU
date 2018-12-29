package com.icu.yankiinsel.icu.Model;

public enum Gender {
    MALE(0), FEMALE(1), ATTACK_HELICOPTER(2), OTHER(3);

    private final int value;
    private Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
