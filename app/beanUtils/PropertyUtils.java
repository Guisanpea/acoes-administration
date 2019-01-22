package beanUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class PropertyUtils {
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
              .map(FeatureDescriptor::getName)
              .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
              .toArray(String[]::new);
    }

    // then use Spring BeanUtils to copy and ignore null
    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
