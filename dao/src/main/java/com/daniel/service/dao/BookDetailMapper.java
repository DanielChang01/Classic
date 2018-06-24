package com.daniel.service.dao;

import com.daniel.beans.BookDetailBO;
import com.daniel.beans.request.BookStoreRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailMapper {

    int insertSelective(@Param("item") BookDetailBO item);

    int insertList(@Param("items") List<BookDetailBO> item);

    int update(@Param("item") BookDetailBO item);

    List<BookDetailBO> queryDetailInfo(BookStoreRequest request);

    int count(BookStoreRequest request);
}
