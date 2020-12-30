package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "yx_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;

    private String cate_name;

    private Integer levels;

    private String parent_id;

}