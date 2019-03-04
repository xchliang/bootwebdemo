package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * 实体类 - 基类
 * ============================================================================
 */

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -6718838800112233445L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;// ID
	@Column(name = "base_create_time")
	protected String baseCreateTime;// 创建日期
	@Column(name = "base_update_time")
	protected String baseUpdateTime;// 修改日期
	@Column(name = "create_ip")
	protected String createIp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBaseCreateTime() {
		return baseCreateTime;
	}

	public void setBaseCreateTime(String baseCreateTime) {
		this.baseCreateTime = baseCreateTime;
	}

	public String getBaseUpdateTime() {
		return baseUpdateTime;
	}

	public void setBaseUpdateTime(String baseUpdateTime) {
		this.baseUpdateTime = baseUpdateTime;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	@Transient
	public void onSave() {}
	
	@Transient
	public void onUpdate() {}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof BaseEntity) {
			BaseEntity baseEntity = (BaseEntity) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
	}

}