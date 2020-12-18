package com.backend.shop.common;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //日志
//    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 捕捉所有Shiro异常
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<String> handle401(ShiroException e) {
        String msg = "无权访问(Unauthorized):" + e.getMessage();
        System.out.println(new Date() + "  [EXCEPTION]ShiroException:   " + msg);
//        return new GlobalResult(401, "无权访问(Unauthorized):" + e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
    }

    /**
     * 单独捕捉Shiro(UnauthorizedException)异常 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handle401(UnauthorizedException e) {
        String msg = "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")";
        System.out.println(new Date() + "  [EXCEPTION]UnauthorizedException:   " + msg);
//        return new GlobalResult(401, "无权访问(Unauthorized):当前Subject没有此请求所需权限(" + e.getMessage() + ")");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
    }

    /**
     * 单独捕捉Shiro(UnauthenticatedException)异常
     * 该异常为以游客身份访问有权限管控的请求无法对匿名主体进行授权，而授权失败所抛出的异常
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<String> handle401(UnauthenticatedException e) {
        String msg = "无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)";
        System.out.println(new Date() + "  [EXCEPTION]UnauthenticatedException:   " + msg);
//        return new GlobalResult(401, "无权访问(Unauthorized):当前Subject是匿名Subject，请先登录(This subject is anonymous.)");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
    }

    /**
     * 捕捉校验异常(BindException)
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> validException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> error = this.getValidError(fieldErrors);
        String msg = error.get("errorMsg").toString();
        System.out.println(new Date() + "  [EXCEPTION]BindException:   " + msg);
//        return new GlobalResult(400, error.get("errorMsg").toString(), error.get("errorList"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    /**
     * 捕捉JWT解析异常
     */
    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<String> decodeException(JWTDecodeException e) {
        String msg = "异常校验码: " + e.getMessage();
        System.out.println(new Date() + "  [EXCEPTION]JWTDecodeException:   " + msg);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
    }


    /**
     * 捕捉404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handle(NoHandlerFoundException e) {
//        return new GlobalResult(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /**
     * 捕捉其他所有异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(HttpServletRequest request, Throwable ex) {
//        return new GlobalResult(500, ex.toString() + ": " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.toString() + ": " + ex.getMessage());
    }


    /**
     * 获取状态码
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 获取校验错误信息
     */
    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<String> errorList = new ArrayList<String>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField() + "-" + error.getDefaultMessage() + ".");
        }
        map.put("errorList", errorList);
        map.put("errorMsg", errorMsg);
        return map;
    }
}

