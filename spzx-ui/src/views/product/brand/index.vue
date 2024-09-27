<script setup name="Brand">
  import {listBrand} from "../../../api/brand.js";
  //定义分页列表数据模型
  const brandList = ref([
    // {
    //   "createTime": "2024-01-09 10:15:05",
    //   "id": 1,
    //   "name": "华为",
    //   "logo": "http://139.198.127.41:9000/sph/20230506/华为.png"
    // },
    // {
    //   "createTime": "2024-01-19 10:15:05",
    //   "id": 2,
    //   "name": "小米",
    //   "logo": "http://139.198.127.41:9000/sph/20240202/xm.png"
    // }
  ]);
  //定义列表总记录数模型
  const total = ref(0);
  const loading = ref(true)
  const data = reactive({
    queryParams: {
      pageNum: 1,
      pageSize: 10},
  })
  const {queryParams} = toRefs(data);

  function getList(){
    loading.value = true
    listBrand(queryParams.value).then(response => {
      brandList.value = response.rows
      total.value = response.total;
      loading.value = false
    })
  }
  getList()
</script>

<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form ref="queryRef" :inline="true" label-width="68px">
      <el-form-item label="品牌名称" prop="name">
        <el-input placeholder="请输入品牌名称" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search">搜索</el-button>
        <el-button icon="Refresh">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 功能按钮栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete">删除</el-button>
      </el-col>
    </el-row>

    <!-- 数据展示表格 -->
    <el-table v-loading="loading" :data="brandList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="品牌名称" prop="name" width="200"/>
      <el-table-column label="品牌图标" prop="logo" #default="scope">
        <img :src="scope.row.logo" width="50" />
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit">修改</el-button>
          <el-button link type="primary" icon="Delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页条组件 -->
    <pagination v-show="total > 0" :total="total"
                v-model:page="queryParams.pageNum"
                v-model:limit="queryParams.pageSize"
                @pagination="getList"
    />

  </div>
</template>

<style scoped lang="scss">

</style>