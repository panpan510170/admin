package com.pan.admin.Json;

import com.alibaba.fastjson.JSON;
import com.pan.model.entitys.system.SPermissions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author pan
 * @date 2019/9/24 14:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JTest {
    @Test
    public void testJia(){
        String a = "[{\"permissionsName\":\"系统管理\",\"type\":1,\"serialNumber\":1,\"permissionsUrl\":\"/\",\"parentId\":0},{\"permissionsName\":\"用户管理\",\"type\":2,\"serialNumber\":1,\"permissionsUrl\":\"/system/suserList\",\"parentId\":1},{\"permissionsName\":\"角色管理\",\"type\":2,\"serialNumber\":2,\"permissionsUrl\":\"/system/sroleList\",\"parentId\":1},{\"permissionsName\":\"权限管理\",\"type\":2,\"serialNumber\":3,\"permissionsUrl\":\"/system/sPermissionsList\",\"parentId\":1},{\"permissionsName\":\"用户角色管理\",\"type\":2,\"serialNumber\":4,\"permissionsUrl\":\"/system/suserRoleList\",\"parentId\":1},{\"permissionsName\":\"增加用户\",\"type\":3,\"serialNumber\":1,\"permissionsUrl\":\"/system/addSystemUser\",\"parentId\":2,\"permissions\":\"user:add\"},{\"permissionsName\":\"增加权限\",\"type\":3,\"serialNumber\":1,\"permissionsUrl\":\"/system/addSystemUser\",\"parentId\":4,\"permissions\":\"permissions:add\"},{\"permissionsName\":\"增加角色\",\"type\":3,\"serialNumber\":1,\"permissionsUrl\":\"/system/addSystemUser\",\"parentId\":5,\"permissions\":\"role:add\"},{\"permissionsName\":\"平台维护\",\"type\":1,\"serialNumber\":1,\"permissionsUrl\":\"/\",\"parentId\":0},{\"permissionsName\":\"socket消息推送\",\"type\":2,\"serialNumber\":1,\"permissionsUrl\":\"/platForm/send/socketMsg\",\"parentId\":2,\"permissions\":\"platForm:sendSocketMsg\"}]";
        //PerListVO permissionsListVO = BeanHandler.jsonString2Bean(a, PerListVO.class);

        List<SPermissions> sPermissions = JSON.parseArray(a, SPermissions.class);
        for(SPermissions s : sPermissions){
            System.out.println(JSON.toJSON(s));
        }
    }
}
