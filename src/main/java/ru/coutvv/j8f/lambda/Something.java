package ru.coutvv.j8f.lambda;

/**
 * Created by coutvv on 01.02.2017.
 */

class Something {
    public Something() {}

    String firstName, lastName;

    public Something(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

