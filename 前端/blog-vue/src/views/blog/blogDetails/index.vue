<template>
  <div class="app-container">
    <el-row>
      <h2> {{ form.title }} </h2>
    </el-row>
    <div class="preview-wrapper" v-html="content" />
  </div>
</template>
<script type="text/javascript">
import app from '@/common/js/app'
export default {
  data() {
    return {
      content: '',
      form: {
        // 标题
        title: '',
        // 标签
        articleTags: '',
        // 类型
        categoryId: null,
        // 是否公开
        isPublic: '1',
        // 是否打开评论
        openComment: '0',
        // 封面图片地址
        coverImage: 'b.png',
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
    this.getContent()
  },
  methods: {
    getContent() {
      if (this.articleId && this.articleId !== null) {
        const parm = {
          articleId: this.articleId
        }
        app.get('getArticleById', parm).then(data => {
          if (data.code === 0) {
            const msg = data.msg
            console.log(msg.content)
            this.form = {
              id: this.articleId,
              title: msg.title,
              articleTags: msg.articleTags,
              categoryId: msg.categoryId,
              isPublic: msg.isPublic ? '1' : '0',
              openComment: msg.openComment ? '1' : '0',
              coverImage: msg.coverImage,
              articleType: msg.articleType + ''
            }
            this.content = msg.content
          }
        }).catch(response => {})
      }
    }
  }
}
</script>

<style>
.preview-wrapper{
  width :100%;
  border :1px solid #BFBFBF;
  padding :5px 20px;
}
</style>
