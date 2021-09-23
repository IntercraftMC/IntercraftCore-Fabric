package net.intercraft.intercraftcore.common;

import net.intercraft.intercraftcore.common.element.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class ObjectElementGroup<T>
{
    protected final Element element;

    public ObjectElementGroup(Element element)
    {
        this.element = element;
    }

    /**
     * Create new instance from constructor.
     * @param constructor Object constructor.
     * @param form Check if the object is enabled.
     * @param suffix The registry name suffix.
     */
    protected T createObject(Constructor<?> constructor, int form, String suffix) {
        if ((element.forms & form) == form) {
            try {
                return (T) constructor.newInstance(new Object[]{element, suffix});
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                printError(e);
            }
        }
        return null;
    }

    protected abstract Constructor<?> createConstructor(Class<?> clazz);

    public Element getElement()
    {
        return element;
    }

    protected void printError(Exception e)
    {
        //System.out.println(String.format("[%s]: {%s}", e.getClass().getSimpleName(), e));
        System.out.printf("[%s]: {%s}%n", e.getClass().getSimpleName(), e);
    }
}