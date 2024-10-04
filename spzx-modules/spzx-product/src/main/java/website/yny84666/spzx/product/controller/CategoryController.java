package website.yny84666.spzx.product.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Operation(summary = "获取分类下拉树列表")
    @GetMapping(value = "/treeSelect/{id}")
    public AjaxResult treeSelect(@PathVariable Long id) {
        return success(categoryService.treeSelect(id));
    }
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        categoryService.exportData(response);
    }
    @PostMapping("/import")
    public AjaxResult importData(MultipartFile file) throws Exception {
        try {
            categoryService.importData(file);
            return AjaxResult.success("导入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导入失败");
    }
}
