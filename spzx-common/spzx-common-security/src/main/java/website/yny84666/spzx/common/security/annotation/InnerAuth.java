package website.yny84666.spzx.common.security.annotation;

import java.lang.annotation.*;

/**
 * 内部认证注解
 *
 * @author spzx
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth
{
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}
