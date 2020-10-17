package com.sharding.jdbc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;

/**
 * Base Controller 所有控制器父类，一些共有方法及spring 绑定。
 *
 * @author Jacky
 */
@Slf4j
public class BaseController {
    protected ResponseEntity success() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity success(Object data) {
        return ResponseEntity.ok(data);
    }

    protected ResponseEntity failure(String message) {
        return ResponseEntity.badRequest().body(message);
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}
