<template>
  <section id="addBlog" class="app-container">
    <el-form ref="addBlogForm" :model="form" label-width="80px" style="margin-bottom:120px;height:100%">
      <el-form-item label="文章标题" required>
        <el-input v-model="form.title" placeholder="输入文章标题" maxlength="100" show-word-limit />
      </el-form-item>
      <smeditor :config="config" style="margin-bottom:20px" />
      <el-form-item label="文章封面" required>
        <el-upload
          class="avatar-uploader"
          action=""
          :show-file-list="false"
          :http-request="uploadFile"
          :before-upload="beforeAvatarUpload"
          accept=".jpg,.png"
        >
          <img v-if="form.coverImage" :src="GLOBAL.serverIpAndPort + form.coverImage" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>
      <!-- <el-form-item label="文章标签" required>
        <el-input v-model="form.articleTags" placeholder="输入文章标签" maxlength="100" />
      </el-form-item> -->
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
        <el-button @click="back">返回</el-button>
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
        // 标题
        title: '',
        // 类型
        categoryId: null,
        // 是否公开
        isPublic: '1',
        // 是否打开评论
        openComment: '0',
        // 封面图片地址
        coverImage: '',
        // 文章类型，原创'1'或转载'0'
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
    // 页面改变时,更改尺寸
    window.addEventListener('resize', this.changeTableHeight)
    this.changeTableHeight()
    this.listCategory()
    this.getContent()
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
          this.form.content = doc
          let url = 'insertArticle'
          if (this.articleId && this.articleId !== null) {
            url = 'editArticle'
          }
          app.post(url, this.form).then(data => {
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
    },
    back() {
      this.$router.go(-1)
    },
    getContent() {
      if (this.articleId && this.articleId !== null) {
        const parm = {
          articleId: this.articleId
        }
        app.get('getArticleById', parm).then(data => {
          if (data.code === 0) {
            const msg = data.msg
            this.form = {
              id: this.articleId,
              title: msg.title,
              categoryId: msg.categoryId,
              isPublic: msg.isPublic ? '1' : '0',
              openComment: msg.openComment ? '1' : '0',
              coverImage: msg.coverImage,
              articleType: msg.articleType + ''
            }
            document.querySelector('.smeditor .input-area').innerHTML = msg.content
          }
        }).catch(response => {})
      }
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!')
      }
      return isLt2M
    },
    uploadFile(file) {
      const formData = new FormData()
      formData.append('file', file.file)
      app.postData('upload_file', formData).then(data => {
        if (data.code === 0) {
          this.form.coverImage = data.msg
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
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
