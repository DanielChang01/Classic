package com.daniel.utils.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by danielchang on 2018/7/18.
 */
public class Scale6Decimal extends AbstractFixedScaleDecimal<Scale6Decimal> implements Serializable {

    public static final int SCALE = 6;

    private static final long serialVersionUID = -8431631953979262675L;

    public Scale6Decimal() {
        super(SCALE);
    }

    public Scale6Decimal(BigDecimal amount) {
        super(amount, SCALE);
    }
}