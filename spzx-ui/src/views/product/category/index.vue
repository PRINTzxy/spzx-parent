<script setup name="Category">
    import {getTreeSelect} from "@/api/product/category.js"
    import { ref } from "vue"
    const categoryList = ref([])
    const loading = ref(true)

    const fetchData = async (row,treeNode,resolve) => {
        const {code,data,message} = await getTreeSelect(row.id)
        resolve(data)
    }

    function getList(id){
        //debugger
        loading.value = true
        getTreeSelect(id).then(response => {
            categoryList.value = response.data
            loading.value = false
        })
    }
    getList(0)
</script>
<template>
    <div>
        <el-row :gutter="10" class="mb8">
            <el-col span="1.5">
                <el-button type="primary" plain icon="Upload">导入</el-button>
            </el-col>
            <el-col span="1.5">
                <el-button type="warning" plain icon="Download">导出</el-button>
            </el-col>
            <right-toolbar></right-toolbar>
        </el-row>

        <el-table :data="categoryList" style="width: 100%" row-key="id" border lazy :load="fetchData" :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column prop="name" label="分类名称" />
            <el-table-column prop="imageUrl" label="图标" #default="scope">
                <img :src="scope.row.imageUrl" width="50" />
            </el-table-column>
            <el-table-column prop="orderNum" label="排序" />
            <el-table-column prop="status" label="状态" #default="scope">
                {{ scope.row.status == 1 ? '正常' : '停用' }}
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" />
        </el-table>
    </div>
</template>
<style scope>
    
</style>