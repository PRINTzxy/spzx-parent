package website.yny84666.spzx.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import website.yny84666.spzx.common.core.web.domain.BaseEntity;

/**
 * 分类品牌
 * @TableName brand
 */
@TableName(value ="brand")
@Data
public class Brand extends BaseEntity implements Serializable {
    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图标
     */
    private String logo;

//    @Override
//    public Long getId() {
//        return super.getId();
//    }
//
//    @Override
//    public void setId(Long id) {
//        super.setId(id);
//    }
//
//    @Override
//    public String getDelFlag() {
//        return super.getDelFlag();
//    }
//
//    @Override
//    public void setDelFlag(String delFlag) {
//        super.setDelFlag(delFlag);
//    }
//
//    @Override
//    public String getSearchValue() {
//        return super.getSearchValue();
//    }
//
//    @Override
//    public void setSearchValue(String searchValue) {
//        super.setSearchValue(searchValue);
//    }
//
//    @Override
//    public String getCreateBy() {
//        return super.getCreateBy();
//    }
//
//    @Override
//    public void setCreateBy(String createBy) {
//        super.setCreateBy(createBy);
//    }
//
//    @Override
//    public Date getCreateTime() {
//        return super.getCreateTime();
//    }
//
//    @Override
//    public void setCreateTime(Date createTime) {
//        super.setCreateTime(createTime);
//    }
//
//    @Override
//    public String getUpdateBy() {
//        return super.getUpdateBy();
//    }
//
//    @Override
//    public void setUpdateBy(String updateBy) {
//        super.setUpdateBy(updateBy);
//    }
//
//    @Override
//    public Date getUpdateTime() {
//        return super.getUpdateTime();
//    }
//
//    @Override
//    public void setUpdateTime(Date updateTime) {
//        super.setUpdateTime(updateTime);
//    }
//
//    @Override
//    public String getRemark() {
//        return super.getRemark();
//    }
//
//    @Override
//    public void setRemark(String remark) {
//        super.setRemark(remark);
//    }
//
//    @Override
//    public Map<String, Object> getParams() {
//        return super.getParams();
//    }
//
//    @Override
//    public void setParams(Map<String, Object> params) {
//        super.setParams(params);
//    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}