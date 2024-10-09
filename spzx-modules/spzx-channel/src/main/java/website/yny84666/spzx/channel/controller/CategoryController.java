package website.yny84666.spzx.channel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.channel.service.CategoryService;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;

import static website.yny84666.spzx.common.core.web.domain.AjaxResult.success;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Operation(summary = "子列表")
    @GetMapping(value = "/tree")
    public AjaxResult tree() {
        return success(categoryService.tree());
    }
}
