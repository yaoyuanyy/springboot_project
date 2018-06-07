package com.yy.demo.bean;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 用户账户金额转账记录表 user_account_transfer_record
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 上午7:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountTransferRecord {

    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 转账类型 0:转入 1转出
     */
    private Integer type;
    /**
     * 状态：默认：0 不正常单子，正常逻辑中没有这个状态产生；转账处理中：1；成功：2
     */
    private Integer status;
    /**
     * 转账金额
     */
    private BigDecimal amount;
    /**
     * 转账流水号-唯一标识
     */
    private String transferUUid;
    /**
     *
     */
    private Integer retryTimes;
    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date utime;
}
