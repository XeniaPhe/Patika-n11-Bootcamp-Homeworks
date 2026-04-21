package main.java.com.xenia.n11bootcamp.paymentprocessing.ioc;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

public abstract class TypeLiteral<T> {
    private final Type type;

    protected TypeLiteral() {
        Type superClass = getClass().getGenericSuperclass();

        if (!(superClass instanceof ParameterizedType pt)) {
            throw new RuntimeException("Must be anonymous subclass");
        }

        this.type = pt.getActualTypeArguments()[0];
    }

    Type getType() {
        return type;
    }
}