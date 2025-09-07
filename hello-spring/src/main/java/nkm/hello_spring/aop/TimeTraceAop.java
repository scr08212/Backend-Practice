package nkm.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* nkm.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();

        System.out.println("Start: "+pjp.toString());

        try {
            return pjp.proceed();
        } finally {
            long finish =  System.currentTimeMillis();
            long duration = finish - start;
            System.out.println("Duration: "+pjp.toString()+" "+duration+"ms");
        }
    }
}
