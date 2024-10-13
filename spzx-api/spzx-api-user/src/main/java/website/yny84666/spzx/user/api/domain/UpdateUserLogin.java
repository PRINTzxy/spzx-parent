package website.yny84666.spzx.user.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserLogin {

    @Schema(name = "用户id")
    private Long userId;

    @Schema(name = "最后一次登录ip")
    private String lastLoginIp;

    @Schema(name = "最后一次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;
}
