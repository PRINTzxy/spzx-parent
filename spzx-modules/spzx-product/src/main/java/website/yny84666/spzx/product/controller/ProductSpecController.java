package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.product.domain.ProductSpec;
import website.yny84666.spzx.product.mapper.ProductSpecMapper;
import website.yny84666.spzx.product.service.ProductSpecService;

import java.util.List;

@RestController
@RequestMapping("/productSpec")
@Tag(name = "商品规格属性值管理模块")
public class ProductSpecController extends BaseController {
    @Resource
    private ProductSpecService productSpecService;
    @Resource
    private ProductSpecMapper productSpecMapper;

    @Operation(summary = "新增商品规格")
    @PostMapping
    public AjaxResult save(@RequestBody ProductSpec productSpec) {
        productSpecService.checkProductSpecUnique(productSpec);
        return toAjax(productSpecMapper.insert(productSpec));
    }

    @Operation(summary = "修改商品规格")
    @PutMapping()
    public AjaxResult updateProductSpec(@Validated @RequestBody ProductSpec productSpec) {
        productSpecService.checkProductSpecUnique(productSpec);
        return toAjax(productSpecMapper.updateById(productSpec));
    }


    @Operation(summary = "获取商品规格详细信息")
    @GetMapping("/{id}")
    public AjaxResult getProductSpec(@PathVariable("id") Long id) {
        return success(productSpecMapper.selectById(id));
    }
    @Operation(summary = "根据分类id获取商品规格列表")
    @GetMapping("/productSpecList/{categoryId}")
    public AjaxResult getproductSpecListById(@PathVariable("categoryId") Long categoryId) {
        return success(productSpecMapper.selectList(new LambdaQueryWrapper<ProductSpec>().eq(ProductSpec::getCategoryId,categoryId)));
    }

    @Operation(summary = "查询商品规格列表")
    @GetMapping("/list")
    public AjaxResult getProductSpecList(ProductSpec productSpec) {
        return success(productSpecMapper.selectList(new LambdaQueryWrapper<ProductSpec>()));
    }


    @Operation(summary = "删除商品规格")
    @DeleteMapping("/{ids}")
    public AjaxResult deleteProductSpec(@PathVariable("ids") List<Long> ids) {
        return toAjax(productSpecMapper.deleteBatchIds(ids));
    }
}
