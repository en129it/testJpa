package com.ddv.test;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;

public class LegalFundSpecification implements Specification<LegalFund> {

	@Override
	public Predicate toPredicate(Root<LegalFund> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Subquery<ShareClass> subQuery = query.subquery(ShareClass.class);
		Root<ShareClass> subQueryRoot = subQuery.from(ShareClass.class);
		subQuery.where(subQueryRoot.get("shareClassPk").get("shareClassId").in(Arrays.asList("041", "042")));
		subQuery.select(subQueryRoot);

		return builder.and(
				builder.equal(root.get("legalFundPk").get("regionId"), "LAB1"),
				root.get("shareClasses").in(subQuery)
				); 

/*
		Join<Object, Object> shareClasses = root.join("shareClasses");
		return (builder.and(
				builder.equal(shareClasses.get("shareClassPk").get("regionId"), "LAB1"),
				builder.equal(shareClasses.get("shareClassPk").get("regionId"), "LAB1")) )
				;
				*/
	}

}
