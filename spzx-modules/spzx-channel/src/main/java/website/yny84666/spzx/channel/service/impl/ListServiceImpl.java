package website.yny84666.spzx.channel.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.channel.service.ListService;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.common.core.web.page.TableDataInfo;
import website.yny84666.spzx.product.api.RemoteProductService;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;

@Service
@Slf4j
public class ListServiceImpl implements ListService {

    @Resource
    private RemoteProductService remoteProductService;

    @Override
    public TableDataInfo selectProductSkuList(Integer pageNum, Integer pageSize, SkuQuery skuQuery) {
        R<TableDataInfo> tableDataInfoResult = remoteProductService.skuList(pageNum, pageSize, skuQuery, SecurityConstants.INNER);
        if (R.FAIL == tableDataInfoResult.getCode()) {
            throw new ServiceException(tableDataInfoResult.getMsg());
        }
        return tableDataInfoResult.getData();
    }
}
