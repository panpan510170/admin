package com.pan.model.vo.system;

import com.pan.model.entitys.system.SPermissions;
import lombok.Data;

import java.util.List;

/**
 * @author pan
 * @date 2019/9/24 14:48
 */
@Data
public class PerListVO {
    private List<SPermissions> list;
}
