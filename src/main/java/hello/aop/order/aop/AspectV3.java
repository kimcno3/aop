package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {
    // 모든 order 패키지 하위 패키지의 메소드
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} // 포인트컷 시그니처

    // 모든 패키지의 클래스 중 ~Service의 클래스명 패턴을 가진 클래스
    @Pointcut("execution(* *..* .*(..))")
    private void allService(){} // 포인트컷 시그니처

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // order.aop 하위 패키지 중 *Service 패턴의 클래스명을 가진 클래스
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[transaction start] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[transaction rollback] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[resource release] {}", joinPoint.getSignature());
        }
    }
}
