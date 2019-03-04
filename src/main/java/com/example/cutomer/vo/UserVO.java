package com.example.cutomer.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户VO
 * @author xcl
 *
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = -1394791910712351082L;
	
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 用户状态
	 */
	private Integer lockStatus;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 级别
	 */
	private Integer level;

	/**
	 * 机构id
	 */
	private Integer orgId;
	
	private List<Integer> orgIdList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<Integer> getOrgIdList() {
		return orgIdList;
	}

	public void setOrgIdList(List<Integer> orgIdList) {
		this.orgIdList = orgIdList;
	}

}
