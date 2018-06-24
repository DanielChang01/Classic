package com.daniel.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by danielchang on 2018/6/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDetailBO {
    private Long id;
    private String bookNameCN;//书名
    private String innerId;//同名书编号
    private String author;//作者
    private String translator;//译者
    private String publishingHouse;//出版社
    private Date publishDate;//出版时间
    private Integer pageNumber;//页数
    private String bindingAndLayout;//装帧
    private BigDecimal price;//价格
    private String ISBN;
    private BigDecimal score;//评分
    private String categoryCode;//分类编码
    private String categoryName;//分类名称
    private String bigCategoryCode;//大分类
    private String bigCategoryName;
    private String middleCategoryCode;//中分类
    private String middleCategoryName;
    private String smallCategoryCode;//小分类
    private String smallCategoryName;
    private String remark;//备注
    private Date createTime;
    private Date updateTime;
}
