package website.yny84666.spzx.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.product.domain.dto.ProductSaveDTO;
import website.yny84666.spzx.product.mapper.ProductMapper;
import website.yny84666.spzx.product.service.ProductService;

@RestController
@RequestMapping("product")
@Tag(name = "商品管理模块")
public class ProductController extends BrandController{

//    @Resource
//    private ProductService productService;
//    @Resource
//    private ProductMapper productMapper;

//    @Operation(summary = "新增商品and修改商品")
//    @PostMapping
//    public AjaxResult save(@RequestBody ProductSaveDTO productSaveDTO) {
//        return toAjax(productService.selectProductSaveDTO(productSaveDTO));
//    }

//    @Operation(summary = "批量获取商品sku最新价格信息")
//    @Operation(summary = "获取商品sku信息列表")
//    @Operation(summary = "检查与锁定库存")
//    @Operation(summary = "获取商品详细信息")
//    @Operation(summary = "更新上下架状态")
//    @Operation(summary = "商品审核")
//    @Operation(summary = "skuList")
//    @Operation(summary = "查询商品列表")
//    @Operation(summary = "获取销量好的sku")
//    @Operation(summary = "获取商品sku库存信息")
//    @Operation(summary = "获取商品sku规则详细信息")
//    @Operation(summary = "获取商品sku最新价格信息")
//    @Operation(summary = "获取商品sku信息")
//    @Operation(summary = "获取商品详细信息")
//    @Operation(summary = "获取商品信息")
//    @Operation(summary = "删除商品")



}
