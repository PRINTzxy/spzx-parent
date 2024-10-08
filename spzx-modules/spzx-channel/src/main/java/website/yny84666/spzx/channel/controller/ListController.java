package website.yny84666.spzx.channel.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.channel.service.ListService;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;

@Tag(name = "商品详情接口")
@RestController
@RequestMapping("/list")
public class ListController extends BaseController {

    @Resource
    private ListService listService;

    @GetMapping("/skuList/{pageNum}/{pageSize}")
    public TableDataInfo skuList(@Parameter(name = "pageNum", description = "当前页码", required = true)
                                 @PathVariable Integer pageNum,

                                 @Parameter(name = "pageSize", description = "每页记录数", required = true)
                                 @PathVariable Integer pageSize,

                                 @Parameter(name = "productQuery", description = "查询对象", required = false)
                                 SkuQuery skuQuery) {
        return listService.selectProductSkuList(pageNum, pageSize, skuQuery);
    }

}
