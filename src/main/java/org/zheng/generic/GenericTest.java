package org.zheng.generic;

import org.springframework.core.GenericTypeResolver;
import org.zheng.accesscontrol.a.A;
import org.zheng.accesscontrol.a.B;

/**
 * 获取泛型包含的具体的类信息，可以是自己定义的，也可以是继承的
 * Create by zxb on 2017/5/10
 */
public class GenericTest<T extends A> {

    public static void main(String[] args) {
        GenericTest<B> instance = new GenericTest<B>();
        Class<?> annoType = GenericTypeResolver.resolveTypeArgument(instance.getClass(), GenericTest.class);
        System.out.println(annoType);
    }
}

//abstract class Parent <T extends A> {
//
//}
//public class GenericTest extends Parent<B>{
//    public static void main(String[] args){
//        GenericTest instance = new GenericTest();
//        Class<?> annoType = GenericTypeResolver.resolveTypeArgument(instance.getClass(), Parent.class);
//        System.out.println(annoType);
//    }
//}