package com.mindtree.mystayapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Role {

	@Id
	private Integer roleId;
	private String roleType;
	private String roleDescription;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	

	public Role() {
		super();
	}

	public Role(String roleType, String roleDescription) {
		super();
		this.roleType = roleType;
		this.roleDescription = roleDescription;
	}

	public Role(int roleId, String roleType, String roleDescription) {
		this.roleId = roleId;
		this.roleType = roleType;
		this.roleDescription = roleDescription;

	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);

}
}
