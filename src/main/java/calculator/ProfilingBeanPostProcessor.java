package calculator;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;


public class ProfilingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = bean.getClass();
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
//                String name = method.getName();
//                Class<?>[] parameterTypes = method.getParameterTypes();
//                Method classMethod = beanClass.getMethod(name, parameterTypes);
                List<Method> list = Arrays.asList(beanClass.getMethods());
                Function<Optional<Method>, Object> function = (s)->{
                    Object value = null;
                    Method annotatedMethod;
                    try {
                        annotatedMethod = s.get();
                    } catch (NoSuchElementException  e) {
                        try {
                            value = method.invoke(bean,args);
                        } catch (IllegalAccessException | InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                        return value;
                    }
                    long before = System.nanoTime();
                    try {
                        value = annotatedMethod.invoke(bean,args);
                    } catch (IllegalAccessException | InvocationTargetException exception) {
                    exception.printStackTrace();
                    }
                    long after = System.nanoTime();
                    System.out.println("Profiling ");
                    System.out.println(after - before);
                    System.out.println("End ");
                    return value;
                };

                Object value = function.apply(
                        list.stream().filter(s -> s.getName().equals(method.getName())
                        && Arrays.equals(s.getParameterTypes(), method.getParameterTypes())
                        && s.isAnnotationPresent(Profiling.class)).findFirst());
//                if(classMethod.isAnnotationPresent(Profiling.class)) {
//                    System.out.println("Profiling ");
//                    long before = System.nanoTime();
//                    value = method.invoke(bean, args);
//                    long after = System.nanoTime();
//                    System.out.println(after - before);
//                    System.out.println("End ");
//                }
//                else{
//                    value=method.invoke(bean,args);
//                }

                return value;

            });

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        return bean;
    }
}
