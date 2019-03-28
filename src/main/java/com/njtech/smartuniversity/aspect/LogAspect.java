package com.njtech.smartuniversity.aspect;

import com.google.gson.Gson;
import com.njtech.smartuniversity.annotation.ServiceAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ritchie on 6/27/18
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();


    /**
     * 监控com.njtech.smartuniversity.dao包及其子包的所有方法
     */
    @Pointcut("execution(* com.njtech.smartuniversity.dao.*.*(..))")
    private void dao() {
    }

    /**
     * 监控com.njtech.smartuniversity.controller包及其子包的所有方法
     */
    @Pointcut(value = "execution(* com.njtech.smartuniversity.controller.*.*(..))")
    public void controller() {
    }

    /**
     * 监控com.njtech.smartuniversity.service包及其子包的所有方法
     */
    @Pointcut(value = "execution(* com.njtech.smartuniversity.service..*.*(..))")
    public void service() {
    }


    @Before("controller() || service() || dao()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        startTime.set(System.currentTimeMillis());
        Gson gson = new Gson();
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//
//        //记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //获取用户请求方法的参数并序列化为JSON格式字符串
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params.append(gson.toJson(joinPoint.getArgs()[i])).append(";");
            }
        }
        HttpSession session = request.getSession();
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            logger.info("===== 前置通知开始=====");
            logger.info("URL : " + request.getRequestURL().toString());
            logger.info("HTTP请求类型 : " + request.getMethod());
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            logger.info("方法描述:" + getServiceMthodDescription(joinPoint));
//            logger.info("请求人ID:" + user.getId());
//            logger.info("请求人NAME:" + user.getName());
            System.out.println("请求IP:" + ip);
            logger.info("请求参数:" + params);
            System.out.println("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }


//    /**
//     * 声明环绕通知
//     *
//     * @param pjp
//     * @return
//     * @throws Throwable
//     */
//    @Around("dao() || controller() || service()")
//    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//        long begin = System.nanoTime();
//        Object obj = pjp.proceed();
//        long end = System.nanoTime();
//
//        logger.info("调用方法：{}，\n参数：{}，\n执行耗时：{}纳秒，\r\n耗时：{}毫秒",
//                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()),
//                (end - begin), (end - begin) / 1000000);
//        return obj;
//    }


    @AfterReturning(returning = "result", pointcut = "dao() || controller() || service()")
    public void doAfter(JoinPoint joinPoint, Object result) {
        //处理完请求，返回内容
        Gson gson = new Gson();
        String res = gson.toJson(result);
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params.append(gson.toJson(joinPoint.getArgs()[i])).append(";");
            }
        }
        try {
            logger.info("===== 后置通知开始=====");
            logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            logger.info("方法描述:" + getServiceMthodDescription(joinPoint));
//            logger.info("请求人ID:" + user.getId());
//            logger.info("请求人NAME:" + user.getName());
            logger.info("请求IP:" + ip);
            logger.info("请求参数:" + params);
            logger.info("返回值为：" + res);
            logger.info("耗时 : {} 毫秒", (System.currentTimeMillis() - startTime.get()));


        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
    }

    @AfterThrowing(pointcut = "dao() || controller() || service()", throwing = "e")
    public void doThrow(JoinPoint joinPoint, Throwable e) {

        Gson gson = new Gson();
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //请求IP address
        String ip = request.getRemoteAddr();

        //获取用户请求方法的参数并序列化为JSON格式字符串
        StringBuilder params = new StringBuilder();
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params.append(gson.toJson(joinPoint.getArgs()[i])).append(";");
            }
        }
        try {
            logger.error("=====异常通知开始=====");
            logger.error("异常代码:" + e.getClass().getName());
            logger.error("异常信息:" + e.getMessage());
            logger.error("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            logger.error("方法描述:" + getServiceMthodDescription(joinPoint));
            logger.error("请求IP:" + ip);
            logger.error("请求参数:" + params.toString());
            logger.error("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
        /*==========记录本地异常日志==========*/
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params.toString());
    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ServiceAnnotation.class).description();
                    break;
                }
            }
        }
        return description;
    }
}
