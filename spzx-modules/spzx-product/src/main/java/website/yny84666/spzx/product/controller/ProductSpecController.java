package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.product.domain.ProductSpec;
import website.yny84666.spzx.product.mapper.ProductSpecMapper;
import website.yny84666.spzx.product.service.ProductSpecService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productSpec")
@Tag(name = "商品规格属性值管理模块")
public class ProductSpecController extends BaseController {
    @Resource
    private ProductSpecService productSpecService;
    @Resource
    private ProductSpecMapper productSpecMapper;

    @Operation(summary = "查询商品规格列表")
    @GetMapping("/list")
    public TableDataInfo list(ProductSpec productSpec) {
        startPage();
        List<ProductSpec> list = productSpecService.selectProductSpecList(productSpec);
        return getDataTable(list);
    }

    @Operation(summary = "获取商品规格详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(productSpecService.selectProductSpecById(id));
    }

    @Operation(summary = "新增商品规格")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated ProductSpec productSpec) {
        productSpec.setCreateBy(SecurityUtils.getUsername());
        return toAjax(productSpecService.save(productSpec));
    }

    @Operation(summary = "修改商品规格")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated ProductSpec productSpec) {
        productSpec.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(productSpecMapper.updateById(productSpec));
    }

    @Operation(summary = "删除商品规格")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(productSpecMapper.deleteBatchIds(ids));
    }

    @Operation(summary = "根据分类id获取商品规格列表")
    @GetMapping("/productSpecList/{categoryId}")
    public AjaxResult selectProductSpecListByCategoryId(@PathVariable Long categoryId) {
        return success(productSpecService.selectProductSpecListByCategoryId(categoryId));
    }



}
