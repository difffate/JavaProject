package org.zheng.proxy.objenesis;


import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

/**
 * Create by zxb on 2017/4/22
 */
public class ObjenesisTest {

    public static void main(String[] args) {
        //Objenesis的类，也和CGLib的类一样，被包含在spring中了，不过这里采用原始的jar包，效果同
        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator objectInstantiator = objenesis.getInstantiatorOf(ObjenesisModel.class);
        ObjenesisModel objenesisModel = (ObjenesisModel) objectInstantiator.newInstance();
        // or use this way
        // ObjenesisModel objenesisModel = objenesis.newInstance(ObjenesisModel.class);
        objenesisModel.setId("111");
        objenesisModel.setName("AAA");
        objenesisModel.print();
    }
}
