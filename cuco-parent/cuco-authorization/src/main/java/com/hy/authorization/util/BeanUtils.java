package com.hy.authorization.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class BeanUtils extends org.springframework.beans.BeanUtils {
      
      public static void copyProperties(Object source, Object target) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");  
        Class<?> actualEditable = target.getClass();  
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {  
          if (targetPd.getWriteMethod() != null) {  
            PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());  
            if (sourcePd != null && sourcePd.getReadMethod() != null) {
              String filed = "";
              try {
                Method readMethod = sourcePd.getReadMethod();
                filed = readMethod.getName();
                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                  readMethod.setAccessible(true);  
                }  
                Object value = readMethod.invoke(source);
                if (value != null) {
                  Method writeMethod = targetPd.getWriteMethod();
                  if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {  
                    writeMethod.setAccessible(true);  
                  }  
                  writeMethod.invoke(target, value);  
                }  
              } catch (Throwable ex) {
                throw new FatalBeanException("Could not copy properties from source to target!->"+StringUtil.filterNull(filed), ex);
              }  
            }  
          }  
        }  
      }  
    }  