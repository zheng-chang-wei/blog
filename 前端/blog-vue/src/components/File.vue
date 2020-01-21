<template>
  <el-card class="box-card cardStyle">
    <div slot="header" class="clearfix">
      <span>归档</span>
    </div>
    <template v-for="(date,index) in dates">
      <el-row :key="index" style="margin-bottom:10px;margin-left:15%">
        <el-col :span="18"><el-link :underline="false" @click="click(date.updateTime)">{{ date.updateTime }}</el-link></el-col>
        <el-col :span="4" class="text">{{ date.count }}篇</el-col>
      </el-row>
    </template>
  </el-card>
</template>

<script>
import app from '@/common/js/app'
export default {
  data() {
    return {
      dates: []
    }
  },
  mounted() {
    this.statistics()
  },
  methods: {
    statistics() {
      app.get('statisticsArticleByDate').then(data => {
        if (data.code === 0) {
          this.dates = data.msg
        }
      }).catch(response => {})
    },
    click(updateTime) {
      this.$bus.$emit('refreshArticle', { updateTime: updateTime })
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

