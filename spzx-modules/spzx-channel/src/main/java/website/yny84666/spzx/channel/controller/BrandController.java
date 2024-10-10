package website.yny84666.spzx.channel.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.channel.service.BrandService;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;

@RestController
@RequestMapping("/brand")
public class BrandController extends BaseController {
    @Resource
    private BrandService brandService;

    @Operation(summary = "获取全部品牌")
    @GetMapping("getBrandAll")
    public AjaxResult selectBrandAll(){
        return success(brandService.getBrandAll());
    }
}
