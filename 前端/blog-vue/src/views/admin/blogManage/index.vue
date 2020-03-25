<template>
  <div class="app-container">
    <el-form ref="retrieveForm" :inline="true" :model="retrieveForm">
      <el-form-item label="标题">
        <el-input v-model="retrieveForm.title" placeholder="请输入标题关键字" size="small" />
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="retrieveForm.categoryId" placeholder="请选择分类" size="small">
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" icon="el-icon-search" @click="onSubmit(1)">查询</el-button>
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
      <el-table-column label="分类" prop="categoryName" align="center" sortable />
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
      <el-pagination :current-page.sync="currentPage" :page-sizes="[10, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" style="float: right;" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
    </el-col>
  </div>
</template>

<script>
import app from '@/common/js/app'
export default {
  data() {
    return {
      categoryOptions: [],
      retrieveForm: {
        categoryId: null,
        title: null
      },
      listLoading: false,
      tableMaxHeight: 0,
      tableData: [],
      selections: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      currentPage: 1
    }
  },
  created() {
    window.addEventListener('resize', this.changeTableMaxHeight)
    this.changeTableMaxHeight()
  },
  mounted() {
    window.onresize = this.changeTableHeight
    this.listCategory()
    this.onSubmit(1)
  },
  methods: {
    // 动态更改表格最大高度
    changeTableMaxHeight() {
      this.tableMaxHeight = document.body.offsetHeight - 270
    },
    onSubmit(currentPage) {
      this.currentPage = currentPage
      this.handleCurrentChange(currentPage)
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
      const parm = {
        categoryId: this.retrieveForm.categoryId,
        title: this.retrieveForm.title,
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      this.listLoading = true
      app.get('listArticles', parm).then(data => {
        if (data.code === 0) {
          this.tableData = data.msg.rows
          this.total = data.msg.total
          this.listLoading = false
        }
      }).catch(response => {})
    },
    handleAdd() {
      this.$router.push('/addBlog')
    },
    handleEdit(index, row) {
      this.$router.push({
        path: '/editBlog',
        query: {
          id: row.id
        }
      })
    },
    handleDelete(index, row) {
      const deleteParm = {
        articleIds: row.id
      }
      this.deleteSumbit(deleteParm)
    },
    batchRemove() {
      if (this.selections.length === 0) {
        this.$message({
          message: '请至少勾选一条数据删除',
          type: 'error'
        })
        return
      }
      const ids = this.selections.map(item => item.id).toString()
      const deleteParm = {
        articleIds: ids
      }
      this.deleteSumbit(deleteParm)
    },
    deleteSumbit(deleteParm) {
      this.$confirm('确认删除选中记录？', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true
        app.post('deleteArticleById', deleteParm).then(data => {
          if (data.code === 0) {
            const parm = {
              categoryId: this.retrieveForm.categoryId,
              title: this.retrieveForm.title,
              pageNum: this.pageNum,
              pageSize: this.pageSize
            }
            app.get('listArticles', parm).then(data => {
              if (data.code === 0) {
                if (data.msg.total === 0) {
                  this.tableData = data.msg.rows
                  this.total = data.msg.total
                  this.listLoading = false
                } else {
                  if (data.msg.rows.length === 0) {
                    this.onSubmit(this.currentPage - 1)
                  } else {
                    this.tableData = data.msg.rows
                    this.total = data.msg.total
                    this.listLoading = false
                  }
                }
                this.$message({
                  message: '删除成功',
                  type: 'success'
                })
              }
            }).catch(response => {})
          }
        }).catch(response => {})
      })
    },
    selectionChange(selections) {
      this.selections = selections
    },
    listCategory() {
      this.categoryOptions = []
      this.categoryOptions.push({
        id: null,
        categoryName: '所有'
      })
      app.get('listCategory').then(data => {
        if (data.code === 0) {
          for (let i = 0; i < data.msg.rows.length; i++) {
            this.categoryOptions.push(data.msg.rows[i])
          }
        }
      }).catch(response => {})
    }
  }
}
</script>

<style scoped>

</style>

