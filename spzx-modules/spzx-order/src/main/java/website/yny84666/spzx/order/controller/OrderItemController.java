package website.yny84666.spzx.order.controller;

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
import website.yny84666.spzx.order.domain.OrderItem;
import website.yny84666.spzx.order.service.OrderItemService;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;

/**
 * 订单项信息Controller
 *
 * @author atguigu
 * @date 2024-10-07
 */
@Tag(name = "订单项信息接口管理")
@RestController
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController
{
    @Resource
    private OrderItemService orderItemService;

    /**
     * 查询订单项信息列表
     */
    @Operation(summary = "查询订单项信息列表")
    @RequiresPermissions("user:orderItem:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderItem orderItem)
    {
        startPage();
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        return getDataTable(list);
    }

    /**
     * 导出订单项信息列表
     */
    @Operation(summary = "导出订单项信息列表")
    @RequiresPermissions("user:orderItem:export")
    @Log(title = "订单项信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderItem orderItem)
    {
        List<OrderItem> list = orderItemService.selectOrderItemList(orderItem);
        ExcelUtil<OrderItem> util = new ExcelUtil<OrderItem>(OrderItem.class);
        util.exportExcel(response, list, "订单项信息数据");
    }

    /**
     * 获取订单项信息详细信息
     */
    @Operation(summary = "获取订单项信息详细信息")
    @RequiresPermissions("user:orderItem:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderItemService.getById(id));
    }

    /**
     * 新增订单项信息
     */
    @Operation(summary = "新增订单项信息")
    @RequiresPermissions("user:orderItem:add")
    @Log(title = "订单项信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.save(orderItem));
    }

    /**
     * 修改订单项信息
     */
    @Operation(summary = "修改订单项信息")
    @RequiresPermissions("user:orderItem:edit")
    @Log(title = "订单项信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderItem orderItem)
    {
        return toAjax(orderItemService.updateById(orderItem));
    }

    /**
     * 删除订单项信息
     */
    @Operation(summary = "删除订单项信息")
    @RequiresPermissions("user:orderItem:remove")
    @Log(title = "订单项信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderItemService.removeBatchByIds(Arrays.asList(ids)));
    }
}
