package com.pan.base.util;

import com.pan.model.vo.regist.RegistVO;

/**
 * Created by Pangaofeng on 2018/11/10
 */
public class ValidateUtils {

    public static Boolean validate(RegistVO registVO) {
        if(StringUtils.isNull(registVO.getValidate1()) && StringUtils.isNull(registVO.getValidate2()) &&
                StringUtils.isNull(registVO.getValidate3()) && StringUtils.isNull(registVO.getValidate5())){

            if("1".equals(registVO.getValidate2())){
                if(Integer.parseInt(registVO.getValidate5()) == (Integer.parseInt(registVO.getValidate1()) + Integer.parseInt(registVO.getValidate3()))){
                    return true;
                }else{
                    return false;
                }
            }else if("2".equals(registVO.getValidate2())){
                if(Integer.parseInt(registVO.getValidate5()) == (Integer.parseInt(registVO.getValidate1()) - Integer.parseInt(registVO.getValidate3()))){
                    return true;
                }else{
                    return false;
                }
            }else if("3".equals(registVO.getValidate2())){
                if(Integer.parseInt(registVO.getValidate5()) == (Integer.parseInt(registVO.getValidate1()) * Integer.parseInt(registVO.getValidate3()))){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
