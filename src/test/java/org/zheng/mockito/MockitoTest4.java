package org.zheng.mockito;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

/**
 * MockitoTester.
 *
 * @author zxb
 * @version 1.0
 * @since <pre>09/02/2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest4 {

    @Data
    class SandBox {

        private Sand sand;
    }

    interface Sand {

        Integer doA();
    }

    @Test
    public void test() throws InterruptedException {
        //mock对象时，修改默认返回值策略
        LinkedList mockList = mock(LinkedList.class
                , withSettings().defaultAnswer(RETURNS_SMART_NULLS));
        Assert.assertNotNull(mockList.get(0));

        //mock对象时，使用默认调用真实方法
        LinkedList mockList2 = mock(LinkedList.class
                , withSettings().defaultAnswer(CALLS_REAL_METHODS));
        mockList2.add(1);
        Assert.assertTrue(1 == mockList2.size());

        //mock对象时，使用默认构造函数（或者自己实现的）
        ConstructorTest constructorTest = mock(ConstructorTest.class
                , withSettings().useConstructor().defaultAnswer(RETURNS_SMART_NULLS));  //打印：Here's in constructor
        ConstructorTest constructorTest2 = mock(ConstructorTest.class);  // 不会打印
        Assert.assertNotNull(constructorTest);

        //Deep Stubs 可进行调用链上的mock，mock理解为树模型
        SandBox sandBox = mock(SandBox.class, RETURNS_DEEP_STUBS);
        SandBox sandBox2 = mock(SandBox.class);
        System.out.println("doA()=" + sandBox.getSand().doA());  //打印：doA()=0
        try {
            sandBox2.getSand().doA();
        } catch (Exception ex) {
            System.out.println("Exception！因为调用链上getSand已为null");
        }
    }
}
