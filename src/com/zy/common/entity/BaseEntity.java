package com.zy.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zy.common.util.CustomDateSerializer;

@MappedSuperclass
public class BaseEntity extends IdEntity implements Serializable {
	
	private static final long serialVersionUID = 1024354665982751553L;
	
	public static final Integer DELETE_FLAG_DELETED = 1;
	public static final Integer DELETE_FLAG_NORMAL = 0;
	
	/**
	 * 创建日期
	 */
	public static final String CREATE_DATE_PROPERTY_NAME = "createDate";
	/**
	 * 修改日期
	 */
	public static final String UPDATE_DATE_PROPERTY_NAME = "updateDate";
	
	/**
	 * 创建日期
	 */
	protected Date createDate;
	/**
	 * 修改日期
	 */
	protected Date updateDate;
	/**
	 * 删除标志(0-正常 1-删除)
	 * @return
	 */
	protected Integer deleteFlag;
	/**
	 * 创建人Id
	 * @return
	 */
	protected String createId;
	
	/**
	 * 创建人名称
	 */
	protected String createName;
	
	@Column(updatable=false)
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@Column(name = "create_id",length=32)
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
	@Column(name = "create_name",length=64)
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
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

	@PrePersist
    public void prePersist() {
		if(this.createDate == null){
			this.setCreateDate(new Date());
		}
		this.setUpdateDate(new Date());
		this.setDeleteFlag(BaseEntity.DELETE_FLAG_NORMAL);
    }
 
    @PreUpdate
    public void preUpdate() {
    	this.setUpdateDate(new Date());
    }
	
}
