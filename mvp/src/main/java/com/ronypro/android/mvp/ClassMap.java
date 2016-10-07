package com.ronypro.android.mvp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rahony on 23/05/16.
 */
public class ClassMap<T> {

    private final Map<Class<? extends T>, Class<? extends T>> map;

    public ClassMap() {
        map = new HashMap<>();
    }

    public <A extends T, C extends A> Class<C> getConcrectClass(Class<A> abstractClass) throws IllegalStateException {
        @SuppressWarnings("unchecked") Class<C> concretClass = (Class<C>) map.get(abstractClass);
        if (concretClass == null) throw new IllegalStateException("No class configured to " + abstractClass.getName());
        return concretClass;
    }

    public <A extends T, C extends A> void put(Class<A> abstractClass, Class<C> concretClass) {
        checkClass(abstractClass, concretClass);
        map.put(abstractClass, concretClass);
    }

    protected <A extends T, C extends A> void checkClass(Class<A> abstractClass, Class<C> concretClass) {
        if (abstractClass == concretClass)
            throw new MvpWrongConfigException("Can't put in map a key and value with same class " + abstractClass.getName());
    }

}

