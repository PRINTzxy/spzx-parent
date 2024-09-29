<script setup name="CategoryBrand">
    import { listCategoryBrand,addCategoryBrand,getCategoryBrand,updateCategoryBrand,delCategoryBrand } from "@/api/product/categoryBrand.js"
    import { reactive, ref } from "vue"
    import { getBrandAll } from "@/api/product/brand.js"
    import { getTreeSelect } from "@/api/product/category.js"

    const { proxy } = getCurrentInstance();

    //定义分页列表数据模型
    const categoryBrandList = ref([])

    //定义列表总记录数模型
    const total = ref(0)

    //加载数据时显示的动效控制模型
    const loading = ref(true)
    const showSearch = ref(true) //true为显示，false为隐藏

    const data = reactive({
        queryParams: {
            pageNum: 1,
            pageSize: 10,
            brandId: null,
            category: null
        },
        form: {},
        rules: {
            categoryIdList: [
                { required: true, message: "分类不能为空", trigger: "blur" }
            ],
            brandId: [
            { required: true, message: "品牌不能为空", trigger: "blur" }
            ]
        }
    })

    const {queryParams,form,rules} = toRefs(data)
    //查询分类品牌列表
    function getList(){
        loading.value =true //设置为true表示正在加载
        listCategoryBrand(queryParams.value).then(response => {
            categoryBrandList.value = response.rows
            total.value = response.total
            loading.value = false //设置为false通常会取消加载动画或恢复按钮的可操作性
        })
    }


    //品牌
    const brandList = ref([])
    function getBrandAllList(){
        getBrandAll().then(response => {
            brandList.value = response.data
        })
    }
    getBrandAllList()

    //三级分类
    const props = {
        lazy: true,
        value: 'id',
        label: 'name',
        leaf: 'leaf',
        async lazyLoad(node, resolve) {   //加载数据的方法
            const { level } = node
            if (typeof node.value == 'undefined') node.value = 0
            const {code,data,message} = await getTreeSelect(node.value)
            //hasChildren判断是否有子节点
            data.forEach(function(item) {
                item.leaf = !item.hasChildren
            })
            resolve(data)  //返回数据
        }
    }
    const categoryProps = ref(props)
    
    //处理分类change事件
    const queryCategoryIdList = ref([])
    const handleCategoryChange = () => {
        if(queryCategoryIdList.value.length == 3){
            queryParams.value.categoryId = queryCategoryIdList.value[2]
        }
    }

    //搜索操作
    function handleQuery(){
        queryParams.value.pageNum = 1 //每次查询时都会将页码重置为第一页
        getList()
    }
    //重置操作
    function resetQuery(){
        queryCategoryIdList.value = []
        proxy.resetForm("queryRef")
        queryParams.value.categoryId = null
        queryParams.value.brandId = null
        handleQuery()
    }
    getList()

    //对话框新增与修改弹出层控制模型
    const open = ref(false)

    //对话框新增与修改弹出层标题模型
    const title = ref("")

    function handleAdd(){
        reset()
        open.value = true
        title.value = "添加分类品牌"
    }

    function reset(){
        form.value = {
            id: null,
            brandId: null,
            categoryId: null,
            categoryIdList: []
        }
        proxy.resetForm("categoryBrandRef")
    }

    //取消按钮
    function cancel(){
        open.value = false
        reset()
    }

    //提交按钮
    function submitForm(){
        proxy.$refs["categoryBrandRef"].validate(valid => {
            //debugger
            if (valid) {
                //系统只需要三级分类id
                form.value.categoryId = form.value.categoryIdList[2]
                if (form.value.id != null) {
                    updateCategoryBrand(form.value).then(response => {
                    proxy.$modal.msgSuccess("修改成功");
                    open.value = false;
                    getList();
                    });
                } else {
                    addCategoryBrand(form.value).then(response => {
                    proxy.$modal.msgSuccess("新增成功");
                    open.value = false;
                    getList();
                    });
                }
            }
        });
    }

    //定义批量操作id列表模型
    const ids = ref([]);
    //定义单选控制模型
    const single = ref(true);

    //多选框选中数据
    function handleSelectionChange(selection){
        //debugger
        ids.value = selection.map(item=>item.id)
        single.value = selection.length != 1
        multiple.value = !selection.length
    }

    //修改按钮操作
    function handleUpdate(row){
        reset()
        const _id = row._id || ids.value
        getCategoryBrand(_id).then(response => {
            form.value = response.data
            open.value = true
            title.value = "修改分类品牌"
        })
    }

    //定义多选控制模型
    const multiple = ref(true)
    
    //删除按钮操作
    function handleDelete(row){
        const _ids = row.id || ids.value
        proxy.$modal.confirm('是否确认删除分类品牌编号为"' + _ids + '"的数据项？').then(function() {
            return delCategoryBrand(_ids)
        }).then(() => {
            getList()
            proxy.$modal.msgSuccess("删除成功")
        }).catch(() => {});
    }


</script>

<template>
    <div class="app-container">
        <!-- 搜索表单 -->
        <el-form ref="queryRef" :model="queryParams" v-show="showSearch" :inline="true" label-width="68px">
            <el-form-item label="分类">
                <el-cascader :props="categoryProps" v-model="queryCategoryIdList" @change="handleCategoryChange" style="width: 100%"/>
            </el-form-item>
            <el-form-item label="品牌">
                <el-select v-model="queryParams.brandId" class="m-2" placeholder="选择品牌" size="small" style="width: 100%">
                    <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.id"/>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                <el-button icon="Refresh" id="reset-all" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <!-- 功能按钮 -->
        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="Delete" :disabled="single" @click="handleDelete" >删除</el-button>
            </el-col>
            <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <!-- 数据展示表格 -->
        <el-table :data="categoryBrandList" v-loading="loading" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="分类名称" prop="categoryName" />
            <el-table-column label="品牌名称" prop="brandName" />
            <el-table-column prop="logo" label="品牌图标" #default="scope">
                <img :src="scope.row.logo" width="50" />
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template #default="scope">
                    <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
                    <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页条组件 -->
        <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList"/>
    
        <!-- 添加或修改分类品牌对话框 -->
        <el-dialog :title="title" v-model="open" :rules="rules" width="500px" append-to-body>
            <el-form ref="categoryBrandRef" :model="form" label-width="80px">
                <el-form-item label="分类" prop="categoryIdList">
                    <!-- el-cascader级联选择器 -->
                    <el-cascader :props="categoryProps" v-model="form.categoryIdList"/>
                </el-form-item>
                <el-form-item label="品牌" prop="brandId">
                    <el-select v-model="form.brandId" class="m-2" placeholder="选择品牌" size="small">
                        <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.id"/>
                    </el-select>
                </el-form-item>
            </el-form>
            <template #footer>
                <div class="dialog-footer">
                    <el-button type="primary" @click="submitForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
            </template>
        </el-dialog>
    
    </div>
</template>

<style scope>
    
</style>