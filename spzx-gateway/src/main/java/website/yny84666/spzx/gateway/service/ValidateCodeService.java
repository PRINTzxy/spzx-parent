package website.yny84666.spzx.gateway.service;

import java.io.IOException;
import website.yny84666.spzx.common.core.exception.CaptchaException;
import website.yny84666.spzx.common.core.web.domain.AjaxResult;

/**
 * 验证码处理
 *
 * @author spzx
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}
