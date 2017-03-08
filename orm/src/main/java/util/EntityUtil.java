package util;

/**
 * Created by IO on 24.12.2016.
 */

import annotation.Column;
import annotation.Entity;
import annotation.Id;

import org.springframework.beans.PropertyAccessor;
import org.springframework.util.ReflectionUtils;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EntityUtil {

    public static <T> int extractId(T instance) {
        Class<?> instanceClass = instance.getClass();
        Field field = Arrays.stream(instanceClass.getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotationsByType(Id.class) != null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid @Id annotations number"));
        ReflectionUtils.makeAccessible(field);
        return (int) ReflectionUtils.getField(field, instance);
    }

    public static <T> String extractIdColumn(T instance) {
        Class<?> clazz = instance.getClass();
        Class<?> instanceClass = instance.getClass();
        Field field = Arrays.stream(instanceClass.getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotationsByType(Id.class) != null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid @Id annotations number"));
        ReflectionUtils.makeAccessible(field);
        return field.getName();

    }

    public static <T> String extractIdColumn(Class<T> tClass) {
        Field field = Arrays.stream(tClass.getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotationsByType(Id.class) != null)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid @Id annotations number"));
        ReflectionUtils.makeAccessible(field);
        return field.getName();
    }


    public static <T> Map<String, String> getMapping(T instance) {
        Class<?> instanceClass = instance.getClass();
        List<Field> fields = Arrays.stream(instanceClass.getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotationsByType(Column.class) != null & f.getDeclaredAnnotationsByType(Id.class).length == 0)
                .collect(Collectors.toList());
        Map<String, String> mapping = new HashMap<>();
        fields.forEach(field -> {
            ReflectionUtils.makeAccessible(field);
            try {
                mapping.put(field.getName(), field.get(instance).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return mapping;
    }

    public static <T> String getTableName(T instance) {
        Class<?> instanceClass = instance.getClass();
        Entity annotation = instanceClass.getDeclaredAnnotation(Entity.class);
        return annotation.table();
    }

    public static String getTableName(Class<?> tClass) {
        Entity annotation = tClass.getDeclaredAnnotation(Entity.class);
        return annotation.table();
    }

    public static <T> T extractObject(ResultSet resultSet, Class<T> tClass) throws IllegalAccessException, InstantiationException {
        List<Field> fields = Arrays.stream(tClass.getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) != null)
                .collect(Collectors.toList());

        T instance = null;
        try {
            Constructor<T> constructor = tClass.getConstructor();
            instance = constructor.newInstance();
            PropertyAccessor finalPropertyAccessor = PropertyAccessorFactory.forBeanPropertyAccess(instance);
            resultSet.next();
            fields.forEach(field -> {
                try {
                    finalPropertyAccessor.setPropertyValue(field.getName(), resultSet.getObject(field.getName()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (NoSuchMethodException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

}
