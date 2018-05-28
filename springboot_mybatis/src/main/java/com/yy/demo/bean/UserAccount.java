package com.yy.demo.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 用户账户表
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 上午7:36
 */
public class user_account {


    /**
     *
     */
    private Long id;
    /**
     *
     */
    private Date createdDate;
    /**
     *
     */
    private Date modifyDate;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 可用余额
     */
    private BigDecimal balance;
    /**
     * 冻结余额
     */
    private BigDecimal frozenBalance;
    /**
     * 可提现余额
     */
    private BigDecimal balance;
}
