package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    // 모든 order 패키지 하위 패키지의 메소드
    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder() {} // 포인트컷 시그니처

    // 모든 패키지의 클래스 중 ~Service의 클래스명 패턴을 가진 클래스
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {} // 포인트컷 시그니처

    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
