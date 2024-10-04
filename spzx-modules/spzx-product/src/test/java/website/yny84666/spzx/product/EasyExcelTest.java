package website.yny84666.spzx.product;

import com.alibaba.excel.EasyExcel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import website.yny84666.spzx.common.core.utils.bean.BeanUtils;
import website.yny84666.spzx.product.domain.vo.CategoryExcelVo;
import website.yny84666.spzx.product.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;
@SpringBootTest
public class EasyExcelTest {
    @Resource
    CategoryService categoryService;
    @Test
    public void writeDataToExcel() {
        List<CategoryExcelVo> list = categoryService.list()
                .stream().map(category -> {
                    CategoryExcelVo categoryExcelVo= new CategoryExcelVo();
                    BeanUtils.copyProperties(category , categoryExcelVo);
                    return categoryExcelVo;
                }).collect(Collectors.toList());

        EasyExcel.write("C:\\Users\\Dell\\Desktop\\分类数据-导出.xlsx" , CategoryExcelVo.class).sheet("分类数据").doWrite(list);
    }

    @Test
    public void easyExcelReadTest() {
        String fileName = "C:\\Users\\Dell\\Desktop\\分类数据-导出.xlsx" ;
        ExcelListener categoryExcelDataListener = new ExcelListener();
        EasyExcel.read(fileName)
                .head(CategoryExcelVo.class)
                .sheet()
                .registerReadListener(categoryExcelDataListener)
                .doRead();

        categoryExcelDataListener.getDatas().forEach(s -> System.out.println(s) );   // 进行遍历操作
        categoryExcelDataListener.getDatas().clear();
    }
}
