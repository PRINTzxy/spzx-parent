package website.yny84666.spzx.product.domain;

import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

@Data
public class Brand extends BaseEntity {
    private String name;
    private String logo;
}
