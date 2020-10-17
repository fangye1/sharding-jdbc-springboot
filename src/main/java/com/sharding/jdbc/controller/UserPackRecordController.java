package com.sharding.jdbc.controller;

import com.github.pagehelper.PageInfo;
import com.sharding.jdbc.aspect.PrintLog;
import com.sharding.jdbc.form.UserPackRecordForm;
import com.sharding.jdbc.po.UserPackRecord;
import com.sharding.jdbc.service.UserPackRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@Slf4j
@Api(tags = "用户阅读课程记录")
@RestController
@RequestMapping("user/pack/record")
public class UserPackRecordController extends BaseController {
    @Resource
    private UserPackRecordService userPackRecordService;

    @ApiOperation(value = "获取用户阅读课程记录列表", response = UserPackRecord.class)
    @PrintLog
    @GetMapping(value = "")
    public ResponseEntity getUserPackRecordList(
            @ApiParam(value = "开始阅读时间，格式：2020-01-01 00:00:00") @RequestParam  String startTime,
            @ApiParam(value = "结束阅读时间，格式：2020-01-02 00:00:00") @RequestParam String endTime,
            @ApiParam(value = "页码") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam(value = "每页大小") @RequestParam(defaultValue = "20") int pageSize) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);
        PageInfo<UserPackRecord> pageInfo = userPackRecordService.getUserPackRecordList(startDateTime, endDateTime, pageNum, pageSize);
        return success(pageInfo);
    }

    @ApiOperation(value = "创建用户阅读课程记录")
    @PrintLog
    @PostMapping(value = "")
    public ResponseEntity create(
            @Valid @ModelAttribute("userPackRecordForm") UserPackRecordForm userPackRecordForm,
            @ApiIgnore BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            return failure(fieldError.getDefaultMessage());
        }
        UserPackRecord userPackRecord = userPackRecordForm.toUserPackRecord();
        userPackRecordService.createUserPackRecord(userPackRecord);
        return success();
    }
}
