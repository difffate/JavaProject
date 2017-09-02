package org.zheng.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * MockitoTester.
 *
 * @author zxb
 * @version 1.0
 * @since <pre>08/27/2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest1 {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Mock
    private List annotationMockList;

    @Test
    public void test() {
//        Integer i = mock(Integer.class); //编译正常，运行时异常，基本数据类型或其包装类
//        String ss = mock(String.class); //编译正常，运行时异常，,final类
//        Mockito cannot mock/spy following: final classes、anonymous classes、primitive types
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        //验证函数的确切、最少、从未调用次数
        verify(mockedList).add("one");  //至少一次，否则报错
        verify(mockedList).clear();
        verify(mockedList, times(1)).clear();
        verify(mockedList, atLeastOnce()).clear();
        verify(mockedList, atLeast(1)).clear();  //至少1次
        verify(mockedList, atMost(2)).clear(); //至多2次
        verify(mockedList, never()).remove(0);

        LinkedList linkedList = mock(LinkedList.class);
        //验证打桩
        when(linkedList.get(0)).thenReturn("first");
        System.out.println(linkedList.get(0));   //打印：first

        //验证异常
        when(linkedList.get(1)).thenThrow(new RuntimeException());  //抛出异常
        try {
            System.out.println(linkedList.get(1));
        } catch (Exception ex) {
            System.out.println("Exception ! "); //打印：Exception !
        }

        //未打桩默认值
        System.out.println(linkedList.get(999));  //打印：null 对象默认值
        System.out.println(linkedList.contains("1")); //打印：false  布尔默认值
        System.out.println(linkedList.subList(0, 5)); //打印：[]  集合默认值
        System.out.println(linkedList.size()); //打印：0

        //使用参数匹配器
        when(linkedList.get(anyInt())).thenReturn("element");
        System.out.println(linkedList.get(999));  //打印：element

        //使用自定义的参数匹配器
        when(linkedList.contains(argThat(new isStringMatcher()))).thenReturn(true);
        //等价：when(linkedList.contains(anyString())).thenReturn(true);
        System.out.println(linkedList.contains("hello"));  // 不是String类型就会报错，打印：true

        //参数匹配器来验证
        verify(linkedList).contains(eq("hello")); // 不是 hello 就会报错

        //如果你使用参数匹配器,所有参数都必须由匹配器提供。
        when(linkedList.subList(anyInt(), anyInt())).thenReturn(new ArrayList());
        try {
            when(linkedList.subList(0, anyInt())).thenReturn(new ArrayList());
        } catch (Exception ex) {
            System.out.println("要么都是参数匹配器，要么都不是 ! "); //打印：要么都是参数匹配器，要么都不是 !
        }
        System.out.println(linkedList.subList(0, 5));  //打印：[]

        //无法为返回值为void的类型打桩，编译会不通过
        //when(linkedList.clear());

        //验证单个对象执行顺序
        List singleMock = mock(List.class);
        singleMock.add("was added first");
        singleMock.add("was added second");
        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("was added first");

        //查找冗余的调用(即未被验证到的)  如果这个位置执行会报异常，因为second还没验证
        try {
            verifyNoMoreInteractions(singleMock);
        } catch (Error error) {  //使用Error来捕获
            System.out.println("second还没验证 ! "); //打印：second还没验证 !
        }
        inOrder.verify(singleMock).add("was added second");

        //验证多对象执行顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);
        firstMock.add("was added first");
        secondMock.add("was added second");
        InOrder inOrder2 = inOrder(firstMock, secondMock);
        inOrder2.verify(firstMock).add("was added first");
        inOrder2.verify(secondMock).add("was added second");

        // 验证mock对象没有交互过
        verifyZeroInteractions(firstMock, secondMock);

        //为连续的调用做测试桩 (stub)
        when(linkedList.size()).thenThrow(new RuntimeException()).thenReturn(2);
        try {
            System.out.println(linkedList.size());
        } catch (Exception e) {
            System.out.println("Exception ! ");//打印：Exception !
        }
        System.out.println(linkedList.size());
        //后续调用 : 也是输出2
        System.out.println(linkedList.size());
        when(linkedList.size()).thenReturn(11, 22, 33);
        System.out.println(linkedList.size());//打印：11
        System.out.println(linkedList.size());//打印：22
        System.out.println(linkedList.size());//打印：33
        System.out.println(linkedList.size());//打印：33  用最后一个为准

        //用回调的方式做测试桩
        when(linkedList.get(anyInt())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + Arrays.toString(args);
            }
        });
        System.out.println(linkedList.get(123456));//打印：called with arguments: [123456]

        //注解方式简化mock对象创建
        annotationMockList.add("mock");
        verify(annotationMockList).add("mock");
    }

    /**
     * 是否是string类型参数，自定义判断传入的参数，类似anyString()
     */
    class isStringMatcher extends ArgumentMatcher {

        @Override
        public boolean matches(Object argument) {
            if (argument instanceof String) {
                return true;
            }
            return false;
        }
    }
}
