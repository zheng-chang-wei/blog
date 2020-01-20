<template>
  <div class="insert-options-items">
    <ul>
      <li v-for="(key, index) in names" :key="index" @click="insertClick(key, index)">
        <input
          v-if="index === 0"
          id="images_upload"
          ref="input"
          type="file"
          accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"
          multiple="multiple"
          @change="handleFileChange"
        >
        <!-- <img :src="icons[key]" :class="key"> -->
        <img class="image" :src="require('../../assets/editor/'+key+'.svg')">
        <span>{{ labels[index] }}</span>
      </li>
    </ul>
  </div>
</template>
<script type="text/javascript">
export default {
  name: 'Insert',
  props: {
    insertImageClick: {
      type: Function,
      default: null
    },
    insertLink: {
      type: Function,
      default: null
    },
    insertLine: {
      type: Function,
      default: null
    },
    insertVideo: {
      type: Function,
      default: null
    },
    insertBlock: {
      type: Function,
      default: null
    },
    uploadImages: {
      type: Function,
      default: null
    }
  },
  data() {
    return {
      labels: ['图片', '分割线', '视频', '代码块'],
      names: ['insertimage', 'insertLine', 'insertVideo', 'insertBlock']
    }
  },
  methods: {
    handleFileChange() {
      const input = this.$refs.input[0]
      const files = input.files
      this.uploadImages(files)
    },
    insertClick(key, index) {
      if (this[key]) {
        this[key]()
      }
    }
  }
}
</script>

<style>

.insert-options-items {
  width: 120px;
  padding: 5px 0px;
  border-radius: 2px;
  position: absolute;
  box-shadow: 0 1px 2px #ccc;
  background-color: #fff;
  margin-top: 35px;
  text-align: left;
  z-index: 3;
}
.insert-options-items ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.insert-options-items ul li {
  padding: 5px 20px;
  color: #333;
  font-size: 12px;
  position: relative;
  cursor: pointer;
  display: flex;
  align-items: center;
}

.insert-options-items ul li:hover, .insertActive {
  background-color: #E5E5E5;
}

.insert-options-items .image{
  width: 20px;
  height: 20px;
  top: 1px;
  position: relative;
  margin-right: 15px;
}

.insert-options-items #images_upload {
  position: absolute;
  z-index: 2;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}
</style>
