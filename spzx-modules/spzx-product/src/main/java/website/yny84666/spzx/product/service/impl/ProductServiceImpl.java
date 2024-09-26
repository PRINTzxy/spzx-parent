package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import website.yny84666.spzx.common.core.utils.StringUtils;
import website.yny84666.spzx.common.security.utils.SecurityUtils;
import website.yny84666.spzx.product.domain.Product;
import website.yny84666.spzx.product.domain.ProductDetails;
import website.yny84666.spzx.product.domain.ProductSku;
import website.yny84666.spzx.product.domain.SkuStock;
import website.yny84666.spzx.product.domain.dto.ProductDetailsDTO;
import website.yny84666.spzx.product.domain.vo.ProductDetailVO;
import website.yny84666.spzx.product.mapper.*;
import website.yny84666.spzx.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Autowired
    private SkuStockMapper skuStockMapper;

    @Override
    public List<ProductDetailVO> selectProductList(ProductDetailsDTO productDetailsDTO) {
        return productMapper.selectProductList(productDetailsDTO);
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if (auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        }else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if (status == 1) product.setStatus(1);
        else product.setStatus(-1);
        productMapper.updateById(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateProductDetailsDTO(ProductDetailsDTO productDetailsDTO) {
        productDetailsDTO.setUpdateBy(SecurityUtils.getUsername());
        baseMapper.updateById(productDetailsDTO);

        List<String> dtoDetailsImageUrlS = productDetailsDTO.getDetailsImageUrlList();
        productDetailsMapper.deleteById(productDetailsDTO.getId());
        if(!CollectionUtils.isEmpty(dtoDetailsImageUrlS)){
            ProductDetails productDetails = new ProductDetails();
            productDetails.setId(productDetailsDTO.getId());
            productDetails.setProductId(productDetailsDTO.getId());
            productDetails.setImageUrls(StringUtils.join(dtoDetailsImageUrlS,","));
            productDetailsMapper.insert(productDetails);
        }
        List<ProductSku> productSkus = productDetailsDTO.getProductSkuList();
        for (ProductSku productSku : productSkus) {
            String skuName = productDetailsDTO.getName() + "" + productSku.getSkuSpec();
            productSku.setSkuName(skuName);
            productSkuMapper.updateById(productSku);
            Long skuId = productSku.getId();
            SkuStock skuStock = skuStockMapper.selectOne(Wrappers.lambdaQuery(SkuStock.class).eq(SkuStock::getSkuId,skuId).last("limit 1"));
            skuStock.setTotalNum(productSku.getStockNum());
            skuStock.setAvailableNum(productSku.getStockNum()-skuStock.getLockNum());
            skuStockMapper.updateById(skuStock);
        }
        return 1;
    }

    @Override
    public ProductDetailVO selectProductDetailById(Long id) {

        return null;
    }


//    @Resource
//    private ProductDetailsMapper productDetailsMapper;
//    @Resource
//    private ProductSkuMapper productSkuMapper;
//    @Resource
//    private SkuStockMapper skuStockMapper;
//    @Resource
//    private BrandMapper brandMapper;
//    @Resource
//    private CategoryMapper categoryMapper;

//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public int selectProductSaveDTO(ProductSaveDTO productSaveDTO) {
//        productSaveDTO.setStatus(0);
//        productSaveDTO.setAuditStatus(0);
//
//        productSaveDTO.setCreateBy(SecurityUtils.getUsername());
//        int i = baseMapper.insert(productSaveDTO);
//        if (i == 0){
//            throw new ServiceException("商品数据新增失败");
//        }
//        Long productId = productSaveDTO.getId();
//
//        if(!CollectionUtils.isEmpty(productSaveDTO.getDetailsImageUrlList())){
//            ProductDetails productDetails = new ProductDetails();
//            productDetails.setProductId(productId);
//            productDetails.setImageUrls(StringUtils.join(productSaveDTO.getDetailsImageUrlList(),","));
//            productDetailsMapper.insert(productDetails);
//        }
//        List<ProductSku> productSkus = productSaveDTO.getProductSkuList();
//        if(!CollectionUtils.isEmpty(productSkus)){
//            productSkus.forEach(productSku -> {
//                productSku.setProductId(productId);
//                String skuCode = DateUtil.today().replace("-","")+ IdUtil.getSnowflakeNextIdStr();
//                productSku.setSkuCode(skuCode);
//                String skuName = productSaveDTO.getName()+" "+productSku.getSkuSpec();
//                productSku.setSkuName(skuName);
//                productSku.setStatus(0);
//                Long skuId = productSku.getId();
//
//                SkuStock skuStock = new SkuStock();
//
//                skuStock.setSkuId(skuId);
//                skuStock.setLockNum(0);
//
//                skuStock.setTotalNum(productSku.getStockNum());
//                skuStock.setAvailableNum(productSku.getStockNum());
//                skuStock.setSaleNum(0);
//                skuStock.setStatus(0);
//                skuStockMapper.insert(skuStock);
//            });
//        }
//        return i;
//    }
}




