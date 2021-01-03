package com.kingja.wenda.exception;


import com.alibaba.fastjson.JSON;
import com.kingja.wenda.enums.ResultEnum;
import com.kingja.wenda.result.ApiResult;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;

/**
 * Description：TODO
 * Create Time：2018/1/12 13:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = BlogException.class)
//    @ResponseBody
//    public ResultVO handlerSellerException(BlogException e) {
//        return ResultVoUtil.error(e.getCode(), e.getMessage());
//    }

    /*直接回复状态值，比如403*/
    @ExceptionHandler(value = ResponseException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseException() {
    }

    /*参数校验异常捕捉*/
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object handlerViolationException(HttpServletRequest request, HttpServletResponse response,
                                            ConstraintViolationException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", "出错啦");
        System.out.println(request.getRequestURL().toString());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println(request.getServletPath());
        return modelAndView;
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if ("application/json".equals(request.getContentType())) {
            ApiResult apiResult;
            if (e instanceof GlobalException) {
                apiResult = ApiResult.error((GlobalException) e);
            } else {
                apiResult = ApiResult.error(ResultEnum.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(apiResult));
                writer.flush();
                writer.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            if (e instanceof GlobalException) {
                modelAndView.addObject("message", e.getMessage());
            } else {
                modelAndView.addObject("message", ResultEnum.SYSTEM_ERROR.getMsg());
            }
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

}
