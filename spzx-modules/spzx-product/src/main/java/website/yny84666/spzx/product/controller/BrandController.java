package website.yny84666.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.common.log.annotation.Log;
import website.yny84666.spzx.common.log.enums.BusinessType;
import website.yny84666.spzx.common.security.annotation.InnerAuth;
import website.yny84666.spzx.common.security.annotation.RequiresPermissions;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.product.api.domain.vo.Brand;
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
    @Resource
    private BrandMapper brandMapper;

    @RequiresPermissions("product:brand:list")
    @Operation(summary = "查询品牌列表")
    @GetMapping("/list")
    public TableDataInfo list(Brand brand)
    {
        startPage();
        List<Brand> list = brandService.selectBrandList(brand);
        return getDataTable(list);
    }

    @RequiresPermissions("product:brand:query")
    @Operation(summary = "获取品牌详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(brandMapper.selectById(id));
    }

    @Log(title = "品牌管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("product:brand:add")
    @Operation(summary = "新增品牌")
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Brand brand) {
        brand.setCreateBy(SecurityUtils.getUsername());
        return toAjax(brandMapper.insert(brand));
    }

    @Log(title = "品牌管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("product:brand:edit")
    @Operation(summary = "修改品牌")
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated Brand brand) {
        return toAjax(brandService.updateBrand(brand));
    }

    @Log(title = "品牌管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("product:brand:remove")
    @Operation(summary = "删除品牌")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<Long> ids) {
        return toAjax(brandMapper.deleteBatchIds(ids));
    }

    @RequiresPermissions("product:brand:query")
    @Operation(summary = "获取全部品牌")
    @GetMapping("getBrandAll")
    public AjaxResult getBrandAll() {
        return success(brandMapper.selectBrandList(null));
    }

    @InnerAuth
    @Operation(summary = "获取全部品牌")
    @GetMapping("getBrandAllList")
    public R<List<Brand>> getBrandAllList() {
        return R.ok(brandService.selectBrandList(null));
    }

}

