package website.yny84666.spzx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import website.yny84666.spzx.user.domain.Region;
import website.yny84666.spzx.user.service.RegionService;
import website.yny84666.spzx.user.mapper.RegionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Dell
* @description 针对表【region(地区信息表)】的数据库操作Service实现
* @createDate 2024-10-07 01:40:43
*/
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
    implements RegionService{

    @Resource
    private RegionMapper regionMapper;

    @Override
    public List<Region> treeSelect(String parentCode) {
        return regionMapper.selectList(new LambdaQueryWrapper<Region>().eq(Region::getParentCode, parentCode));
    }
}




