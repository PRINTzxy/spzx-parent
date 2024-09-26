package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.common.core.web.page.TableSupport;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.product.domain.Product;
import website.yny84666.spzx.product.domain.ProductSku;
import website.yny84666.spzx.product.domain.dto.ProductDetailsDTO;
import website.yny84666.spzx.product.domain.vo.ProductDetailVO;
import website.yny84666.spzx.product.mapper.ProductMapper;
import website.yny84666.spzx.product.service.ProductService;

import java.util.List;


@RestController
@RequestMapping("/product")
@Tag(name = "商品管理模块")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;
    @Resource
    private ProductMapper productMapper;

//    @Operation(summary = "新增商品数据")
//    @PostMapping
//    public AjaxResult save(@RequestBody ProductDetailsDTO productDetailsDTO){
//        return toAjax(productService.saveProductSaveDTO(productSaveDTO));
//    }
    @Operation(summary = "根据id查询商品详情")
    @GetMapping("/selectProductDetailById/{id}")
    public AjaxResult selectProductDetailById(@PathVariable Long id){
        return success(productService.selectProductDetailById(id));
    }


    @Operation(summary = "修改商品数据")
    @PutMapping
    public AjaxResult edit(@RequestBody ProductDetailsDTO productDetailsDTO) {
        return toAjax(productService.updateProductDetailsDTO(productDetailsDTO));
    }

    @Operation(summary = "更新上下架状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        productService.updateStatus(id,status);
        return success();
    }


    @Operation(summary = "商品审核")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public AjaxResult updateAuditStatus(@PathVariable("id") Long id, @PathVariable("auditStatus") Integer auditStatus) {
        productService.updateAuditStatus(id,auditStatus);
        return success();
    }

    @Operation(summary = "查询商品列表")
    @GetMapping("/list")
    public TableDataInfo selectProductList(ProductDetailsDTO productDetailsDTO){
        startPage();
        List<ProductDetailVO> list = productService.selectProductList(productDetailsDTO);
        return getDataTable(list);

    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{ids}")
    public AjaxResult deleteProduct(@PathVariable("ids") List<Long> ids) {
        productMapper.deleteBatchIds(ids);
        return success();
    }


}
