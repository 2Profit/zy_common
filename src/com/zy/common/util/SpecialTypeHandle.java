package com.zy.common.util;

public interface SpecialTypeHandle<T extends Object> {
	
	public Class<T> getClassSimpleName();  
    
    public T getResultObject(Class<?> eClass,Object value);  
}
