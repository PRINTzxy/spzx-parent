package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import website.yny84666.spzx.product.domain.Category;
import website.yny84666.spzx.product.service.CategoryService;
import website.yny84666.spzx.product.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Dell
* @description 针对表【category(商品分类)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:25
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> treeSelect(Long id) {
        List<Category> categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getParentId, id));
        if (!CollectionUtils.isEmpty(categoryList)) {
            categoryList.forEach(item -> {
                Long count = categoryMapper.selectCount(new LambdaQueryWrapper<Category>().eq(Category::getParentId, item.getId()));
                if (count > 0) item.setHasChildren(true);
                else item.setHasChildren(false);
            });
        }
        return categoryList;
    }

    @Override
    public List<Long> getAllCategoryIdList(Long id) {
        List<Long> list = new ArrayList<>();
        List<Category> categoryList = this.getParentCategory(id, new ArrayList<Category>());
        int size = categoryList.size();
        for (int i = size; i > 0; i--) list.add(categoryList.get(i-1).getId());
        return list;
    }

    private List<Category> getParentCategory(Long id, List<Category> categoryList) {
        while (id>0){
            Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>()
                    .eq(Category::getId,id)
                    .select(Category::getId,Category::getParentId));
            categoryList.add(category);
            return getParentCategory(category.getParentId(),categoryList);
        }
        return categoryList;
    }
}




