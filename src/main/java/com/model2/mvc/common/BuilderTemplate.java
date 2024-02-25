package com.model2.mvc.common;

import java.util.function.Consumer;

public abstract class BuilderTemplate<T extends Buildable> implements Cloneable {

    public final <U extends BuilderTemplate<T>> U setField(Consumer<U> fieldSetter) {
        U instance;
        try {
            instance = (U)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            instance = (U)this;
        }
        fieldSetter.accept(instance);
        return instance;
    }

    public abstract T build();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.build().builder();
    }
}
