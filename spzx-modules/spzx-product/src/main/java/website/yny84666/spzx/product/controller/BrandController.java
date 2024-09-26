package website.yny84666.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.domain.Brand;
import website.yny84666.spzx.product.mapper.BrandMapper;
import website.yny84666.spzx.product.service.BrandService;

import java.util.List;

import static website.yny84666.spzx.common.core.utils.PageUtils.startPage;

@RestController
@RequestMapping("/brand")
@Tag(name = "品牌管理模块")
public class BrandController extends BaseController {

    @Resource
    private BrandService brandService;

    @Operation(summary = "条件分页查询品牌列表")
    @GetMapping("/list")
    public TableDataInfo list(Brand brand) {
            startPage();
            List<Brand> brandList = brandService.selectBrandList(brand);
            return getDataTable(brandList);
    }

    @Operation(summary = "根据id查询品牌详情")
    @GetMapping("{id}")
    public AjaxResult getBrandById(@PathVariable("id") Long id) {
        Brand brand = brandService.getBrandById(id);
        return success(brand);
    }

    @Operation(summary = "根据id更新品牌")
    @PutMapping
    public AjaxResult updateBrandById(@RequestBody Brand brand) {
        return toAjax(brandService.updateBrandById(brand));
    }

    @Operation(summary = "新增品牌")
    @PostMapping
    public AjaxResult saveBrand(@RequestBody Brand brand) {
        return toAjax(brandService.saveBrand(brand));
    }

    @Operation(summary = "批量删除品牌")
    @DeleteMapping("{ids}")
    public AjaxResult deleteBrand(@PathVariable("ids") List<Long> ids) {
        return toAjax(brandService.deleteBrand(ids));
    }



}

