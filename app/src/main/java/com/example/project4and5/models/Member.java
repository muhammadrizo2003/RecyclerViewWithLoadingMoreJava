package com.example.project4and5.models;

import androidx.annotation.NonNull;

public class Member {
    private String name;
    private String surname;
    private Boolean isAvailable = false;

    public Member(String name, String surname, Boolean isAvailable) {
        this.name = name;
        this.surname = surname;
        this.isAvailable = isAvailable;
    }

    public Member() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
}