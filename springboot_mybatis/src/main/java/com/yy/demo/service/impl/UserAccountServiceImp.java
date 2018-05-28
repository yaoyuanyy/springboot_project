package com.yy.demo.service.impl;

import com.yy.demo.bean.UserAccount;
import com.yy.demo.mapper.UserAccountMapper;
import com.yy.demo.service.UserAccountService;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 下午1:01
 */
@Service
public class UserAccountServiceImp implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void incrBalance(long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException(String.valueOf("参数违法"));
        }

        UserAccount userAccount = userAccountMapper.getByIdForUpdate(userId);

        userAccount.setUseableBalance(userAccount.getUseableBalance().add(amount));
        userAccountMapper.updateById(userAccount);
    }

    @Override
    public void decrBalance(long userId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException(String.valueOf("参数违法"));
        }

        UserAccount userAccount = userAccountMapper.getByIdForUpdate(userId);

        userAccount.setUseableBalance(userAccount.getUseableBalance().subtract(amount));
        userAccountMapper.updateById(userAccount);
    }
}
