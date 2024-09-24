package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.bouncycastle.LICENSE;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.domain.ProductUnit;
import website.yny84666.spzx.product.mapper.ProductUnitMapper;
import website.yny84666.spzx.product.service.ProductUnitService;

import java.util.List;

@RestController
@RequestMapping("/unit")
@Tag(name = "商品单位管理模块")
public class ProductUnitController extends BaseController {
    @Resource
    private ProductUnitService productUnitService;
    @Resource
    private ProductUnitMapper productUnitMapper;

    @Operation(summary = "新增商品单位")
    @PostMapping("/productUnit")
    public AjaxResult saveProductUnit(@Validated @RequestBody ProductUnit productUnit) {
        productUnitService.checkUniqueUnitName(productUnit);
//        return toAjax(productUnitMapper.insert(productUnit));
        return toAjax(productUnitService.save(productUnit));
    }

    @Operation(summary = "根据id更新商品单位")
    @PutMapping("/productUnit")
    public AjaxResult updateProductUnitById(@RequestBody ProductUnit productUnit) {
        return toAjax(productUnitMapper.updateById(productUnit));
    }

    @Operation(summary = "根据id查询商品单位详情")
    @GetMapping("/productUnit/{id}")
    public AjaxResult getProductUnitById(@PathVariable("id") Long id) {
        return success(productUnitMapper.selectById(id));
    }

    @Operation(summary = "条件分页查询商品单位列表")
    @GetMapping("/productUnit/list")
    public TableDataInfo getProductUnitList(ProductUnit productUnit) {
        Page<ProductUnit> page = productUnitService.selectProductUnitPage(productUnit);
        return getDataTable(page);
    }

    @Operation(summary = "批量删除商品单位")
    @DeleteMapping("/productUnit/{ids}")
    public AjaxResult deleteProductUnitById(@PathVariable("ids") List<Long> ids) {
        return toAjax(productUnitMapper.deleteBatchIds(ids));
    }

}
