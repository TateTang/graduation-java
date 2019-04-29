package com.gxy.tmf.signin.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gxy.tmf.signin.model.Arrive;

public interface ArriveRepository extends JpaRepository<Arrive, Integer>{
	/**
	 * 不带分页全查询
	 * @param spec 条件构造器
	 * @return
	 */
	List<Arrive> findAll(Specification<Arrive> spec);
}
