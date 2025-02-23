package com.fno.crm.domain;

import lombok.Data;

@Data
public class KV {
    //键
    private String k;
    //第一个值
    private Object v1;
    //第二个值
    private Object v2;
    //第三个值
    private Object v3;

    public KV(String k, Object v1,Object v2,Object v3) {
        this.k = k;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
    public KV(String k,Object v1,Object v2){
        this(k, v1, v2, null);
    }
    public KV(String k,Object v1){
        this(k, v1, null, null);
    }
}
