package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.bouncycastle.LICENSE;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.product.domain.ProductUnit;
import website.yny84666.spzx.product.mapper.ProductUnitMapper;
import website.yny84666.spzx.product.service.ProductUnitService;

import java.util.List;

@RestController
@RequestMapping("/productUnit")
@Tag(name = "商品单位管理模块")
public class ProductUnitController extends BaseController {
    @Resource
    private ProductUnitService productUnitService;
    @Resource
    private ProductUnitMapper productUnitMapper;

    /**
     * @Parameter用于API资源请求的参数
     * @Schema则用于模型的属性
     * @param pageNum
     * @param pageSize
     * @param productUnit
     * @return
     */

    @Operation(summary = "获取分页列表")
    @GetMapping("/list")
    public TableDataInfo getPage(@Parameter(name = "pageNum", description = "当前页码", required = true)
                                     @RequestParam(value = "pageNum", defaultValue = "0", required = true) Integer pageNum,

                                 @Parameter(name = "pageSize", description = "每页记录数", required = true)
                                     @RequestParam(value = "pageSize", defaultValue = "10", required = true) Integer pageSize,

                                 @Parameter(name = "driverInfoQuery", description = "查询对象", required = false)
                                     ProductUnit productUnit){
        Page<ProductUnit> pageParam = new Page<>(pageNum, pageSize);
        IPage<ProductUnit> iPage = productUnitService.selectProductUnitPage(pageParam,productUnit);
        return getDataTable(iPage);
    }

    @Operation(summary = "获取商品单位详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(productUnitMapper.selectById(id));
    }

    @Operation(summary = "新增商品单位")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated ProductUnit productUnit) {
        productUnit.setCreateBy(SecurityUtils.getUsername());
        return toAjax(productUnitMapper.insert(productUnit));
    }

    @Operation(summary = "修改商品单位")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated ProductUnit productUnit) {
        return toAjax(productUnitMapper.updateById(productUnit));
    }

    @Operation(summary = "删除商品单位")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(productUnitMapper.deleteBatchIds(ids));
    }

    @Operation(summary = "获取全部单位")
    @GetMapping("getUnitAll")
    public AjaxResult selectProductUnitAll() {
        return success(productUnitService.list());
    }

}
