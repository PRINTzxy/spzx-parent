package website.yny84666.spzx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import feign.Client;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.transaction.annotation.Transactional;
import website.yny84666.spzx.common.core.utils.bean.BeanUtils;
import website.yny84666.spzx.common.core.utils.uuid.UUID;
import website.yny84666.spzx.common.redis.cache.GuiguCache;
import website.yny84666.spzx.product.api.domain.vo.SkuPrice;
import website.yny84666.spzx.product.api.domain.vo.SkuQuery;
import website.yny84666.spzx.product.api.domain.vo.SkuStockVo;
import website.yny84666.spzx.product.api.domain.vo.Product;
import website.yny84666.spzx.product.api.domain.vo.ProductDetails;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import website.yny84666.spzx.product.domain.SkuStock;
import website.yny84666.spzx.product.mapper.*;
import website.yny84666.spzx.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
* @author Dell
* @description 针对表【product(商品)】的数据库操作Service实现
* @createDate 2024-09-24 14:19:26
*/
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductSkuMapper productSkuMapper;
    @Resource
    private ProductDetailsMapper productDetailsMapper;
    @Resource
    private SkuStockMapper skuStockMapper;
    @Autowired
    private RedissonClient redissonClient;
    /*@Qualifier("feignRetryClient")
    @Resource
    private Client feignRetryClient;*/


    @Override
    public List<Product> selectProductList(Product product) {
        return productMapper.selectProductList(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertProduct(Product product) {
        productMapper.insert(product);
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i = 0,size = productSkuList.size(); i < size; i++) {
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId()+"_"+i);
            productSku.setProductId(product.getId());
            String skuName = product.getName()+" "+productSku.getSkuSpec();
            productSku.setSkuName(skuName);
            productSkuMapper.insert(productSku);

            //添加商品库存
            SkuStock skuStock = new SkuStock();
            skuStock.setSkuId(productSku.getId());
            skuStock.setTotalNum(productSku.getStockNum());
            skuStock.setLockNum(0);

            skuStock.setAvailableNum(productSku.getStockNum());
            skuStock.setSaleNum(0);
            skuStockMapper.insert(skuStock);
        }

        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(String.join(",",product.getDetailsImageUrlList()));
        productDetailsMapper.insert(productDetails);

        return product.getId().intValue();
    }

    @Override
    public Product selectProductById(Long id) {
        //商品信息
        Product product = productMapper.selectById(id);

        //商品sku列表
        List<ProductSku> productSkuList = productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, id));
        //查询库存
        List<Long> skuIdList = productSkuList.stream().map(ProductSku::getId).collect(Collectors.toList());
        List<SkuStock> skuStockList = skuStockMapper.selectList(new LambdaQueryWrapper<SkuStock>().in(SkuStock::getSkuId, skuIdList).select(SkuStock::getSkuId, SkuStock::getTotalNum));
        Map<Long, Integer> skuIdToStockNumMap = skuStockList.stream().collect(Collectors.toMap(SkuStock::getSkuId, SkuStock::getTotalNum));
        productSkuList.forEach(item->{
            item.setStockNum(skuIdToStockNumMap.get(item.getId()));
        });
        product.setProductSkuList(productSkuList);
        //商品详情
        ProductDetails productDetails = productDetailsMapper.selectOne(new LambdaQueryWrapper<ProductDetails>().eq(ProductDetails::getProductId, id));

        product.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        return product;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateProduct(Product product) {
        productMapper.updateById(product);

        List<ProductSku> productSkuList = product.getProductSkuList();

        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);

            SkuStock skuStock = skuStockMapper.selectOne(new LambdaQueryWrapper<SkuStock>().eq(SkuStock::getSkuId, productSku.getId()));
            skuStock.setTotalNum(productSku.getStockNum());
            int availableNum = skuStock.getTotalNum() - skuStock.getLockNum();
            skuStock.setAvailableNum(availableNum);
            skuStockMapper.updateById(skuStock);
        });

        ProductDetails productDetails = productDetailsMapper.selectOne(new LambdaQueryWrapper<ProductDetails>().eq(ProductDetails::getProductId, product.getId()));
        productDetails.setImageUrls(String.join(",",product.getDetailsImageUrlList()));
        productDetailsMapper.updateById(productDetails);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteProductByIds(Long[] ids) {
        productMapper.deleteBatchIds(Arrays.asList(ids));
        List<ProductSku> productSkuList = productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>().in(ProductSku::getProductId, ids).select(ProductSku::getId));
        List<Long> skuIdList = productSkuList.stream().map(ProductSku::getId).collect(Collectors.toList());
        productSkuMapper.delete(new LambdaQueryWrapper<ProductSku>().in(ProductSku::getProductId,ids));
        skuStockMapper.delete(new LambdaQueryWrapper<SkuStock>().in(SkuStock::getSkuId,skuIdList));
        productDetailsMapper.delete(new LambdaQueryWrapper<ProductDetails>().in(ProductDetails::getProductId,ids));
        return 1;
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if (auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        }else{
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
        if (status == 1) {
            product.setStatus(1);
            RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("sku:bloom:filter");
            List<ProductSku> productSkuList = productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, id));
            productSkuList.forEach(item->{
                bloomFilter.add(item.getId());
            });
        }
        else product.setStatus(-1);
        productMapper.updateById(product);

    }

    @Override
    public List<ProductSku> getTopSale() {
        return productSkuMapper.getTopSale();
    }

    @Override
    public List<ProductSku> selectProductSkuList(SkuQuery skuQuery) {
        return productMapper.selectProductSkuList(skuQuery);
    }

    ////////////////////////////////////////////////////////////////////////////////
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ProductSku getProductSku(Long skuId) {
        try{
            //1.优先从缓存中获取数据
            //1.1 构建业务数据Key 形式：前缀+业务唯一标识
            String dataKey = "product:sku:" + skuId;
            //1.2 查询Redis获取业务数据
            ProductSku productSku = (ProductSku) redisTemplate.opsForValue().get(dataKey);
            //1.3 命中缓存则直接返回
            if (productSku != null) {
                log.info("命中缓存，直接返回，线程ID：{}，线程名称：{}", Thread.currentThread().getId(), Thread.currentThread().getName());
                return productSku;
            }
            //2.尝试获取分布式锁（set k v ex nx可能获取锁失败）
            //2.1 构建锁key
            String lockKey = "product:sku:lock:" + skuId;
            //2.2 采用UUID作为线程标识
            String lockVal = UUID.randomUUID().toString().replaceAll("-", "");
            //2.3 利用Redis提供set nx ex 获取分布式锁
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(lockKey, lockVal, 5, TimeUnit.SECONDS);
            if (flag) {
                //3.获取锁成功执行业务,将查询业务数据放入缓存Redis
                log.info("获取锁成功：{}，线程名称：{}", Thread.currentThread().getId(), Thread.currentThread().getName());
                try {
                    productSku = this.getProductSkuFromDB(skuId);
                    long ttl = productSku == null ? 1 * 60 : 10 * 60;
                    redisTemplate.opsForValue().set(dataKey, productSku, ttl, TimeUnit.SECONDS);
                    return productSku;
                } finally {
                    //4.业务执行完毕释放锁
                    String scriptText = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                            "then\n" +
                            "    return redis.call(\"del\",KEYS[1])\n" +
                            "else\n" +
                            "    return 0\n" +
                            "end";
                    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                    redisScript.setScriptText(scriptText);
                    redisScript.setResultType(Long.class);
                    redisTemplate.execute(redisScript, Arrays.asList(lockKey), lockVal);
                }
            } else {
                try {
                    //5.获取锁失败则自旋（业务要求必须执行）
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.error("获取锁失败，自旋：{}，线程名称：{}", Thread.currentThread().getId(), Thread.currentThread().getName());
                return this.getProductSku(skuId);
            }
        }catch (Exception e){
            //兜底处理方案：Redis服务有问题，将业务数据获取自动从数据库获取
            log.error("【商品服务】查询商品信息异常：{}",e);
            return this.getProductSkuFromDB(skuId);
        }
    }
    public ProductSku getProductSkuFromDB(Long skuId) {
        return productSkuMapper.selectById(skuId);
    }

    @GuiguCache(prefix = "product:")
    @Override
    public Product getProduct(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public SkuPrice getSkuPrice(Long skuId) {
        ProductSku productSku = productSkuMapper.selectOne(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getId, skuId).select(ProductSku::getSalePrice, ProductSku::getMarketPrice));
        SkuPrice skuPrice = new SkuPrice();
        BeanUtils.copyProperties(productSku, skuPrice);
        return skuPrice;
    }

    @GuiguCache(prefix = "productDetails:")
    @Override
    public ProductDetails getProductDetails(Long id) {
        return productDetailsMapper.selectOne(new LambdaQueryWrapper<ProductDetails>().eq(ProductDetails::getProductId, id));
    }

    @GuiguCache(prefix = "skuSpecValue:")
    @Override
    public Map<String, Long> getSkuSpecValue(Long id) {
        List<ProductSku> productSkuList = productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, id).select(ProductSku::getId, ProductSku::getSkuSpec));
        Map<String,Long> skuSpecValueMap = new HashMap<>();
        productSkuList.forEach(item -> {
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });
        return skuSpecValueMap;
    }

    @Override
    public SkuStockVo getSkuStock(Long skuId) {
        SkuStock skuStock = skuStockMapper.selectOne(new LambdaQueryWrapper<SkuStock>().eq(SkuStock::getSkuId, skuId));
        SkuStockVo skuStockVo = new SkuStockVo();
        BeanUtils.copyProperties(skuStock, skuStockVo);
        return skuStockVo;
    }

}




