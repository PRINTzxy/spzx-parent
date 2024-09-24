package website.yny84666.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.domain.Brand;
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
}
