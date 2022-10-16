package ru.ifellow.utils;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

public class ReflectionUtil {

    public static Object extractValue(Field field, Object context){
        field.setAccessible(true);
        try{
            return field.get(context);
        }catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }finally {
            field.setAccessible(false);
        }
    }

    public static Set<Class<?>> getPagesAnnotatedWith(String packageName, Class<? extends Annotation> annotation) {
        return new Reflections(packageName).getTypesAnnotatedWith(annotation);
    }
}
