package com.sharding.jdbc.controller;

import com.sharding.jdbc.aspect.PrintLog;
import com.sharding.jdbc.form.UserForm;
import com.sharding.jdbc.po.User;
import com.sharding.jdbc.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@Slf4j
@Api(tags = "用户信息")
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "获取用户信息列表", response = User.class)
    @PrintLog
    @GetMapping(value = "")
    public ResponseEntity getUserList(
            @ApiParam(value = "页码") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam(value = "每页大小") @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<User> pageInfo = userService.getUserList(pageNum, pageSize);
        return success(pageInfo);
    }

    @ApiOperation(value = "创建/修改用户信息")
    @PrintLog
    @PostMapping(value = "")
    public ResponseEntity createOrUpdateUser(
            @Valid @ModelAttribute("userForm") UserForm userForm,
            @ApiIgnore BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            return failure(fieldError.getDefaultMessage());
        }
        if (StringUtils.isBlank(userForm.getUserId())) {
            User user = userForm.toUser();
            userService.createUser(user);
        } else {
            User user = userForm.toUser();
            userService.updateUser(user);
        }
        return success();
    }

    @ApiOperation(value = "删除用户信息")
    @PrintLog
    @PutMapping(value = "{userId}")
    public ResponseEntity delete(
            @ApiParam(value = "用户信息的id") @PathVariable Long userId) {
        userService.deleteUser(userId);
        return success();
    }
}
