package website.yny84666.spzx.product.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import website.yny84666.spzx.product.api.domain.vo.CategoryVo;
import website.yny84666.spzx.product.domain.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Dell
* @description 针对表【category(商品分类)】的数据库操作Service
* @createDate 2024-09-24 14:19:25
*/
public interface CategoryService extends IService<Category> {

    List<Category> treeSelect(Long id);

    List<Long> getAllCategoryIdList(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);

    List<CategoryVo> getOneCategory();

    List<CategoryVo> tree();
}
