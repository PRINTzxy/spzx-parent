package website.yny84666.spzx.channel.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.channel.service.IndexService;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.product.api.RemoteCategoryService;
import website.yny84666.spzx.product.api.RemoteProductService;
import website.yny84666.spzx.product.api.domain.vo.CategoryVo;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {
    @Resource
    private RemoteProductService remoteProductService;
    @Resource
    private RemoteCategoryService remoteCategoryService;

    @Override
    public Map<String, Object> getIndexData() {
        //获取分类列表
        R<List<CategoryVo>> categoryListResult = remoteCategoryService.getOneCategory(SecurityConstants.INNER);
        //检查分类结果
        if(R.FAIL == categoryListResult.getCode()){
            throw new ServiceException(categoryListResult.getMsg());
        }
        //获取热销产品 SKU 列表
        R<List<ProductSku>> productSkuListResult = remoteProductService.getTopSale(SecurityConstants.INNER);
        //检查产品结果
        if(R.FAIL == productSkuListResult.getCode()){
            throw new ServiceException(productSkuListResult.getMsg());
        }
        //构建结果 Map
        Map<String, Object> map = new HashMap<>();
        map.put("categoryList", categoryListResult.getData());
        map.put("productSkuList", productSkuListResult.getData());
        return map;
    }
}
