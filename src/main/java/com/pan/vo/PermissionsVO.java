package com.pan.vo;

import com.pan.model.BaseDO;
import lombok.Data;

import java.util.List;

@Data
public class PermissionsVO extends BaseDO {

    private String name;

    private String url;

    private String imageUrl;

    private List<PermissionsVO> list;
}
