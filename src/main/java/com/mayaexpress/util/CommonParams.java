package com.mayaexpress.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonParams {

    private int page;
    private int max;
    private boolean pagination;

    public CommonParams() {
        this.page = 0;
        this.max = 10000;
        this.pagination = true;
    }
}