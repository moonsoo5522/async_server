package com.board.async.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequest {

    private String deviceId;
    private String gender;
    private String nationCd;
    private int ageRange;

}
