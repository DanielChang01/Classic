package com.daniel.biz.impl;

import com.daniel.beans.BookDetailBO;
import com.daniel.beans.request.BookStoreRequest;
import com.daniel.biz.BookDetailInfoBiz;
import com.daniel.service.BookDetailService;
import com.daniel.utils.page.Pager;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by danielchang on 2018/6/24.
 */
@Component
public class BookDetailInfoBizImpl implements BookDetailInfoBiz {
    @Resource
    private BookDetailService bookDetailService;

    @Override
    public int saveDetailInfo(BookDetailBO item) {
        return bookDetailService.insertSelective(item);
    }

    @Override
    public int saveDetailInfoBatch(List<BookDetailBO> items) {
        if (CollectionUtils.isEmpty(items))
            return 0;
        int num = 0;
        for (BookDetailBO item : items) {
            num += bookDetailService.insertSelective(item);
        }
        return num;
    }

    @Override
    public Pager<BookDetailBO> queryDetailInfo(BookStoreRequest request) {
        int count = bookDetailService.count(request);
        List<BookDetailBO> bookDetailBOS = bookDetailService.queryDetailInfo(request);
        Pager<BookDetailBO> detailBOPager = Pager.builder(bookDetailBOS).
                        total(count).current(request.getPage()).create();
        return detailBOPager;
    }
}
