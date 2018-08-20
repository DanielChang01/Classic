package com.daniel.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.daniel.annotation.JsonResponseBody;
import com.daniel.utils.common.JsonResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by danielchang on 2018/8/19.
 */
@Controller
@RequestMapping(value = "daniel/login")
public class LoginController {

    @RequestMapping("/sign_in")
    @ResponseBody
    public JsonResult<Object> signIn(@RequestParam(value = "emailAddr") String emailAddr,
                                     @RequestParam(value = "password") String password){
        try {
            System.out.println(emailAddr + "--" + password);
            return JsonResult.success("123");
        }catch (Exception e){
            return JsonResult.error("234");
        }

    }
}
