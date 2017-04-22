package org.zheng.proxy.objenesis;

import lombok.Data;

/**
 * Create by zxb on 2017/4/22
 */
@Data
public class ObjenesisModel {

    private String id;

    private String name;

    public ObjenesisModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void print() {
        System.out.println("ObjenesisModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}');
    }
}
