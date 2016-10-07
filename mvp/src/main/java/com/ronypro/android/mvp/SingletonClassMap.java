package com.ronypro.android.mvp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rahony on 23/05/16.
 */
public class SingletonClassMap<T> {

    private final Map<Class<? extends T>, SingletonClass<? extends T>> map;

    public SingletonClassMap() {
        map = new HashMap<>();
    }

    public <A extends T> A get(Class<A> abstractClass) throws MvpInstantiationException, IllegalStateException {
        @SuppressWarnings("unchecked") SingletonClass<A> modelConfig = (SingletonClass<A>) map.get(abstractClass);
        if (modelConfig == null) throw new IllegalStateException("No class configured to " + abstractClass.getName());
        return getOrCreateInstance(modelConfig);
    }

    private <A extends T> A getOrCreateInstance(SingletonClass<A> singletonClass) throws MvpInstantiationException {
        if (singletonClass.instance == null) {
            singletonClass.instance = tryCreateInstance(singletonClass);
        }
        return singletonClass.instance;
    }

    private <A extends T> A tryCreateInstance(SingletonClass<A> singletonClass) throws MvpInstantiationException {
        try {
            return createInstance(singletonClass);
        } catch (InstantiationException e) {
            throw new MvpInstantiationException(singletonClass.concretClass.getName() + " need an empty constuctor", e);
        } catch (IllegalAccessException e) {
            throw new MvpInstantiationException(singletonClass.concretClass.getName() + " need a public empty constuctor", e);
        }
    }

    private <A extends T> A createInstance(SingletonClass<A> singletonClass) throws IllegalAccessException, InstantiationException {
        A instance = singletonClass.concretClass.newInstance();
        onInstanceCreated(instance);
        return instance;
    }

    protected void onInstanceCreated(T instance) {
    }

    public <A extends T, C extends A> void put(Class<A> abstractClass, Class<C> concretClass) {
        checkClass(abstractClass, concretClass);
        SingletonClass<A> singletonClass = new SingletonClass<>();
        singletonClass.concretClass = concretClass;
        map.put(abstractClass, singletonClass);
    }

    protected <A extends T, C extends A> void checkClass(Class<A> abstractClass, Class<C> concretClass) {
        if (abstractClass == concretClass)
            throw new MvpWrongConfigException("Can't put in map a key and value with same class " + abstractClass.getName());
    }

    private static class SingletonClass<A> {

        Class<? extends A> concretClass;
        A instance;

    }

}

