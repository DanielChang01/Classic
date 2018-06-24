package com.daniel.service.impl;

import com.daniel.beans.BookDetailBO;
import com.daniel.beans.request.BookStoreRequest;
import com.daniel.service.dao.BookDetailMapper;
import com.daniel.service.BookDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by danielchang on 2018/6/24.
 */
@Service
public class BookDetailServiceImpl implements BookDetailService {
    @Resource
    private BookDetailMapper bookDetailMapper;

    @Override
    public int insertSelective(BookDetailBO item) {
        return bookDetailMapper.insertSelective(item);
    }

    @Override
    public int insertList(List<BookDetailBO> items) {
        return bookDetailMapper.insertList(items);
    }

    @Override
    public int updateById(BookDetailBO item) {
        return bookDetailMapper.update(item);
    }

    @Override
    public List<BookDetailBO> queryDetailInfo(BookStoreRequest request) {
        return bookDetailMapper.queryDetailInfo(request);
    }

    @Override
    public int count(BookStoreRequest request) {
        return bookDetailMapper.count(request);
    }
}
