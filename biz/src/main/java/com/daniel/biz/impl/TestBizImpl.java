package com.daniel.biz.impl;

import com.daniel.biz.TestBiz;
import org.springframework.stereotype.Component;

/**
 * Created by danielchang on 2018/6/23.
 */
@Component
public class TestBizImpl implements TestBiz {
    public String getInput(String input) {
        return input;
    }
}
