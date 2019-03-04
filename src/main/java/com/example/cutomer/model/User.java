package com.example.cutomer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.entity.BaseEntity;

@Entity
@Table(name="user")
public class User extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -2067022014590584536L;
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 用户名
	 */
	@Column(name = "zs_user_name")
	private String ZSuserName;
	/**
	 * 密码
	 */
	@Column(name = "pass_word")
	private String password;
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;
	/**
	 * 用户状态
	 */
	@Column(name = "lock_status")
	private Integer lockStatus;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private String createTime;
	/**
	 * 级别
	 * 0超级管理员
	 * 1运营
	 * 2机构管理员
	 * 3普通用户
	 */
	@Column(name = "level")
	private Integer level;

	/**
	 * 机构id
	 */
	@Column(name = "org_id")
	private Integer orgId;

	/**
	 * 机构String
	 */
	@Transient
	private String organization;
	/**
	 * 拥有角色的备注
	 */
	@Transient
	private String roleRemark;

	/**
	 * 创建该用户的userId
	 */
	@Column(name = "create_user_id")
	private Long createUserId;
	
	/**
	 * 创建该用户的用户姓名
	 */
	@Transient
	private String  createUserName;
	
	/**
	 * Transient 不映射到数据库的属性
	 */
	@Transient
	private String address;
	
	public User() {
	}

	public User(String userName, String email, Integer lockStatus,
			Integer level, Integer orgId) {
		super();
		this.userName = userName;
		this.email = email;
		this.lockStatus = lockStatus;
		this.level = level;
		this.orgId = orgId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getZSuserName() {
		return ZSuserName;
	}

	public void setZSuserName(String zSuserName) {
		ZSuserName = zSuserName;
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

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
}
