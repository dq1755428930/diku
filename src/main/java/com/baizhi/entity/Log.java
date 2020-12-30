package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Log
 * @time 2020/12/24-16:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    private String id;
    private String adminName;
    private Date optionTime;
    private String options;
    private String isSuccess;

}
