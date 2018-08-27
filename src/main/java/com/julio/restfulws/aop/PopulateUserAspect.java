package com.julio.restfulws.aop;

import com.julio.restfulws.annotations.PopulateUserOnPost;
import com.julio.restfulws.annotations.ValidateUser;
import com.julio.restfulws.entities.Post;
import com.julio.restfulws.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Configuration
@Slf4j
public class PopulateUserAspect {

    @Autowired
    private UserService userService;

    @Around("com.julio.restfulws.aop.CommonJoinPointConfig.populateUserAnnotation()")
    public Object populateUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering in the method {} in the class {}", joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());

        Method method = getMethod(joinPoint);
        PopulateUserOnPost methodAnnotations = method.getAnnotation(PopulateUserOnPost.class);

        Long userId = (Long) Arrays.asList(joinPoint.getArgs()).get(Integer.valueOf(methodAnnotations.positionOfUserId()));
        Post post = (Post) Arrays.asList(joinPoint.getArgs()).get(Integer.valueOf(methodAnnotations.positionOfPostObject()));
        post.setUser(userService.findOne(userId));

        return joinPoint.proceed();
    }

    @Around("com.julio.restfulws.aop.CommonJoinPointConfig.validateUserAnnotation()")
    public Object validateUser(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering in the method {} in the class {}", joinPoint.getSignature().getName(), joinPoint.getSignature().getDeclaringTypeName());

        Method method = getMethod(joinPoint);
        ValidateUser methodAnnotations = method.getAnnotation(ValidateUser.class);

        Long userId = (Long) Arrays.asList(joinPoint.getArgs()).get(Integer.valueOf(methodAnnotations.positionOfUserId()));
        userService.findOne(userId);

        return joinPoint.proceed();
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
