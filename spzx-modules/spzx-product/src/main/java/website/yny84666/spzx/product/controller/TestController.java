package website.yny84666.spzx.product.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.product.service.TestService;

@Tag(name = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {
    @Resource
    private TestService testService;

    @GetMapping("/testLock")
    public AjaxResult testLock(){
        testService.testLock();
        return AjaxResult.success();
    }
}
