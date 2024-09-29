<script setup name="Brand">
//////////////////////////////////////////////////////////////
  import {getToken} from "@/utils/auth.js"
  import {listBrand,addBrand,getBrand,updateBrand,delBrand} from "@/api/product/brand.js"
  import {ElMessage, ElMessageBox} from "element-plus"
  import { ref } from "vue";
  //定义分页列表数据模型
  const brandList = ref([]);
  //定义列表总记录数模型
  const total = ref(0);
  const loading = ref(true)
  const data = reactive({
    //定义搜索模型
    queryParams: {
      pageNum: 1,
      pageSize: 10,
      name: '',
    },
    form: {},
    imgUpload: {
      //设置上传的请求头部
      headers: {
        Authorization: "Bearer " + getToken()
      },
      url: import.meta.env.VITE_APP_BASE_API + "/file/upload"
    },
    rules: {
      name: [{ required: true, message: "品牌名称不能为空", trigger: "blur" }],
      logo: [{ required: true, message: "品牌LOGO不能为空", trigger: "blur" }],
    }
  })
  //新增与修改弹出层控制模型
  const open = ref(false)
  //新增与修改弹出层标题模型
  const title = ref("")
  /**
   * toRef: 复制 reactive 里的单个属性并转成 ref
   * toRefs: 复制 reactive 里的所有属性并转成 ref
   */
  const {queryParams,form,imgUpload,rules} = toRefs(data);
  //定义批量操作id列表模型
  const ids = ref([])
  //定义单选控制模型
  const single = ref(true)
  //定义多选控制模型
  const multiple = ref(true)
  //定义隐藏搜索控制模型
  const showSearch = ref(true);
  function getList(){
    loading.value = true
    listBrand(queryParams.value).then(response => {
      brandList.value = response.rows
      total.value = response.total;
      loading.value = false
    })
  }
  getList()
  /**
   * 点击事件
   * @handleQuery 搜索
   * @resetQuery 重置
   */
  function handleQuery() {
    getList()
  }
  function resetQuery() {
    /* queryParams.value.pageNum = 1
    queryParams.value.pageSize = 10;
    queryParams.value.name = null */
    proxy.resetForm("queryRef");
    handleQuery()
  }

  //表单重置
  function reset(){
    form.value = {
      id: '',
      name: '',
      logo: ''
    }
    proxy.resetForm("brandRef");
  }

  //新增按钮操作
  function handleAdd(){
    reset()
    open.value = true
    title.value = "添加品牌"
  }

  //上传
  function handleAvatarSuccess (response, uploadFile) {
    console.log(response)
    console.log(uploadFile)
    form.value.logo = response.data.url
  }
  //取消按钮
  function cancel(){
    open.value = false
    reset()
  }
  //提交按钮
  function submitForm(){
    proxy.$refs["brandRef"].validate(valid => {
      if (valid) {
        if (form.value.id != null) {
          updateBrand(form.value).then(response => {
            // ElMessage.success('修改成功')
            const { proxy } = getCurrentInstance()
            proxy.$modal.msgSuccess("修改成功")
            open.value = false
            getList()
          })
        } else {
          addBrand(form.value).then(response=>{
            ElMessage.success("新增成功")
            open.value = false
            getList()
          })
        }
      }
    })
  }
  //修改按钮操作
  function handleUpdate(row){
    reset()
    const _id = row.id || ids.value
    getBrand(_id).then(response=>{
      form.value = response.data
      open.value = true
      title.value = "修改品牌"
    })
  }
  //删除按钮操作
  /* function handleDelete(row){
    const _ids = row.id || ids.value
    ElMessageBox.confirm('是否确认删除分类品牌编号为'+_ids+'的数据项？','系统提示',{
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(function(){
      return delBrand(_ids)
    }).then(()=>{
      getList()
      ElMessage.success("删除成功")
    })
    .catch(()=>{})
  } */
 function handleDelete(row) {
    const _ids = row.id || ids.value;
    proxy.$modal.confirm('是否确认删除分类品牌编号为"' + _ids + '"的数据项？').then(function() {
      return delBrand(_ids);
    }).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    }).catch(() => {});
  }

  //多选框选中数据
  function handleSelectionChange(selection) {
    ids.value = selection.map(item => item.id);
    single.value = selection.length != 1;
    multiple.value = !selection.length;
  }



//////////////////////////////////////////////////////////////
  
</script>

<template>
  <div class="app-container">

    <!-- 搜索表单 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="品牌名称" prop="name">
        <el-input placeholder="请输入品牌名称" v-model="queryParams.name" clearable/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 功能按钮栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete"  :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据展示表格 -->
    <el-table v-loading="loading" :data="brandList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="品牌名称" prop="name" width="200"/>
      <el-table-column label="品牌图标" prop="logo" #default="scope">
        <img :src="scope.row.logo" width="50" />
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" >修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页条组件 -->
    <pagination v-show="total > 0"
               :total="total"
                v-model:page="queryParams.pageNum"
                v-model:limit="queryParams.pageSize"
                @pagination="getList" >
    </pagination>

    <!-- 新增或修改分类品牌对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="brandRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="品牌名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入品牌名称"/>
        </el-form-item>
        <el-form-item label="品牌图标" prop="logo">
          <el-upload class="avatar-uploader"
                       :action="imgUpload.url"
                       :headers="imgUpload.headers"
                       :show-file-list="false"
                       :on-success="handleAvatarSuccess">
            <img v-if="form.logo" :src="form.logo" class="avatar"/>
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm" >确定</el-button>
          <el-button @click="cancel">取消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
  .avatar-uploader .avatar {
        width: 178px;
        height: 178px;
        display: block;
  }

  .avatar-uploader .el-upload {
        border: 1px dashed var(--el-border-color);
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: var(--el-transition-duration-fast);
    }

    .avatar-uploader .el-upload:hover {
        border-color: var(--el-color-primary);
    }

    .el-icon.avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        text-align: center;
    }


</style>