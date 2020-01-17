<template>
  <div class="preview">
    <div class="preview-wrapper" v-html="content" />
  </div>
</template>
<script type="text/javascript">
export default {
  data() {
    return {
      content: ''
    }
  },
  mounted() {
    this.content = window.localStorage.getItem('smeditorPreview')
  }
}
</script>

<style>
.preview-wrapper{
  width :100%;
  margin :50px auto;
  border :1px solid #BFBFBF;
  padding :20px;
}
</style>
