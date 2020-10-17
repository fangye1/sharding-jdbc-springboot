package com.sharding.jdbc.controller;

import com.github.pagehelper.PageInfo;
import com.sharding.jdbc.aspect.PrintLog;
import com.sharding.jdbc.form.PackForm;
import com.sharding.jdbc.po.Pack;
import com.sharding.jdbc.service.PackService;
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
@Api(tags = "课程信息")
@RestController
@RequestMapping("pack")
public class PackController extends BaseController {
    @Resource
    private PackService packService;

    @ApiOperation(value = "获取课程信息列表", response = Pack.class)
    @PrintLog
    @GetMapping(value = "")
    public ResponseEntity getPackList(
            @ApiParam(value = "页码") @RequestParam(defaultValue = "1") int pageNum,
            @ApiParam(value = "每页大小") @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<Pack> pageInfo = packService.getPackList(pageNum, pageSize);
        return success(pageInfo);
    }

    @ApiOperation(value = "创建/修改课程信息")
    @PrintLog
    @PostMapping(value = "")
    public ResponseEntity createOrUpdatePack(
            @Valid @ModelAttribute("packForm") PackForm packForm,
            @ApiIgnore BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            return failure(fieldError.getDefaultMessage());
        }
        if (StringUtils.isBlank(packForm.getPackId())) {
            Pack pack = packForm.toPack();
            packService.createPack(pack);
        } else {
            Pack pack = packForm.toPack();
            packService.updatePack(pack);
        }
        return success();
    }

    @ApiOperation(value = "删除课程信息")
    @PrintLog
    @PutMapping(value = "{packId}")
    public ResponseEntity delete(
            @ApiParam(value = "课程信息的id") @PathVariable Long packId) {
        packService.deletePack(packId);
        return success();
    }
}
