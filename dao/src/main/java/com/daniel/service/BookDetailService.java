package com.daniel.service;

import com.daniel.beans.BookDetailBO;
import com.daniel.beans.request.BookStoreRequest;

import java.util.List;

/**
 * Created by danielchang on 2018/6/24.
 */
public interface BookDetailService {

    int insertSelective(BookDetailBO item);

    int insertList(List<BookDetailBO> item);

    int updateById(BookDetailBO item);

    List<BookDetailBO> queryDetailInfo(BookStoreRequest request);

    int count(BookStoreRequest request);
}
