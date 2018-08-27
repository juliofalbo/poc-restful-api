package com.julio.restfulws.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {
	
	@Pointcut("@annotation(com.julio.restfulws.annotations.PopulateUserOnPost)")
	public void populateUserAnnotation(){}

    @Pointcut("@annotation(com.julio.restfulws.annotations.ValidateUser)")
    public void validateUserAnnotation(){}

}
