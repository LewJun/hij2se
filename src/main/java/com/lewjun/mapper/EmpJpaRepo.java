package com.lewjun.mapper;

import com.lewjun.bean.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author LewJun
 * @version v0.1 2018/12/14 11:01 LewJun Exp $$
 */
public interface EmpJpaRepo extends JpaRepository<Emp, Integer>,
        JpaSpecificationExecutor<Emp> {
}
