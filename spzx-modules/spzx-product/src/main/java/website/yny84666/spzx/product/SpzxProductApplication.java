package website.yny84666.spzx.product;

import jakarta.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import website.yny84666.spzx.common.security.annotation.EnableCustomConfig;
import website.yny84666.spzx.common.security.annotation.EnableRyFeignClients;
import website.yny84666.spzx.product.api.domain.vo.ProductSku;
import website.yny84666.spzx.product.mapper.ProductSkuMapper;

import java.util.List;

/**
 * 商品模块
 *
 * @author spzx
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class SpzxProductApplication implements CommandLineRunner {
    public static void main(String[] args)
    {
        SpringApplication.run(SpzxProductApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }

    @Resource
    private RedissonClient redissonClient;
    @Resource
    private ProductSkuMapper productSkuMapper;

    @Override
    public void run(String... args) throws Exception {
        //初始化布隆过滤器
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("sku:bloom:filter");
        
        //设置数据规模 误判率 预计统计元素数量为100000，期望误差率为0.01
        bloomFilter.tryInit(100000,0.01);
        
        //测试使用，快速自动加入
        List<ProductSku> productSkuList = productSkuMapper.selectList(null);
        productSkuList.forEach(item -> {bloomFilter.add(item.getId());});
    }
}
