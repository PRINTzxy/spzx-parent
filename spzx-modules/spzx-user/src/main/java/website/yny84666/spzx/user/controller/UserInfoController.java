package website.yny84666.spzx.user.controller;

import java.util.List;
import java.util.Arrays;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.common.log.annotation.Log;
import website.yny84666.spzx.common.log.enums.BusinessType;
import website.yny84666.spzx.common.security.annotation.RequiresPermissions;
import website.yny84666.spzx.user.domain.UserAddress;
import website.yny84666.spzx.user.domain.UserInfo;
import website.yny84666.spzx.user.service.UserInfoService;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.user.service.UserInfoService;

/**
 * 会员Controller
 *
 * @author atguigu
 * @date 2024-10-06
 */
@Tag(name = "会员接口管理")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController
{
    @Resource
    private UserInfoService userInfoService;

    @Operation(summary = "查询会员列表")
    @RequiresPermissions("user:userInfo:list")
    @GetMapping("/list")
    public TableDataInfo list(UserInfo userInfo)
    {
        startPage();
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        return getDataTable(list);
    }


    @Operation(summary = "导出会员列表")
    @RequiresPermissions("user:userInfo:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserInfo userInfo)
    {
        List<UserInfo> list = userInfoService.selectUserInfoList(userInfo);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        util.exportExcel(response, list, "会员数据");
    }


    @Operation(summary = "获取会员详细信息")
    @RequiresPermissions("user:userInfo:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(userInfoService.getById(id));
    }


    @Operation(summary = "新增会员")
    @RequiresPermissions("user:userInfo:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserInfo userInfo)
    {
        return toAjax(userInfoService.save(userInfo));
    }


    @Operation(summary = "修改会员")
    @RequiresPermissions("user:userInfo:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserInfo userInfo)
    {
        return toAjax(userInfoService.updateById(userInfo));
    }


    @Operation(summary = "删除会员")
    @RequiresPermissions("user:userInfo:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userInfoService.removeBatchByIds(Arrays.asList(ids)));
    }

    @Operation(summary = "获取用户地址")
    @RequiresPermissions("user:info:query")
    @GetMapping("/getUserAddress/{userId}")
    public AjaxResult getUserAddress(@PathVariable Long userId) {
        List<UserAddress> userAddressList = userInfoService.selectUserAddressList(userId);
        return success(userAddressList);
    }
}
