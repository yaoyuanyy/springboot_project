package com.yy.demo.service;

import java.math.BigDecimal; /**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 下午12:23
 */
public interface UserAccountService {
    void incrBalance(long userId, BigDecimal amount);

    void decrBalance(long userId, BigDecimal amount);
}

