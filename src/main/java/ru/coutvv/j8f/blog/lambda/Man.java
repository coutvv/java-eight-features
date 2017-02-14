package ru.coutvv.j8f.blog.lambda;

/**
 * Created by lomovtsevrs on 14.02.2017.
 */

public class Man {
    private String sexOrientation;
    private String name;


    public Man(String name, String sexOrientation) {
        setName(name);
        setSexOrientation(sexOrientation);
    }

    public Man(String name) {
        this(name, "getero");//Побудем сексистами
    }

    public Man() {
        this("John Locke");
    }

    public String getSexOrientation() {
        return sexOrientation;
    }

    public void setSexOrientation(String sexOrientation) {
        this.sexOrientation = sexOrientation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}