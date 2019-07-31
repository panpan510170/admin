package com.pan.model.vo.testVO;

import com.pan.model.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Pangaofeng on 2018/9/11
 */
public class UserVO extends BaseVO {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户编号")
    private String userNo;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
