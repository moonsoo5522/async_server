package com.board.async.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@Entity(name = "member")
public class Member {
    @Id
    @Column(name = "mem_no")
    private long memNo;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "nation_cd")
    private String nationCd;

    @Column(name = "age_range")
    private int ageRange;

    @Column(name = "reg_dt")
    private String regDt;

    @Override
    public String toString() {
        return memNo + ", " + ageRange + ", " + regDt;
    }
}
