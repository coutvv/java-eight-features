package ru.coutvv.j8f.annotation;

/**
 * Created by lomovtsevrs on 10.02.2017.
 */
//@Hints({@Hint("hint1"), @Hint("hint2")}) //старый способ аннотациии конвейра
@Hint("hint1") //использование повторяемой аннотации(новый способ)
@Hint("hint2") //при использовании нового сособа компилятор автоматически подставляет @Hints
public class Person {

}
