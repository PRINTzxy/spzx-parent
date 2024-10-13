package website.yny84666.spzx.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.yny84666.spzx.common.core.web.controller.BaseController;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;
import website.yny84666.spzx.user.service.SmsService;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/sms")
@Tag(name = "短信")
public class SmsController extends BaseController {
    @Resource
    private SmsService smsService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "获取短信验证码")
    @GetMapping(value = "sendCode/{phone}")
    public AjaxResult sendCode(@Parameter(name = "phone", description = "手机号", required = true) @PathVariable String phone){
        String code =  new DecimalFormat("0000").format(new Random().nextInt(10000));

        stringRedisTemplate.opsForValue().set("phone:code:"+phone,code,1, TimeUnit.MINUTES);

        log.info(phone + ": " + code);

        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        smsService.send(phone,"",param);

        return success();
    }
}
