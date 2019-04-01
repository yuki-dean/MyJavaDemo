package com.lf.sdk.api;

public class MyFlow2 extends MyFlow{
    public void configure() {
        from("hell").from("hee").filter().to();
    }
}
