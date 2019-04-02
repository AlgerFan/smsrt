package com.fan.smsrt.util;

/**
 * @Auther: 阿杰
 * @Date: 2018/4/22 09:54
 * @Description:
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultTwo<T> implements Serializable {
    /** 错误码. */
    private Integer errno;
    /** 具体的内容. */
    private String [] data;
}
