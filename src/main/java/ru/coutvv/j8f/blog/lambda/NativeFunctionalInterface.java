package ru.coutvv.j8f.blog.lambda;

import java.util.Arrays;
import java.util.function.*;

/**
 * Тестировка встроенных интерфейсов
 *
 * Created by lomovtsevrs on 14.02.2017.
 */
public class NativeFunctionalInterface {

    Supplier<Man> birth = () -> new Man();

    Function<Man, Woman> sexOperationToWomen = (m) -> new Woman(m.getName() + "'a", m.getSexOrientation());

    Consumer<Man> intro = (m) -> System.out.println("the man calls as " + m.getName());

    Predicate<Man> isGay = m -> m.getSexOrientation().equals("homo");
    Predicate<Man> haveStupidName = m -> Arrays.asList("Bodis", "Adonis", "Grofa", "John Locke").contains(m.getName());

    UnaryOperator<Man> mutation = m -> {
        m.setSexOrientation("alien"); return m;
    };

    BinaryOperator<Man> merge = (m1, m2) -> new Man(m1.getName() + " Monster", m2.getSexOrientation());

    public void test() {

        Man drizer = new Man("Drizer", "homo");//создаём обычным способом
        //Supplier
        Man johnLocke = birth.get(); // поставляем джонлоков
        //Function
        Woman joahnaLocke = sexOperationToWomen.apply(johnLocke);//из джона создаём жоанну
        //Consumer
        intro.accept(johnLocke);//представляем джона
        //Predicate
        if(isGay.test(drizer)) {
            System.out.println(drizer.getName() + " is faggot");
        }
        if(isGay.negate().and(haveStupidName).test(johnLocke)) { // negate(), and и другие это дефолт-функции(а не абстракт)
            System.out.println(johnLocke.getName() + " is not gay, but he has stupid name");
        }
        //Unary
        johnLocke = mutation.apply(johnLocke);
        intro.accept(johnLocke);
        //Binary
        Man mergeJohnDrizer = merge.apply(johnLocke, drizer);
        intro.accept(mergeJohnDrizer);
    }

    public static void main(String[] args) {
        new NativeFunctionalInterface().test();
    }

}
