package website.yny84666.spzx.product.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.common.security.annotation.InnerAuth;
import website.yny84666.spzx.product.api.domain.vo.SkuPrice;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;
import website.yny84666.spzx.product.api.domain.vo.SkuStockVo;
import website.yny84666.spzx.product.api.domain.vo.Product;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import website.yny84666.spzx.product.api.domain.vo.ProductDetails;
import website.yny84666.spzx.product.mapper.ProductMapper;
import website.yny84666.spzx.product.service.ProductService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
@Tag(name = "商品管理模块")
public class ProductController extends BaseController {

    @Resource
    private ProductService productService;
    @Resource
    private ProductMapper productMapper;

    @Operation(summary = "查询商品列表")
    @GetMapping("/list")
    public TableDataInfo list(Product product) {
        startPage();
        List<Product> list = productService.selectProductList(product);
        return getDataTable(list);
    }

    @Operation(summary = "新增商品")
    @PostMapping
    public AjaxResult add(@RequestBody Product product) {
        return toAjax(productService.insertProduct(product));
    }

    @Operation(summary = "获取商品详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(productService.selectProductById(id));
    }

    @Operation(summary = "修改商品")
    @PutMapping
    public AjaxResult edit(@RequestBody Product product) {
        return toAjax(productService.updateProduct(product));
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(productService.deleteProductByIds(ids));
    }

    @Operation(summary = "商品审核")
    @GetMapping("updateAuditStatus/{id}/{auditStatus}")
    public AjaxResult updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return success();
    }

    @Operation(summary = "更新上下架状态")
    @GetMapping("updateStatus/{id}/{status}")
    public AjaxResult updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return success();
    }

    @InnerAuth
    @Operation(summary = "获取销量好的sku")
    @GetMapping("getTopSale")
    public R<List<ProductSku>> getTopSale() {
        return R.ok(productService.getTopSale());
    }

    @InnerAuth
    @GetMapping("/skuList/{pageNum}/{pageSize}")
    public R<TableDataInfo> skuList(@Parameter(name = "pageNum", description = "当前页码", required = true)
                                        @PathVariable Integer pageNum,
                                    @Parameter(name = "pageSize", description = "每页记录数", required = true)
                                        @PathVariable Integer pageSize,
                                    @Parameter(name = "productQuery", description = "查询对象", required = false)
                                        @ModelAttribute SkuQuery skuQuery){
        PageHelper.startPage(pageNum,pageSize);
        List<ProductSku> list = productService.selectProductSkuList(skuQuery);
        return R.ok(getDataTable(list));
    }


    ////////////////////////////////////////////////////////////////////////////////

    @Operation(summary = "获取商品sku信息")
    @InnerAuth
    @GetMapping(value = "/getProductSku/{skuId}")
    public R<ProductSku> getProductSku(@PathVariable("skuId") Long skuId)
    {
        return R.ok(productService.getProductSku(skuId));
    }

    @Operation(summary = "获取商品信息")
    @InnerAuth
    @GetMapping(value = "/getProduct/{id}")
    public R<Product> getProduct(@PathVariable("id") Long id)
    {
        return R.ok(productService.getProduct(id));
    }

    @Operation(summary = "获取商品sku最新价格信息")
    @InnerAuth
    @GetMapping(value = "/getSkuPrice/{skuId}")
    public R<SkuPrice> getSkuPrice(@PathVariable("skuId") Long skuId)
    {
        return R.ok(productService.getSkuPrice(skuId));
    }

    @Operation(summary = "获取商品详细信息")
    @InnerAuth
    @GetMapping(value = "/getProductDetails/{id}")
    public R<ProductDetails> getProductDetails(@PathVariable("id") Long id)
    {
        return R.ok(productService.getProductDetails(id));
    }

    @Operation(summary = "获取商品sku规则详细信息")
    @InnerAuth
    @GetMapping(value = "/getSkuSpecValue/{id}")
    public R<Map<String, Long>> getSkuSpecValue(@PathVariable("id") Long id)
    {
        return R.ok(productService.getSkuSpecValue(id));
    }

    @Operation(summary = "获取商品sku库存信息")
    @InnerAuth
    @GetMapping(value = "/getSkuStock/{skuId}")
    public R<SkuStockVo> getSkuStock(@PathVariable("skuId") Long skuId)
    {
        return R.ok(productService.getSkuStock(skuId));
    }

}
