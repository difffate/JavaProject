package org.zheng.unittest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by zhengxb on 2017/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
//@RunWith(MockitoJUnitRunner.class)
public class MyControllerTest {

    @Mock
    private MyRepository myRepository;

    @InjectMocks
    @Autowired
    private MyService myService;

//    @InjectMocks
    @Autowired
    private MyController myController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        ReflectionTestUtils.setField(myController, "myService", myService);
        Model model = new Model(11L, "AAA");
        doNothing().when(myRepository).doSomething();
        when(myRepository.findById(11L)).thenReturn(model);
    }

    @Test
    public void doSomething() throws Exception {
        this.myController.doSomething();
    }

    @Test
    public void findById() throws Exception {
        System.out.println(this.myController.findById(11L));
    }

}