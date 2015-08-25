package com.zy.common.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springside.modules.utils.Reflections;

import com.zy.common.entity.BaseEntity;


/**
 * Hibernate拦截器
 *
 */
public class EntityInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 9032102434799876095L;

	/**
	 * 保存的回调方法
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (BaseEntity.CREATE_DATE_PROPERTY_NAME.equals(propertyNames[i])
						|| BaseEntity.UPDATE_DATE_PROPERTY_NAME.equals(propertyNames[i])) {
					state[i] = new Date();
				}
			}
			Reflections.invokeSetter(entity, BaseEntity.CREATE_DATE_PROPERTY_NAME, new Date());
		}
		return true;
	}

	/**
	 * 更新的回调方法
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof BaseEntity) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (BaseEntity.UPDATE_DATE_PROPERTY_NAME.equals(propertyNames[i])) {
					currentState[i] = new Date();
				}
			}
			Reflections.invokeSetter(entity, BaseEntity.UPDATE_DATE_PROPERTY_NAME, new Date());
		}
		return true;
	}
	
}
