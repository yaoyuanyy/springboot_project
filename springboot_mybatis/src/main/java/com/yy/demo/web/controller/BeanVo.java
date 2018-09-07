package com.yy.demo.web.controller;

import com.yy.demo.web.config.Phone;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/9/3 at 下午9:59
 */
@Data
public class BeanVo {

    @Phone(message = "phone not null")
    private String phone;

    @NotBlank(message = "addr not be null")
    private String addr;

}
