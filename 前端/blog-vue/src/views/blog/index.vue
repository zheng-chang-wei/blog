<template>
  <section>
    <el-row>
      <el-col style="width:55%;margin-left:5%">
        <template v-for="(item,index) in blogs">
          <BlogItem :key="index" />
        </template>
      </el-col>
      <el-col style="width:30%;margin-left:5%;margin-right:5%">
        <cards />
      </el-col>
    </el-row>

  </section>
</template>

<script>
import BlogItem from './components/BlogItem'
import Cards from './components/Cards'
export default {
  name: 'HelloWorld',
  components: {
    BlogItem,
    Cards
  },
  data() {
    return {
      blogs: [1, 2, 3, 4, 5, 6, 7]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>
