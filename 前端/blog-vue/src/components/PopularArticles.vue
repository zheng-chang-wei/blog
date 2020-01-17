<template>
  <el-card class="box-card cardStyle">
    <div slot="header" class="clearfix">
      <span>热门文章</span>
    </div>
    <template v-for="(type,index) in types">
      <el-row :key="index" style="margin-bottom:10px;margin-left:15%">
        <el-col :span="18"><el-link :underline="false" href="/">{{ type.label }}</el-link></el-col>
        <el-col :span="4" class="text">{{ type.count }}篇</el-col>
      </el-row>
    </template>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      types: [{
        label: '2019年12月',
        count: 10
      }, {
        label: '2019年11月',
        count: 11
      }, {
        label: '2019年10月',
        count: 12
      }]
    }
  },
  methods: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  scoped>
.text{
  color: #999;font-size: 12px;
  font-family: 'Microsoft YaHei','SF Pro Display',Roboto,Noto,Arial,'PingFang SC',sans-serif;
}
</style>

