package com.ddv.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.util.ObjectUtils;

@Embeddable
public class LegalFundPk implements Serializable {

	@Column(name="REGION_ID", length=20)
	private String regionId;
	
	@Column(name="LEGAL_FUND_ID", length=20)
	private String legalFundId;
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getLegalFundId() {
		return legalFundId;
	}
	public void setLegalFundId(String legalFundId) {
		this.legalFundId = legalFundId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LegalFundPk) {
			LegalFundPk other = (LegalFundPk)obj;
			return other.legalFundId.equals(legalFundId) && other.regionId.equals(regionId);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return regionId.hashCode() + legalFundId.hashCode();
	}
}
