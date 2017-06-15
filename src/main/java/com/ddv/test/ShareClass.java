package com.ddv.test;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SHARE_CLASS")
public class ShareClass {

	@EmbeddedId
	private ShareClassPk shareClassPk;
	@Column(name="SHARE_CLASS_CODE", length=20)
	private String shareClassCode;
	@Column(name="SHARE_CLASS_NAME", length=20)
	private String shareClassName;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name="LEGAL_FUND_ID"),
		@JoinColumn(name="LEGAL_FUND_REGION_ID"),
	})
	private LegalFund legalFund;

	public ShareClass() {
		
	}
	
	public ShareClass(String regionId, String shareClassId, String shareClassCode, String shareClassName, LegalFund legalFund) {
		this.shareClassPk = new ShareClassPk();
		this.shareClassPk.setRegionId(regionId);
		this.shareClassPk.setShareClassId(shareClassId);
		this.shareClassCode = shareClassCode;
		this.shareClassName = shareClassName;
		this.legalFund = legalFund;
		if (this.legalFund.getShareClasses()==null) {
			this.legalFund.setShareClasses(new HashSet<ShareClass>());
		}
		this.legalFund.getShareClasses().add(this);
	}
	
	public ShareClassPk getShareClassPk() {
		return shareClassPk;
	}
	public void setShareClassPk(ShareClassPk shareClassPk) {
		this.shareClassPk = shareClassPk;
	}
	public String getShareClassCode() {
		return shareClassCode;
	}
	public void setShareClassCode(String shareClassCode) {
		this.shareClassCode = shareClassCode;
	}
	public String getShareClassName() {
		return shareClassName;
	}
	public void setShareClassName(String shareClassName) {
		this.shareClassName = shareClassName;
	}
	public LegalFund getLegalFund() {
		return legalFund;
	}
	public void setLegalFund(LegalFund legalFund) {
		this.legalFund = legalFund;
	}

	@Override
	public String toString() {
		return "Share class (regionId=" + this.shareClassPk.getRegionId() + ", id=" + this.shareClassPk.getShareClassId() + ", name=" + this.getShareClassName() + ", legal fund=" + this.getLegalFund().getLegalFundPk().getLegalFundId() + ")";
	}
}
