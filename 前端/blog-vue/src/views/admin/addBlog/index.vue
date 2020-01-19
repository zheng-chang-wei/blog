<template>
  <section id="addBlog" class="app-container">
    <el-form ref="addBlogForm" :model="form" label-width="80px" style="margin-bottom:120px;height:100%">
      <el-form-item label="文章标题" required>
        <el-input v-model="form.title" placeholder="输入文章标题" maxlength="100" show-word-limit />
      </el-form-item>
      <smeditor :config="config" style="margin-bottom:20px" />
      <el-form-item label="文章标签" required>
        <el-input v-model="form.articleTags" placeholder="输入文章标签" maxlength="100" />
      </el-form-item>
      <el-form-item label="文章分类" required>
        <el-select v-model="form.categoryId" placeholder="请选择分类" size="small">
          <el-option v-for="item in categoryOptions" :key="item.id" :label="item.categoryName" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="文章类型">
        <el-radio-group v-model="form.articleType">
          <el-radio label="1">原创</el-radio>
          <el-radio label="0">转载</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="发布形式">
        <el-radio-group v-model="form.isPublic">
          <el-radio label="1">公开</el-radio>
          <el-radio label="0">私密</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">发布博客</el-button>
        <el-button>保存为草稿</el-button>
        <el-button>返回</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
import SMEditor from '@/components/addBlog/SMEditor.vue'
import app from '@/common/js/app'
const config = {
  sticky: true,
  // 上传失败回调, 可选
  uploadFailed: (err) => {
    alert('仅供测试, 并非真正上传!', err)
  }
}

export default {
  name: 'App',
  components: {
    'smeditor': SMEditor
  },
  data() {
    return {
      categoryOptions: [],
      config: config,
      form: {
        title: '',
        articleTags: '',
        categoryId: null,
        isPublic: '1',
        openComment: '0',
        coverImage: 'b.png',
        articleType: '1'
      }
    }
  },
  computed: {
    articleId: function() {
      return this.$route.query.id
    }
  },
  mounted() {
    console.log(this.articleId)
    // 页面改变时,更改尺寸
    window.addEventListener('resize', this.changeTableHeight)
    this.changeTableHeight()
    this.listCategory()
  },
  methods: {
    changeTableHeight() {
      const sectionHeight = document.body.offsetHeight - 50 + 'px'
      const element = document.getElementById('addBlog')
      if (element !== null) {
        element.style.height = sectionHeight
      }
    },
    onSubmit() {
      this.$refs.addBlogForm.validate((valid) => {
        if (valid) {
          const doc = document.querySelector('.smeditor .input-area').innerHTML
          console.log(doc)
          this.form.content = doc
          app.post('insertArticle', this.form).then(data => {
            if (data.msg) {
              if (data.code === 0) {
                this.$router.push('/blogManage')
              }
            }
          }).catch(response => {})
        } else {
          console.log('error submit!!')
        }
      })
    },
    listCategory() {
      this.categoryOptions = []
      app.get('listCategory').then(data => {
        if (data.code === 0) {
          this.categoryOptions = data.msg.rows
        }
      }).catch(response => {})
    }
  }
}
</script>

<style >
  #addBlog{
    overflow:auto;
    height:100%;
    width :100%;
    /* margin: 0 auto;
    background-image :url('https://assets-cdn.shimo.im/assets/images/home/index/banner_back-9564341c38.jpg');
    background-size :1280px 580px;
    background-size :cover;
    background-position: center; */
  }
</style>
