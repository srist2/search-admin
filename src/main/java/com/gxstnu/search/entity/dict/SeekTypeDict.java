package com.gxstnu.search.entity.dict;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "seek_type_dict")
public class SeekTypeDict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_id")
    private Integer dict_id;
    @Column(name = "dict_name")
    private String dict_name;
}
