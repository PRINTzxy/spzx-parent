package website.yny84666.spzx.channel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.channel.service.CategoryService;
import website.yny84666.spzx.channel.service.IndexService;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;

@Slf4j
@Tag(name = "首页管理")
@RestController
@RequestMapping(value = "/index")
@SuppressWarnings({"unchecked","rawtypes"})
public class IndexController extends BaseController {

    @Resource
    private IndexService indexService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public AjaxResult getIndexData(){
        return success(indexService.getIndexData());
    }


}
