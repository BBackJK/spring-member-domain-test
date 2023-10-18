package bback.test.proviider.events;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

@Aspect
@Slf4j
@Configuration
public class EventRouterAspect {

    private final ApplicationContext applicationContext;

    public EventRouterAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Pointcut("@annotation(bback.test.proviider.events.EventCatcher)")
    public void pointCut() {}


    @AfterReturning(pointcut = "pointCut()", returning = "returningValue")
    public void after(JoinPoint joinPoint, Object returningValue) {
        Signature signature = joinPoint.getSignature();
        boolean isMethod = signature instanceof MethodSignature;

        if (!isMethod) {
            return;
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        EventCatcher eventCatcher = AnnotationUtils.findAnnotation(methodSignature.getMethod(), EventCatcher.class);
        if (eventCatcher == null) {
            log.warn("EventCatcher is Empty.");
            return;
        }

        Class<?> eventHandlerType = eventCatcher.value();

        try {
            EventAspectHandler<Object> eventAspectHandler = (EventAspectHandler<Object>) this.applicationContext.getBean(eventHandlerType);
            eventAspectHandler.handle(returningValue);
        } catch (Exception ex) {
            log.error("Event handler occur Exception");
            log.error(ex.getMessage());
        }
    }

}
