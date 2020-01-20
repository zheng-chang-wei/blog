<template>
  <el-card class="box-card cardStyle">
    <div slot="header" class="clearfix">
      <span>热门文章</span>
    </div>
    <template v-for="(title,index) in titles">
      <el-row :key="index" style="margin-bottom:10px;margin-left:2%">
        <el-col :span="18"><el-link :underline="false" @click="click(title.articleId)">{{ title.title }}</el-link></el-col>
        <el-col :span="4" class="text">阅读数 {{ title.showCount }}</el-col>
      </el-row>
    </template>
  </el-card>
</template>

<script>
import app from '@/common/js/app'
export default {
  data() {
    return {
      titles: []
    }
  },
  mounted() {
    this.statistics()
  },
  methods: {
    statistics() {
      app.get('statisticsPopularArticles').then(data => {
        if (data.code === 0) {
          this.titles = data.msg
        }
      }).catch(response => {})
    },
    click(articleId) {
      this.$router.push({
        path: '/detail',
        query: {
          id: articleId
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  scoped>
.text{
  color: #999;font-size: 12px;
  font-family: 'Microsoft YaHei','SF Pro Display',Roboto,Noto,Arial,'PingFang SC',sans-serif;
}
</style>

