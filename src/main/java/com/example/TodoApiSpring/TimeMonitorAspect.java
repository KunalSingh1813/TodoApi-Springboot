package com.example.TodoApiSpring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logtime(ProceedingJoinPoint joinpoint){

        long start = System.currentTimeMillis(); //start time of the code
    try{
    //execute the join point
        joinpoint.proceed();

    }catch (Throwable e){
        System.out.println("Something went wrong during the execution");
    }finally {
        long end = System.currentTimeMillis();
        long totalExecutionTime = end -start;
        System.out.println("Total time of execution of the method is: "+ totalExecutionTime +" ms..");
    }

    }

}
