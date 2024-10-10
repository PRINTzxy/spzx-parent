package website.yny84666.spzx.channel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.channel.service.ItemService;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;

@Tag(name = "商品详情接口")
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {
    @Resource
    private ItemService itemService;

    @Operation(summary = "商品详情")
    @GetMapping("/{skuId}")
    public AjaxResult item(@PathVariable Long skuId) {

        return success(itemService.item(skuId));
    }
}
