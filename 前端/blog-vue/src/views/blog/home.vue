<template>
  <div>
    <h2 class="center">关于我</h2>
    <el-timeline>
      <el-timeline-item timestamp="能力特质" placement="top">
        <el-card class="cardStyle">
          <p>熟悉 Java 编程知识，具备良好的编码习惯。</p>
          <p>熟悉 Spring、Mybatis、SpringMVC等开源框架的使用。</p>
          <p>熟悉 SpringBoot、了解 SpringCloud</p>
          <p>熟悉 MySQL数据库，了解 MySQL的优化。</p>
          <p>熟悉使用 SVN、Maven、Git等项目开发及管理工具。</p>
          <p>熟悉 HTML、CSS、JavaScript，具有 Vue.js开发经验。</p>
          <p>熟悉 MongoDB、Redis等 NOSQL 数据库的基本使用。</p>
          <p>熟悉 Linux常用命令的使用。</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2017/12 - 至今" placement="top">
        <el-card class="cardStyle">
          <h4>上海恒润科技</h4>
          <p>职位：全栈开发工程师 </p>
          <p>职责：1、参与前期需求分析、数据库表设计、方案设计文档编写  </p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、前后端代码编写</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、相关 bug 修改</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、项目部署</p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、上级领导安排的其他工作</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2017/4 - 2017/12" placement="top">
        <el-card class="cardStyle">
          <h4>上海波导信息技术有限公司</h4>
          <p>职位：java 开发工程师 </p>
          <p>职责：1、负责部分模块后端代码开发 </p>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、相关 bug 修改</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2015/9 - 2017/6" placement="top">
        <el-card class="cardStyle">
          <h4>上海大学</h4>
          <p>学历：研究生&nbsp;&nbsp;&nbsp;&nbsp;专业：电子与通信工程</p>
        </el-card>
      </el-timeline-item>
      <el-timeline-item timestamp="2010/9 - 2014/6" placement="top">
        <el-card class="cardStyle">
          <h4>河南工业大学</h4>
          <p>学历：本科&nbsp;&nbsp;&nbsp;&nbsp;专业：电子信息科学与技术</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  computed: {
    aa: function() {
      return this.$route.query.aa
    }
  },
  mounted() {
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cardStyle{
  width:95%;
}
</style>
