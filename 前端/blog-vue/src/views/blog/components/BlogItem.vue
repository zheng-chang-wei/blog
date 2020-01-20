<template>
  <el-card
    id="card"
    :body-style="{ padding: '0px' }"
    onmouseover="this.style.backgroundColor = '#F8F8FF';"
    onmouseout="this.style.backgroundColor='';"
    shadow="always"
  >
    <el-row>
      <el-col style="width:25%;">
        <img style="width:100%;cursor:pointer;" :src="GLOBAL.serverIpAndPort + blogItem.coverImage" class="image" @click="click">
      </el-col>
      <el-col style="width:70%;margin-left:15px">
        <el-link :underline="false" @click="click">{{ blogItem.title }}</el-link>
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
