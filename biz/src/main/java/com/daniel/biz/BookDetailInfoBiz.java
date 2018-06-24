package com.daniel.biz;

import com.daniel.beans.BookDetailBO;
import com.daniel.beans.request.BookStoreRequest;
import com.daniel.utils.page.Pager;

import java.util.List;

/**
 * Created by danielchang on 2018/6/24.
 */
public interface BookDetailInfoBiz {

    public int saveDetailInfo(BookDetailBO item);

    public int saveDetailInfoBatch(List<BookDetailBO> items);

    public Pager<BookDetailBO> queryDetailInfo(BookStoreRequest request);
}
