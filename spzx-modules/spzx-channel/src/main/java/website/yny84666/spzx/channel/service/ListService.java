package website.yny84666.spzx.channel.service;

import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;

public interface ListService {
    TableDataInfo selectProductSkuList(Integer pageNum, Integer pageSize, SkuQuery skuQuery);
}
