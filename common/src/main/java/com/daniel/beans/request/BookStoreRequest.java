package com.daniel.beans.request;

import com.daniel.utils.common.JsonDateFormatDeserialize;
import com.daniel.utils.page.PageRequest;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by danielchang on 2018/6/24.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookStoreRequest extends PageRequest {
    private String bookName;//书名

    @JsonDeserialize(using = JsonDateFormatDeserialize.class)
    private Date startPub;//出版开始时间

    @JsonDeserialize(using = JsonDateFormatDeserialize.class)
    private Date endPub;//出版结束时间
}
