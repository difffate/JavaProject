package org.zheng.proxy.cglib.lazyload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 课程表
 * Create by zxb on 2017/4/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private String courseName;

    private Date courseTime;
}
