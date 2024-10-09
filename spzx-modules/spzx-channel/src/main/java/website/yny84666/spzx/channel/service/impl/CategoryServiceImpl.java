package website.yny84666.spzx.channel.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import website.yny84666.spzx.channel.service.CategoryService;
import website.yny84666.spzx.common.core.constant.SecurityConstants;
import website.yny84666.spzx.common.core.domain.R;
import website.yny84666.spzx.common.core.exception.ServiceException;
import website.yny84666.spzx.product.api.RemoteCategoryService;
import website.yny84666.spzx.product.api.domain.vo.CategoryVo;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private RemoteCategoryService remoteCategoryService;

    @Override
    public List<CategoryVo> tree() {
        R<List<CategoryVo>> categoryVoListResult = remoteCategoryService.tree(SecurityConstants.INNER);
        if (R.FAIL == categoryVoListResult.getCode()){
            throw new ServiceException(categoryVoListResult.getMsg());
        }
        return categoryVoListResult.getData();
    }
}
