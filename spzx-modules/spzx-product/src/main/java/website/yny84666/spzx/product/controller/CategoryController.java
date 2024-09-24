package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.product.domain.Category;
import website.yny84666.spzx.product.mapper.CategoryMapper;
import website.yny84666.spzx.product.service.CategoryService;

@RestController
@RequestMapping("/category")
@Tag(name = "商品分类管理模块")
public class CategoryController extends BaseController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private CategoryMapper categoryMapper;

    @Operation(summary = "根据父id查询下一级商品分类列表")
    @GetMapping("/treeSelect/{id}")
    public AjaxResult treeSelect(@PathVariable("id") Long id) {
        return success(categoryService.list(Wrappers.lambdaQuery(Category.class).eq(Category::getParentId,id)));
    }
}
