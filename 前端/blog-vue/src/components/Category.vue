<template>
  <el-card class="box-card cardStyle">
    <div slot="header" class="clearfix">
      <span>分类专栏</span>
    </div>
    <template v-for="(category,index) in categorys">
      <el-row :key="index" style="margin-bottom:10px;margin-left:15%">
        <el-col :span="18"><el-link :underline="false" @click="click(category.id)">{{ category.categoryName }}</el-link></el-col>
        <el-col :span="4" class="text">{{ category.count }}篇</el-col>
      </el-row>
    </template>
  </el-card>
</template>

<script>
import app from '@/common/js/app'
export default {
  data() {
    return {
      categorys: []
    }
  },
  mounted() {
    this.statistics()
  },
  methods: {
    statistics() {
      app.get('statisticsArticleCategory').then(data => {
        if (data.code === 0) {
          this.categorys = data.msg
        }
      }).catch(response => {})
    },
    click(categoryId) {
      this.$bus.$emit('refreshArticle', { categoryId: categoryId })
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

