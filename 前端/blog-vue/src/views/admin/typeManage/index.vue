<template>
  <div class="app-container">
    <el-form ref="retrieveForm" :inline="true" :model="retrieveForm">
      <el-form-item>
        <el-input v-model="retrieveForm.categoryId" placeholder="分类专栏" size="small" />
      </el-form-item>
      <el-form-item>
        <el-input v-model="retrieveForm.title" placeholder="请输入标题关键字" size="small" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" icon="el-icon-search" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>

    <!--工具条-->
    <el-col :span="24">
      <el-form :inline="true">
        <el-form-item>
          <el-button type="primary" size="small" icon="el-icon-plus" @click="handleAdd">新增</el-button>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="batchRemove">批量删除</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-table
      v-loading="listLoading"
      :data="tableData"
      style="width: 100%"
      border
      highlight-current-row
      :max-height="tableMaxHeight"
      @selection-change="selectionChange"
    >
      <el-table-column type="selection" align="center" width="45" />
      <el-table-column label="标题" prop="title" align="center" sortable />
      <el-table-column label="分类" prop="type" align="center" sortable />
      <el-table-column label="创建日期" prop="updateTime" align="center" sortable />
      <el-table-column align="center">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页  工具条-->
    <el-col :span="24" class="toolbar" style="position:absolute;bottom:20px;right:20px">
      <el-pagination :current-page.sync="currentPage" :page-sizes="[20, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" style="float: right;" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
    </el-col>
  </div>
</template>

<script>
import app from '@/common/js/app'
export default {
  data() {
    return {
      retrieveForm: {
        categoryId: 0,
        title: ''
      },
      listLoading: false,
      tableMaxHeight: 0,
      tableData: [],
      selections: [],
      total: 0,
      pageNum: 1,
      pageSize: 20,
      currentPage: 1
    }
  },
  created() {
    window.addEventListener('resize', this.changeTableMaxHeight)
    this.changeTableMaxHeight()
  },
  mounted() {
    window.onresize = this.changeTableHeight
    this.onSubmit()
  },
  methods: {
    // 动态更改表格最大高度
    changeTableMaxHeight() {
      this.tableMaxHeight = document.body.offsetHeight - 270
    },
    onSubmit() {
      this.currentPage = 1
      this.handleCurrentChange(1)
    },
    handleEdit(index, row) {
      console.log(index, row)
    },
    handleDelete(index, row) {
      console.log(index, row)
    },
    // 分页触发
    handleCurrentChange(val) {
      this.pageNum = val
      this.retrieve()
    },
    // 改变页码
    handleSizeChange(val) {
      this.pageSize = val
      this.retrieve()
    },
    retrieve() {
      app.get('listArticles', this.retrieveForm).then(data => {
        if (data.msg) {
          if (data.code === 0) {
            this.tableData = data.msg
          }
        }
      }).catch(response => {})
    },
    handleAdd() {
      this.$router.push('/addBlog')
    },
    batchRemove() {

    },
    selectionChange(selections) {
      this.selections = selections
    }
  }
}
</script>

<style scoped>

</style>

