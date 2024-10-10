package website.yny84666.spzx.channel.service.impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.channel.service.BrandService;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.product.api.RemoteBrandService;
import website.yny84666.spzx.product.api.domain.vo.Brand;

import java.util.List;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {

    @Resource
    private RemoteBrandService remoteBrandService;

    @Override
    public List<Brand> getBrandAll() {
        R<List<Brand>> brandListResult = remoteBrandService.getBrandAllList(SecurityConstants.INNER);
        if(R.FAIL == brandListResult.getCode()){
            throw new ServiceException(brandListResult.getMsg());
        }
        return brandListResult.getData();
    }
}
