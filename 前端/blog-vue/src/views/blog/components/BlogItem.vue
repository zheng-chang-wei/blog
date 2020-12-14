<template>
  <el-card
    id="card"
    :body-style="{ padding: '0px' }"
    onmouseover="this.style.backgroundColor = '#F8F8FF';"
    onmouseout="this.style.backgroundColor='';"
    shadow="always"
  >
    <el-row>
      <el-col style="width:70%;margin-left:15px">
        <el-link :underline="false" @click="click">{{ blogItem.title }}</el-link>
        <el-row>
          <el-col :span="8">
            <p><em class="el-icon-time" /> {{ blogItem.updateTime }}</p>
          </el-col>
          <el-col :span="5">
            <p style="margin-left:4px"> <em class="el-icon-view" /> 阅读（{{ blogItem.showCount }}）</p>
          </el-col>
          <el-col :span="7">
            <p> <em class="el-icon-chat-dot-round" /> 评论（0）</p>
          </el-col>
        </el-row>
        <p>{{ blogItem.title }}</p>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  props: {
    blogItem: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
    }
  },
  methods: {
    click() {
      this.$router.push({
        path: '/detail',
        query: {
          id: this.blogItem.id
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#card {
  border-radius: 4px 4px 0 0;
  padding: 10px;
  margin-bottom: 15px;
}
#card .el-link{
  font-size: 17px;font-weight: bold;
}
#card p{
  color: #999;font-size: 12px;
}
</style>
