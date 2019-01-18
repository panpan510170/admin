package com.mshd.repository;

import com.mshd.entitys.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/1/17 0017.
 */
public interface TestRepository extends JpaRepository<Test,Long>{

    /**
    * 通过@Modifying结合@Query进行修改操作
    */
   /* @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update test set age=:age where id=:id")
    void updateById(@Param("age") Integer age, @Param("id") Integer id);*/

    /*Page<Test> findAll(Specification<Test> spec, Pageable pageable);*/
}
