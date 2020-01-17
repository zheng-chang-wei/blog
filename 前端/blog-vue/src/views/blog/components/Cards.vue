<template>
  <section>
    <!-- <el-row>
      <PersonalInformation />
    </el-row> -->
    <el-row>
      <Type />
    </el-row>
    <el-row>
      <File />
    </el-row>
    <el-row>
      <PopularArticles />
    </el-row>
    <el-row>
      <LatestComment />
    </el-row>
  </section>
</template>

<script>
// import PersonalInformation from '../../../components/PersonalInformation'
import Type from '../../../components/Type'
import File from '../../../components/File'
import PopularArticles from '../../../components/PopularArticles'
import LatestComment from '../../../components/LatestComment'
export default {
  components: {
    // PersonalInformation,
    Type,
    File,
    PopularArticles,
    LatestComment
  },
  data() {
    return {
    }
  }
}
</script>

<style scoped>
.cardStyle {
  border-radius: 4px 4px 0 0;
  margin-bottom: 15px;
  width: 100%;
}
</style>
