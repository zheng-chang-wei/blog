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
        <img style="width:100%;cursor:pointer;" src="http://www.siyuanblog.com/wp-content/uploads/2018/11/电子制造_Electronic-manufacturing_tilt-shift-photography-of-green-computer-motherboard-640x480.jpg" class="image" @click="click">
      </el-col>
      <el-col style="width:70%;margin-left:15px">
        <el-link :underline="false">Macbook远程查看Ubuntu服务器上的TensorBoard</el-link>
        <p>更好地使用TensorBoard</p>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  data() {
    return {
      a: 1
    }
  },
  methods: {
    click() {
      if (this.a === 1) {
        console.log(111)
      }
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
