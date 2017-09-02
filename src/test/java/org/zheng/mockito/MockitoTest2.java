package org.zheng.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * MockitoTester.
 *
 * @author zxb
 * @version 1.0
 * @since <pre>08/26/2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest2 {

    @Mock
    private LinkedList linkedList;

    //还有InjectMocks注解，注入其他mock对象

    @Test
    public void test() throws InterruptedException {

        //doThrow() 可对void方法处理
        doThrow(new RuntimeException()).when(linkedList).clear();
        try {
            linkedList.clear();
        } catch (Exception e) {
            System.out.println("为void方法抛出异常");//打印：为void方法抛出异常
        }

        //spy和mock的区别：spy是真实对象的拷贝（由动态代理生成）
        LinkedList instance = new LinkedList();
        instance.add(1);
        LinkedList spy = spy(instance);
        System.out.println(spy.size());  //打印：1
        try {
            when(spy.get(1)).thenReturn("foo");
            //when(spy.get(1)).thenAnswer(...)  也会异常，spy.get(1)会先调真实方法
        } catch (Exception ex) {
            System.out.println("spy会调用真实的方法，使用thenReturn会报错");
        }

        //doReturn
        doReturn("foo").when(spy).get(1);
        System.out.println(spy.get(1));  //打印：foo

        //doAnswer() 作用类似thenAnswer
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return "second";
            }
        }).when(spy).get(2);
        System.out.println(spy.get(2));//打印：second

        //doNothing() 啥事也不做  可对void方法处理
        doNothing().when(spy).clear();
        spy.clear();
        System.out.println(spy.size());//打印：1，并没有清空列表

        //doCallRealMethod() 调用真实对象的方法
        doCallRealMethod().when(spy).clear();
        spy.clear();
        System.out.println(spy.size());//打印：0，清空了列表
        System.out.println(instance.size());//打印：1，但是列表元素都null，所以spy调用时，也同时影响到了真实对象

        //修改没有测试桩的调用的默认返回值 ( 1.7版本之后 )
        List mockedList = mock(List.class, RETURNS_SMART_NULLS);
        Object o = mockedList.get(999);
        //不会抛出空指针的异常，因为null被处理了
        // ，会打印：SmartNull returned by this unstubbed method call on a mock: list.get(999);
        System.out.println(o.toString());

        //thenCallRealMethod 打印真实对象
        when(linkedList.add(1)).thenCallRealMethod();
        when(linkedList.size()).thenCallRealMethod();
        linkedList.add(1);
        System.out.println(linkedList.size());// 打印：1，因为值被真实加了，并且读取真实的size，否则mock对象这些未打桩的方法没作用只会返回默认值

        //参数捕获
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(linkedList).add(argument.capture());
        // 使用equal断言
        assertTrue(1 == argument.getValue());

//        重置mock对象
//        reset(linkedList);
//        System.out.println(linkedList.size());// 打印：0，所有的打桩将被废弃

        //行为驱动开发
        given(linkedList.size()).willReturn(111);
        assertEquals(linkedList.size(), 111);

        //验证序列化，暂时不考虑

        //验证超时，在指定时间间隔内，在本线程或其他线程是否被调用，多用在多线程场景，因为他没检测到会阻塞当前线程等待判断
        System.out.println(linkedList.getFirst());
        verify(linkedList, timeout(1000).atLeast(1)).getFirst();

        //忽略所设置的测试桩
        ignoreStubs(linkedList);
        verifyNoMoreInteractions(linkedList);   //执行完ignoreStubs后，这样不会报错，否则提示上文还有未校验的
    }
}
