package website.yny84666.spzx.product.helper;

import website.yny84666.spzx.product.api.domain.vo.CategoryVo;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper {
    public static List<CategoryVo> buildTree(List<CategoryVo> categoryVoList){
        List<CategoryVo> trees = new ArrayList<>();
        for (CategoryVo categoryVo : categoryVoList){
            if(categoryVo.getParentId().longValue() == 0){
                trees.add(findChildren(categoryVo,categoryVoList));
            }
        }
        return trees;
    }

    public static CategoryVo findChildren(CategoryVo categoryVo,List<CategoryVo> treeNodes){
        categoryVo.setChildren(new ArrayList<CategoryVo>());

        for (CategoryVo it : treeNodes){
            if(categoryVo.getId().longValue() == it.getParentId().longValue()){
                if (categoryVo.getChildren() == null){
                    categoryVo.setChildren(new ArrayList<>());
                }
                categoryVo.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return categoryVo;
    }
}
