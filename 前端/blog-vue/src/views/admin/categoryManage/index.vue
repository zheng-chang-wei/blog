<template>
  <div id="categoryManage" class="app-container">
    <el-form ref="retrieveForm" :inline="true" :model="retrieveForm">
      <el-form-item>
        <el-input v-model="retrieveForm.categoryName" placeholder="请输入标题关键字" size="small" />
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
      <el-table-column label="分类" prop="categoryName" align="center" sortable />
      <el-table-column align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页  工具条-->
    <el-col :span="24" class="toolbar" style="position:absolute;bottom:10px;right:20px">
      <el-pagination
        :current-page.sync="currentPage"
        :page-sizes="[10, 50, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        style="float: right;"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </el-col>
    <!--添加或编辑界面-->
    <el-dialog :title="isAdd?'添加':'编辑'" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form
        ref="dialogForm"
        :model="dialogForm"
        label-width="80px"
        style="margin-left:5%;"
      >
        <el-form-item label="分类名称" required>
          <el-input
            v-model="dialogForm.categoryName"
            placeholder="请输入分类名称"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="dialogLoading" @click.native="dialogBtnChecked">
          提交
        </el-button>
        <el-button type="cancel" @click.native="deleteFormVisible = false">取消
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import app from '@/common/js/app'
export default {
  data() {
    return {
      retrieveForm: { categoryName: '' },
      listLoading: false,
      tableMaxHeight: 0,
      tableData: [],
      selections: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      currentPage: 1,
      isAdd: true,
      dialogVisible: false,
      dialogForm: { categoryName: '' },
      dialogLoading: false
    }
  },
  created() {
    window.addEventListener('resize', this.changeTableMaxHeight)
    this.changeTableMaxHeight()
  },
  mounted() {
    window.onresize = this.changeTableHeight
    this.onSubmit(1)
  },
  methods: {
    // 动态更改表格最大高度
    changeTableMaxHeight() {
      this.tableMaxHeight = document.body.offsetHeight - 203
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
        name: this.retrieveForm.categoryName,
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      this.listLoading = true
      app.get('listCategory', parm).then(data => {
        if (data.code === 0) {
          this.tableData = data.msg.rows
          this.total = data.msg.total
          this.listLoading = false
        }
      }).catch(response => {})
    },
    handleAdd() {
      this.isAdd = true
      this.dialogForm.categoryName = ''
      this.dialogForm.id = null
      this.dialogVisible = true
    },
    handleEdit(index, row) {
      console.log(index, row)
      this.isAdd = false
      this.dialogForm.categoryName = row.categoryName
      this.dialogForm.id = row.id
      this.dialogVisible = true
    },
    handleDelete(index, row) {
      console.log(index, row)
      const deleteParm = {
        categoryIds: row.id
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
        categoryIds: ids
      }
      this.deleteSumbit(deleteParm)
    },
    deleteSumbit(deleteParm) {
      this.$confirm('确认删除选中记录？', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true
        app.post('deleteCategoryById', deleteParm).then(res => {
          if (res.code === 0) {
            const parm = {
              name: this.retrieveForm.categoryName,
              pageNum: this.pageNum,
              pageSize: this.pageSize
            }
            app.get('listCategory', parm).then(data => {
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
    dialogBtnChecked() {
      this.$refs.dialogForm.validate((valid) => {
        if (valid) {
          if (this.isAdd) {
            app.post('insertCategory', this.dialogForm).then(data => {
              if (data.code === 0) {
                this.$message({
                  message: '添加成功',
                  type: 'success'
                })
                this.dialogVisible = false
                this.onSubmit(1)
              }
            }).catch(response => {})
          } else {
            app.post('editCategory', this.dialogForm).then(data => {
              if (data.code === 0) {
                this.$message({
                  message: '编辑成功',
                  type: 'success'
                })
                this.dialogVisible = false
                this.retrieve()
              }
            }).catch(response => {})
          }
        } else {
          this.$message({
            message: '提交失败',
            type: 'error'
          })
          return false
        }
      })
    }
  }
}
</script>

<style>
#categoryManage .el-form-item {
    margin-bottom: 5px;
}
</style>

