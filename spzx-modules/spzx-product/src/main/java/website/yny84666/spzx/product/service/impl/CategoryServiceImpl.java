package website.yny84666.spzx.product.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import website.yny84666.spzx.common.core.utils.bean.BeanUtils;
import website.yny84666.spzx.product.api.domain.vo.CategoryVo;
import website.yny84666.spzx.product.domain.Category;
import website.yny84666.spzx.product.domain.vo.CategoryExcelVo;
import website.yny84666.spzx.product.helper.CategoryHelper;
import website.yny84666.spzx.product.service.CategoryService;
import website.yny84666.spzx.product.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 设置响应结果类型
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

            // 查询数据库中的数据
            List<Category> categoryList = categoryMapper.selectList(null);
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());

            // 将从数据库中查询到的Category对象转换成CategoryExcelVo对象
            for(Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
                categoryExcelVoList.add(categoryExcelVo);
            }

            // 写出数据到浏览器端
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类数据").doWrite(categoryExcelVoList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importData(MultipartFile file) {
        // 使用EasyExcel解析数据
        try {
            List<CategoryExcelVo> categoryExcelVoList = EasyExcel.read(file.getInputStream()).head(CategoryExcelVo.class).sheet().doReadSync();
            if(!CollectionUtils.isEmpty(categoryExcelVoList)) {
                List<Category> categoryList = new ArrayList<>(categoryExcelVoList.size());
                for(CategoryExcelVo categoryExcelVo : categoryExcelVoList) {
                    Category category = new Category();
                    BeanUtils.copyProperties(categoryExcelVo, category, Category.class);
                    categoryList.add(category);
                }
                this.saveBatch(categoryList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategoryVo> getOneCategory() {
        List<Category> allCategoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getParentId, 0));
        return allCategoryList.stream().map(item->{
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(item,categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CategoryVo> tree() {
        List<Category> allCategoryList = categoryMapper.selectList(null);
        List<CategoryVo> categoryVoList = allCategoryList.stream().map(item -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(item, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
        return CategoryHelper.buildTree(categoryVoList);
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




