package com.ddv.test;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface LegalFundRepository extends JpaRepository<LegalFund, LegalFundPk>, JpaSpecificationExecutor<LegalFund> {
	
}