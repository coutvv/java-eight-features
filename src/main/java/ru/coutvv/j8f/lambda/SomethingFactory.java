package ru.coutvv.j8f.lambda;

/**
 * Created by coutvv on 01.02.2017.
 */
public interface SomethingFactory<S extends Something> {
	S create(String name, String lastName);
}
