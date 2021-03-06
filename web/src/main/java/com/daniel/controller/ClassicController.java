package com.daniel.controller;

import com.daniel.biz.TestBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by danielchang on 2018/6/23.
 */
@Controller
@RequestMapping(value = "daniel/classic")
public class ClassicController {
    @Resource
    private TestBiz testBiz;

    @RequestMapping(value = "print")
    @ResponseBody
    public String printOnly(@RequestParam(value = "content")String content, HttpServletRequest request){
        System.out.println(testBiz.getInput(content));
        System.out.println(request.getRequestURI());
        return content;
    }
}
