package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import website.yny84666.spzx.product.domain.Category;
import website.yny84666.spzx.product.service.CategoryService;
import website.yny84666.spzx.product.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Dell
* @description 针对表【category(商品分类)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:25
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




