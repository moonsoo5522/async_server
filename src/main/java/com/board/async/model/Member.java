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
    private String gender;
    private String nationCd;
    private int ageRange;
    private String regDt;

    @Override
    public String toString() {
        return memNo + ", " + ageRange + ", " + regDt;
    }
}
