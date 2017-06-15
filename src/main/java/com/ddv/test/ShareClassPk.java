package com.ddv.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShareClassPk implements Serializable {

	@Column(name="REGION_ID", length=20)
	private String regionId;
	
	@Column(name="SHARE_CLASS_ID", length=20)
	private String shareClassId;
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getShareClassId() {
		return shareClassId;
	}
	public void setShareClassId(String shareClassId) {
		this.shareClassId = shareClassId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShareClassPk) {
			ShareClassPk other = (ShareClassPk)obj;
			return other.shareClassId.equals(shareClassId) && other.regionId.equals(regionId);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return regionId.hashCode() + shareClassId.hashCode();
	}
	
}
