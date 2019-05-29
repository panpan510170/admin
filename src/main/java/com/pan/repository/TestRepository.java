package com.pan.repository;

import com.pan.entitys.Test;
import org.springframework.data.jpa.repository.JpaRepository;

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
