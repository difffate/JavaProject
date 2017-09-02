package org.zheng.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.matchers.Equals;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * MockitoTester.
 *
 * @author zxb
 * @version 1.0
 * @since <pre>08/29/2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest3 {

    @Mock
    private LinkedList linkedList;

    @Spy
    private ArrayList arrayList;

    final class DontYouDareToMockMe extends ArrayList {

    }

    @Test
    public void test() throws InterruptedException {
        //区别一个对象是模拟对象还是侦查对象
        Assert.assertTrue(Mockito.mockingDetails(linkedList).isMock());
        Assert.assertTrue(Mockito.mockingDetails(arrayList).isSpy());
        //模拟对象不一定是侦查对象
        Assert.assertFalse(Mockito.mockingDetails(linkedList).isSpy());
        //侦查对象是模拟对象的一种类型
        Assert.assertTrue(Mockito.mockingDetails(arrayList).isMock());

        //委托调用真实实例（doCallRealMethod），用在以下几种场景：
        //Final classes but with an interface，有接口（有继承应该也行）的final类
        //Already custom proxied object ，已经自定义代理的对象
        //Special objects with a finalize method, i.e. to avoid executing it 2 times，final方法
        DontYouDareToMockMe dontYouDareToMockMe = new DontYouDareToMockMe();
        dontYouDareToMockMe.add(new HashSet());

        ArrayList listWithDelegate = mock(ArrayList.class, delegatesTo(dontYouDareToMockMe));
        Assert.assertEquals("mock对象的size不为1", 1, listWithDelegate.size());
        listWithDelegate.clear();
        //委托调用了真实方法，所以真实对象的size也为空了。
        Assert.assertEquals("dontYouDareToMockMe的size不为0", 0, dontYouDareToMockMe.size());
        //因为是调用真实对象的方法，所以当索引不存在时，不能直接用when..thenReturn方法
        //when(listWithDelegate.get(0)).thenReturn("foo");
        doReturn("foo").when(listWithDelegate).get(0);
        Assert.assertEquals("foo", listWithDelegate.get(0));
        //比如对String类mock
        String str = "vvv";
        CharSequence mock = mock(CharSequence.class, delegatesTo(str));
        System.out.println(mock);
        Assert.assertThat(mock.toString(), new Equals("vvv"));
    }
}
