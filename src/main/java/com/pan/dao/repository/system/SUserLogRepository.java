package com.pan.dao.repository.system;

import com.pan.model.entitys.system.SUserLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 系统用户操作
 * @author pan
 * @date 2019/9/10 15:11
 */
public interface SUserLogRepository extends JpaRepository<SUserLog,Long> {
}
