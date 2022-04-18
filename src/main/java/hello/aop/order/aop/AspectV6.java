package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6 {
/*
    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[transaction start] {}", joinPoint.getSignature());

            Object result = joinPoint.proceed();

            //@AfterReturning
            log.info("[transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[transaction rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[resource release] {}", joinPoint.getSignature());
        }
    }
*/

    // 핵심 로직 수행 전에 추가할 경우
    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    // 핵심 로직 수행 후 정상적으로 리턴값을 받아올 경우, 매개변수로 리턴값 매칭 가능
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] {}, result = {}", joinPoint.getSignature(), result);
    }

    // 핵심 로직 수행 후 예외 발생할 경우, 매개변수로 예외 클래스 매칭 가능
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[AfterThrowing] {}, ex = {}", joinPoint.getSignature(), ex);
    }

    // 핵심 로직 수행 후 결과에 상관없이 실행할 어드바이스
    @After(value = "hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[After] {}", joinPoint.getSignature());
    }
}
