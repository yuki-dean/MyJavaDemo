package com.lf.sdk.api;

public interface Flow {
    Flow filter();
    Flow to();
    Flow from(String source);

    void configure();
}
