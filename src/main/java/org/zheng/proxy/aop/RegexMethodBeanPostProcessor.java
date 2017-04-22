package org.zheng.proxy.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Create by zxb on 2017/4/22
 */
public class RegexMethodBeanPostProcessor implements BeanPostProcessor {

    private final Pattern pattern = Pattern.compile("Test");

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        boolean useSelfClass = false;
        Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        for (Method method : allDeclaredMethods) {
            if (pattern.matcher(method.getName()).find()) {
                useSelfClass = true;
                break;
            }
        }
        if (useSelfClass) {
            return createBean(bean);
        }
        return bean;
    }

    private Object createBean(Object bean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(new EnhancerCallBack());
        return enhancer.create();
    }

    class EnhancerCallBack implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            boolean flag = pattern.matcher(method.getName()).find();
            if (flag) {
                System.out.println("before match method invoke..");
            }
            Object result = methodProxy.invokeSuper(o, objects);
            if (flag) {
                System.out.println("after match method invoke..");
            }
            return result;
        }
    }
}
