<template>
  <div id="app">
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      background-color="#343a40"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
    >
      <el-menu-item index="/home" route="/home">首页</el-menu-item>
      <el-menu-item index="/blog" route="/blog">博客</el-menu-item>
      <el-menu-item id="menu" class="center" style="margin-left:70%">
        <el-row>
          <el-col style="width:80%">
            <el-input v-model="search" placeholder="Search" />
          </el-col>
          <el-col style="width:17%;margin-left:3%">
            <el-button icon="el-icon-search" />
          </el-col>
        </el-row>
      </el-menu-item>
    </el-menu>
    <div id="bottom">
      <div id="router-view">
        <keep-alive>
          <router-view v-if="$route.meta.keepAlive" />
        </keep-alive>
        <router-view v-if="!$route.meta.keepAlive" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      search: '',
      activeIndex: '/home'
    }
  },
  computed: {

  },
  created() {},
  mounted() {
    this.activeIndex = this.$route.path
  },
  methods: {

  }
}

</script>
<style>html,
body,
#app {
  margin: 0;
  height: 100%;
  background: #f6f6f8;
  font-size: 14px;
}

#top {
  flex-shrink: 0;
  height: 60px;
  width: 100%;
  box-shadow: 0 0.25rem 0.25rem rgba(0, 0, 0, .25), inset 0 -1px 5px rgba(0, 0, 0, .25);
  background-color: #343a40 !important;
  color: #fff;
}

#menu .el-button{
 padding: 8px 8px;
    font-size: 5px;
    border-radius: 20px;
}
.el-menu-item .el-icon-search {
    margin-right: 0px;
    font-size: 10px;
    width: 10px;
}
#menu .el-input__inner{
  font-size: 12px;
  height: 32px;
  line-height: 32px;
}
#app {
  display: flex;
  flex-direction: column;
  overflow: hidden;
  width: 100%;
}

#app>#bottom {
  display: flex;
  flex-grow: 1;
  align-items: stretch;
  height: 100%;
  width: 100%;
}

#app>#bottom>div {
  overflow: auto;
}

#app>#bottom>#router-view {
  flex-grow: 1;
  width: 100%;
  padding-top: 15px;
  padding-bottom: 70px;
  box-sizing: border-box;
}

</style>
