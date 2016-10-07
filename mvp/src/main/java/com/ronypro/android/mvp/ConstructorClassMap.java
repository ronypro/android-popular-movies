package com.ronypro.android.mvp;

/**
 * Created by rahony on 23/05/16.
 */
public class ConstructorClassMap<T> extends ClassMap<T> {

    public <A extends T> A get(Class<A> abstractClass) throws MvpInstantiationException, IllegalStateException {
        Class<A> concretClass = getConcrectClass(abstractClass);
        if (concretClass == null) throw new IllegalStateException("No class configured to " + abstractClass.getName());
        return tryCreateInstance(concretClass);
    }

    private <A extends T> A tryCreateInstance(Class<A> concretClass) throws MvpInstantiationException {
        try {
            return createInstance(concretClass);
        } catch (InstantiationException e) {
            throw new MvpInstantiationException(concretClass.getName() + " need an empty constuctor", e);
        } catch (IllegalAccessException e) {
            throw new MvpInstantiationException(concretClass.getName() + " need a public empty constuctor", e);
        }
    }

    private <A extends T> A createInstance(Class<A> concretClass) throws IllegalAccessException, InstantiationException {
        A instance = concretClass.newInstance();
        onInstanceCreated(instance);
        return instance;
    }

    protected void onInstanceCreated(T instance) {
    }

}

