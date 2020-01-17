<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
<style>
.red {
  border: 2px solid red;
}
.center {
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  justify-content: center;
}
.hcenter {
  display: flex;
  /*定义body的里的元素水平居中*/
  justify-content: center;
}
.vcenter {
  display: flex;
  /*定义body的元素垂直居中*/
  align-items: center;
}
body {
  font-family: 'Microsoft Yahei';
  line-height: 1.42857143;
}
</style>
