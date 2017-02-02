package ru.coutvv.j8f.lambda;

/**
 * Created by coutvv on 01.02.2017.
 */

public class Something {

    String firstName, lastName;
    
    public Something() {}

    public Something(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
    
    @Override
    public String toString() {
    	return firstName + " " + lastName;
    }

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}

