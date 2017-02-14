package ru.coutvv.j8f.blog.lambda;

/**
 * Created by lomovtsevrs on 14.02.2017.
 */
public class Woman {
    private String sexOrientation;
    private String name;


    public Woman(String name, String sexOrientation) {
        setName(name);
        setSexOrientation(sexOrientation);
    }

    public Woman(String name) {
        this(name, "getero");//Побудем сексистами
    }

    public Woman() {
        this("Jane Dow");
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
