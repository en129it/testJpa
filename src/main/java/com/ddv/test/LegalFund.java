package com.ddv.test;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="LEGAL_FUND")
public class LegalFund {

	@EmbeddedId
	private LegalFundPk legalFundPk;
	@Column(name="LEGAL_FUND_NAME", length=20)
	private String legalFundName;
	@OneToMany(mappedBy="legalFund", fetch=FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	private Set<ShareClass> shareClasses;
	
	public LegalFund() {
		
	}
	
	public LegalFund(String regionId, String legalFundId, String legalFundName) {
		this.legalFundPk = new LegalFundPk();
		this.legalFundPk.setRegionId(regionId);
		this.legalFundPk.setLegalFundId(legalFundId);
		this.legalFundName = legalFundName;
	}
	
	public LegalFundPk getLegalFundPk() {
		return legalFundPk;
	}
	public void setLegalFundPk(LegalFundPk legalFundPk) {
		this.legalFundPk = legalFundPk;
	}
	public String getLegalFundName() {
		return legalFundName;
	}
	public void setLegalFundName(String legalFundName) {
		this.legalFundName = legalFundName;
	}
	public Set<ShareClass> getShareClasses() {
		return shareClasses;
	}
	public void setShareClasses(Set<ShareClass> shareClasses) {
		this.shareClasses = shareClasses;
	}
	
	@Override
	public String toString() {
		return "Legal fund (regionId=" + this.legalFundPk.getRegionId() + ", id=" + this.legalFundPk.getLegalFundId() + ", name=" + this.legalFundName + ", nb share classes=" + ((this.shareClasses!=null) ? this.shareClasses.size() : "null") + ")";
	}
}
