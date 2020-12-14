<template>
  <section>
    <el-row>
      <el-col style="width:55%;margin-left:5%">
        <template v-for="(item,index) in blogs">
          <BlogItem :key="index" :blog-item="item" />
        </template>
        <el-pagination class="center" :current-page.sync="currentPage" :page-sizes="[10, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </el-col>
      <el-col style="width:30%;margin-left:5%;margin-right:5%">
        <cards />
      </el-col>
    </el-row>
  </section>
</template>

<script>
import BlogItem from '../components/BlogItem'
import Cards from '../components/Cards'
import app from '@/common/js/app'
export default {
  name: 'HelloWorld',
  components: {
    BlogItem,
    Cards
  },
  data() {
    return {
      blogs: [],
      updateTime: null,
      categoryId: null,
      title: null,
      listLoading: false,
      total: 0,
      pageNum: 1,
      pageSize: 10,
      currentPage: 1
    }
  },
  mounted() {
    this.onSubmit(1)
    this.$bus.$on('refreshArticle', (data) => {
      if (data.categoryId) {
        this.updateTime = null
        this.title = null
        this.categoryId = data.categoryId
      } else if (data.updateTime) {
        this.categoryId = null
        this.title = null
        this.updateTime = data.updateTime
      } else if (data.title) {
        this.categoryId = null
        this.updateTime = null
        this.title = data.title
      } else {
        this.categoryId = null
        this.updateTime = null
        this.title = null
      }
      this.onSubmit(1)
    })
  },
  beforeDestroy() {
    this.$bus.$off('refreshArticle')
  },
  methods: {
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
        updateTime: this.updateTime,
        categoryId: this.categoryId,
        title: this.title,
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      this.listLoading = true
      app.get('listArticles', parm).then(data => {
        if (data.code === 0) {
          this.total = data.msg.total
          this.listLoading = false
          this.blogs = data.msg.rows
        }
      }).catch(response => {})
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
