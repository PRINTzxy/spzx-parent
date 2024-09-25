package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.domain.CategoryBrand;
import website.yny84666.spzx.product.domain.ProductUnit;
import website.yny84666.spzx.product.mapper.CategoryBrandMapper;
import website.yny84666.spzx.product.service.CategoryBrandService;

import java.util.List;

@RestController
@RequestMapping("/categoryBrand")
@Tag(name = "商品分类品牌管理模块")
public class CategoryBrandController extends BaseController {
    @Resource
    private CategoryBrandService categoryBrandService;
    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Operation(summary = "新增分类品牌")
    @PostMapping
    public AjaxResult addCategoryBrand(@Validated @RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.checkUniqueCategoryBrand(categoryBrand);
        return toAjax(categoryBrandMapper.insert(categoryBrand));
    }

    @Operation(summary = "修改分类品牌")
    @PutMapping
    public AjaxResult updateCategoryBrand(@Validated @RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.checkUniqueCategoryBrand(categoryBrand);
        return toAjax(categoryBrandMapper.updateById(categoryBrand));
    }

    @Operation(summary = "获取分类品牌详细信息")
    @GetMapping("/{id}")
    public AjaxResult getCategoryBrand(@PathVariable("id") Long id) {
        return success(categoryBrandMapper.selectById(id));
    }

    @Operation(summary = "查询分类品牌列表")
    @GetMapping("/list")
    public TableDataInfo list(CategoryBrand categoryBrand){
        startPage();
        List<CategoryBrand> list = categoryBrandService.selectCategoyBrandVOList(categoryBrand);
        return getDataTable(list);
    }
    @Operation(summary = "根据分类id获取品牌品牌列表")
    @GetMapping("/brandList/{categoryId}")
    public AjaxResult getCategoryBrandListById(@PathVariable("categoryId") Long categoryId){
        return success(categoryBrandMapper.selectById(categoryId));
    }

    @Operation(summary = "删除分类品牌")
    @DeleteMapping("/{ids}")
    public AjaxResult deleteCategoryBrand(@PathVariable("ids") List<Long> ids){
        return toAjax(categoryBrandMapper.deleteBatchIds(ids));
    }


}
