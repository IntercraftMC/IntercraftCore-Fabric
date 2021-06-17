package net.intercraft.intercraftcore.common;

import java.lang.reflect.Constructor;

public abstract class ObjectGroup<T>
{
    protected abstract T createObject(Constructor<?> constructor, int form, String suffix);

    protected abstract Constructor<?> createConstructor(Class<?> clazz);

    protected void printError(Exception e)
    {
        //System.out.println(String.format("[%s]: {%s}", e.getClass().getSimpleName(), e));
        System.out.printf("[%s]: {%s}%n", e.getClass().getSimpleName(), e);
    }
}