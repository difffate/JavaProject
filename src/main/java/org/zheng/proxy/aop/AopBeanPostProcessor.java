package org.zheng.proxy.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Create by zxb on 2017/4/22
 */
public class AopBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("profileTest")) {
            //重新设置变量的值
            Method method = ReflectionUtils.findMethod(bean.getClass(), "setEnvironment", String.class);
            //执行下面这句，会将environment的值改变
//            ReflectionUtils.invokeMethod(method, bean, "hellozheng");
        }
        return bean;
    }
}
