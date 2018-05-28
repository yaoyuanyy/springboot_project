package com.yy.demo.mapper;

import com.yy.demo.bean.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 下午1:13
 */
@Mapper
public interface UserAccountMapper {

    @Select("")
    UserAccount getByIdForUpdate(long userId);

    @Update("")
    void updateById(UserAccount userAccount);
}

