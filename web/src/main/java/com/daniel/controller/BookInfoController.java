package com.daniel.controller;

import com.daniel.beans.BookDetailBO;
import com.daniel.beans.request.BookStoreRequest;
import com.daniel.biz.BookDetailInfoBiz;
import com.daniel.utils.common.JsonResult;
import com.daniel.utils.page.Pager;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by danielchang on 2018/6/24.
 */
@Controller
@RequestMapping("classic/book")
public class BookInfoController {

    @Resource
    private BookDetailInfoBiz bookDetailInfoBiz;

    @RequestMapping("/saveDetail/v1")
    @ResponseBody
    public JsonResult<String> saveDetail(@RequestBody BookDetailBO detailBO) {
        try {
            bookDetailInfoBiz.saveDetailInfo(detailBO);
            return JsonResult.success("存储成功");
        } catch (RuntimeException e) {
            return JsonResult.error("存储失败!");
        }
    }

    @RequestMapping("/queryDetail/v1")
    @ResponseBody
    public JsonResult<Pager<BookDetailBO>> queryDetailByPager(@RequestBody BookStoreRequest request) {
        try {
            if (request.getEndPub() != null)
                request.setEndPub(LocalDateTime.fromDateFields(request.getEndPub()).plusDays(1).minusSeconds(1).toDate());
            Pager<BookDetailBO> detailBOPager = bookDetailInfoBiz.queryDetailInfo(request);
            return JsonResult.success(detailBOPager);
        } catch (RuntimeException e) {
            return JsonResult.error("查询失败!");
        }
    }
}
