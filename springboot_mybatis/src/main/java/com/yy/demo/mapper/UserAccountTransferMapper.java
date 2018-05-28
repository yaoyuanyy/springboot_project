package com.yy.demo.mapper;

import com.yy.demo.bean.UserAccountTransferRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/27 at 上午11:02
 */
@Mapper
public interface UserAccountTransferMapper {

    @Select("SELECT * FROM user_account_transfer WHERE transfer_uuid = #{transferUUid}")
    UserAccountTransferRecord queryByTransferUUid(String transferUUid);

    @Insert("")
    void insert(UserAccountTransferRecord record);

    @Select("SELECT * FROM user_account_transfer WHERE id = #{accountTransferId} FOR UPDATE")
    UserAccountTransferRecord getByIdForUpdate(Long accountTransferId);

    @Update("")
    void updateById(UserAccountTransferRecord tr);
}
