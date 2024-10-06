package website.yny84666.spzx.user.service;

import website.yny84666.spzx.user.domain.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Dell
* @description 针对表【region(地区信息表)】的数据库操作Service
* @createDate 2024-10-07 01:40:43
*/
public interface RegionService extends IService<Region> {

    List<Region> treeSelect(String parentCode);
}
