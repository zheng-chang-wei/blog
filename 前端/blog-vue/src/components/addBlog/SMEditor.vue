<template>
  <div id="smeditor" class="smeditor">
    <div class="buttons" :class="buttonsBarFixed === true ? 'isFixed' :''">
      <button type="button" class="undo" title="撤销" @click="undo" @mouseover.stop="mouseover($event)">
        <!-- <img :src="require('../../assets/editor/'+'undo.svg')"> -->
        <img src="../../assets/editor/undo.svg">
      </button>
      <button type="button" class="redo" title="重做" @click="redo" @mouseover.stop="mouseover($event)">
        <img src="../../assets/editor/redo.svg">
      </button>
      <button type="button" class="remove-format" title="清除" @click="removeFormat" @mouseover.stop="mouseover($event)">
        <img src="../../assets/editor/removeformat.svg">
      </button>
      <button type="button" class="set-font" @click.stop="titleButtonClick">
        <span>H</span>
        <title-picker v-show="isTitlePickerShow" :title-picker-click="titlePickerClick" />
      </button>
      <!-- <button type="button" class="font-size"
              title="字号"
              @click="isFontSizePickerShow = !isFontSizePickerShow"
              @mouseover.stop='mouseover($event)'>
        <span> {{fontSize}} </span>
        <font-size-picker v-bind:FontSizePickerClick="FontSizePickerClick" v-show="isFontSizePickerShow"></font-size-picker>
      </button> -->
      <button
        v-for="(name, index) in basicIcons"
        :key="index"
        type="button"
        :class="{buttonsActive: styles.indexOf(name) > -1}"
        :title="basicStyleNames[Object.keys(basicIcons).indexOf(name)]"
        @click="basicStyleClick(name)"
        @mouseover.stop="mouseover($event)"
      >
        <img :src="require('../../assets/editor/'+name+'.svg')">
      </button>
      <button type="button" title="文本颜色" @mouseover.stop="mouseover($event)">
        <img src="../../assets/editor/color.svg" @click="isColorPickerShow = !isColorPickerShow">
        <color-picker v-show="isColorPickerShow" :color-picker-click="colorPickerClick" />
      </button>
      <button type="button" class="indent" title="增加缩进" @click.stop="indent" @mouseover.stop="mouseover($event)">
        <img src="../../assets/editor/indent.svg">
      </button>
      <button type="button" class="outdent" title="减少缩进" @click.stop="outdent" @mouseover.stop="mouseover($event)">
        <img src="../../assets/editor/outdent.svg">
      </button>
      <button
        type="button"
        class="insert-ol"
        title="有序列表"
        @mouseover.stop="mouseover($event)"
        @click="insertList('OrderedList')"
      >
        <img src="../../assets/editor/listordered.svg">
      </button>
      <button
        type="button"
        class="insert-ul"
        title="无序列表"
        @mouseover.stop="mouseover($event)"
        @click="insertList('UnorderedList')"
      >
        <img src="../../assets/editor/listunordered.svg">
      </button>
      <button
        type="button"
        class="align-left"
        title="左对齐"
        @click="align('Left')"
        @mouseover.stop="mouseover($event)"
      >
        <img src="../../assets/editor/alignleft.svg">
      </button>
      <button
        type="button"
        class="align-center"
        title="居中对齐"
        @click="align('Center')"
        @mouseover.stop="mouseover($event)"
      >
        <img src="../../assets/editor/aligncenter.svg">
      </button>
      <button
        type="button"
        class="align-right"
        title="右对齐"
        @click="align('Right')"
        @mouseover.stop="mouseover($event)"
      >
        <img src="../../assets/editor/alignright.svg">
      </button>
      <!-- <button type="button" class="insert-quote" @click="insertQuote">
        <img src="../../assets/editor/insertquote.svg">
      </button> -->
      <button
        type="button"
        class="insert-link"
        title="插入链接"
        @click="insertLinkClick"
        @mouseover.stop="mouseover($event)"
      >
        <img src="../../assets/editor/insertlink.svg">
      </button>
      <button type="button" class="insert-options" @click="isInsertShow = !isInsertShow">
        <span class="insert-options-label" />
        <insert-options
          v-show="isInsertShow"
          :insert-image="insertImageClick"
          :insert-line="insertLine"
          :insert-video="insertVideoClick"
          :insert-block="insertBlock"
          :upload-images="uploadImages"
        />
      </button>
      <button
        type="button"
        class="backup"
        title="Ctrl + S"
        @click="backupClick"
        @mouseover.stop="mouseover($event)"
      />
      <button
        type="button"
        class="restore"
        @click="restoreClick"
      />
      <button
        type="button"
        class="preview"
        title="Ctrl + P"
        @click="previewClick"
        @mouseover.stop="mouseover($event)"
      />
    </div>
    <div
      id="input-area"
      contenteditable="true"
      autocorrect="off"
      autocomplete="off"
      spellcheck="false"
      class="input-area"
      @mouseup="mouseup"
      @keyup.ctrl.83="backupClick"
      @keyup.enter="kenter"
      @keyup.ctrl.80="previewClick"
    />

    <p v-show="selectWords" class="select-words">{{ selectWords.length }}个字</p>
    <insert-link
      v-show="isInsertLinkShow"
      :insert-link="insertLink"
      :prop-text="insertLinkSection.text"
      :prop-link="insertLinkSection.link"
      :cancel="insertLinkCancel"
    />
    <insert-video
      v-show="isInsertVideoShow"
      :insert-video="insertVideo"
      :cancel="insertVideoCancel"
    />
  </div>
</template>

<script>
import ColorPicker from './ColorPicker.vue'
import TitlePicker from './TitlePicker.vue'
import InsertLink from './InsertLink.vue'
import InsertVideo from './InsertVideo.vue'
import Insert from './Insert.vue'
import app from '@/common/js/app'
// import tippy from '../../../../node_modules/tippy.js/dist/tippy.min.js'
const remove = function(arr, val) {
  const index = arr.indexOf(val)
  if (index > -1) {
    arr.splice(index, 1)
  }
}

const editorElement = function() {
  return document.querySelector('.smeditor .input-area')
}

export default {
  components: {
    'color-picker': ColorPicker,
    'title-picker': TitlePicker,
    'insert-options': Insert,
    'insert-link': InsertLink,
    'insert-video': InsertVideo
  },
  props: {
    config: {
      type: Object,
      default: null
    }
  },

  data() {
    return {
      // 样式
      styles: [],
      // 基本样式名称
      basicIcons: ['bold', 'underline', 'italic', 'strikethrough'],
      basicStyleNames: ['粗体', '斜体', '下划线', '中划线'],
      // 调色盘是否显示
      isColorPickerShow: false,
      // 标题选择是否显示
      isTitlePickerShow: false,
      // 字号选项是否显示
      isFontSizePickerShow: false,
      // 插入选项是否显示
      isInsertShow: false,
      // 插入链接是否显示
      isInsertLinkShow: false,
      // 插入视频是否显示
      isInsertVideoShow: false,
      // 选中文字内容
      selectWords: '',
      // 字号
      fontSize: 16,
      // 光标
      cursor: {},
      // 鼠标选中节点
      selectNode: {},
      buttonsBarFixed: false,
      insertLinkSection: {
        node: '',
        start: 0,
        end: 0,
        text: '',
        link: ''
      }
    }
  },
  mounted() {
    setTimeout(() => {
      editorElement().focus()
      this.insertEmptyP()
      window.addEventListener('scroll', () => {
        // if (this.config.onScroll) {
        //   this.config.onScroll()
        // }
        var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
        var offsetTop = document.querySelector('.smeditor').offsetTop
        if (scrollTop > offsetTop) {
          this.buttonsBarFixed = true
        } else {
          this.buttonsBarFixed = false
        }
      })
    }, 100)
    addEvents(this)
  },
  methods: {
    // 回车事件
    kenter(e) {
      console.log(222)
      e.stopPropagation()
      if (this.styles.length === 0) {
        return false
      }
    },
    // 鼠标事件
    mouseup() {
      this.selectNode = getSelectedNode()
      const str = window.getSelection().toString()
      if (str.length < 1) {
        return false
      }
      this.selectWords = str
      setTimeout(() => {
        this.selectWords = ''
      }, 1500)
    },
    // 鼠标事件
    mouseover(event) {
      let target = ''
      event.path.forEach(el => {
        if (el.localName === 'button' && target === '') {
          target = el
        }
      })
      // tippy(target, {
      //   placement: 'bottom',
      //   animation: 'shift-away',
      //   duration: 100,
      //   arrow: true
      // })
    },
    // 重做
    redo() {
      document.execCommand('redo')
    },
    // 撤销
    undo() {
      document.execCommand('undo')
    },
    // 移除格式
    removeFormat() {
      document.execCommand('removeFormat', false, '')
      document.execCommand('insertHTML', false, `<p></p>`)
      this.styles = []
      this.FontSize = 16
    },
    // 字号选项点击
    fontSizePickerClick(size, index) {
      document.execCommand('FontSize', false, index + 1)
      this.fontSize = size
      this.closeAlert()
    },
    // 标题按钮点击
    titleButtonClick() {
      getCursor(this)
      this.isTitlePickerShow = !this.isTitlePickerShow
    },
    // 标题选项点击
    titlePickerClick(size, index) {
      this.closeAlert()
      let html = ''
      restoreCursor(this)
      const node = getSelectedNode()
      console.log(node, node.localName)
      // 一共分六种情况
      // 1. empty <h>
      // 2. empty <p>
      if (node.className === editorElement().className ||
          node.className.startsWith('smeditor')) {
        if (size === '正文') {
          document.execCommand('insertHTML', false, `<p><span><br></span></p>`)
        } else {
          document.execCommand('insertHTML', false, `<${size}><span><br></span></${size}>`)
        }
        return false
      }
      // 3 h -> p
      // 4 h -> h+
      // 5 p -> h
      // 6 p -> p
      if (node.localName.startsWith('h') && size === '正文') {
        html = `<p>${node.innerHTML}</p>`
      } else if (node.localName.startsWith('h') && size.startsWith('H')) {
        html = `<${size}>${node.innerHTML}</${size}>`
      } else if (node.innerHTML.length > 0 && node.localName.startsWith('h') === false && size !== '正文') {
        html = `<${size}>${node.innerHTML}</${size}>`
      } else {
        html = `<p>${node.innerHTML}</p>`
      }
      restoreCursor(this)
      node.outerHTML = ''
      document.execCommand('insertHTML', false, html)
      // const range = document.createRange()
      // range.selectNodeContents(node)
      // range.collapse(false)
      // const selection = window.getSelection()
      // selection.removeAllRanges()
      // selection.addRange(range)
    },
    // 基本样式点击
    basicStyleClick(name) {
      execCmd(this, () => {
        document.execCommand(name, false, '')
        if (this.styles.indexOf(name) === -1) {
          this.styles.push(name)
        } else {
          remove(this.styles, name)
        }
      })
    },
    // 调色盘点击
    colorPickerClick(color) {
      // document.querySelector('.ql-color-label').style.fill = color
      execCmd(this, () => {
        document.execCommand('forecolor', false, color)
        this.closeAlert()
      })
    },
    // 点击插入图片
    insertImageClick(size, index) {
      this.closeAlert()
    },
    // 上传图片
    uploadImages(files) {
      Array.from(files).forEach(file => {
        const formData = new FormData()
        formData.append('file', file)
        app.uploadFile('upload_file', formData).then(data => {
          if (data.code === 0) {
            this.insertImageHtml(this.GLOBAL.serverIpAndPort + data.msg)
          }
        }).catch(response => {})
      })
    },
    insertImageHtml(url) {
      document.execCommand('insertHTML', false, `
              <br><div class="image-desc" style="text-align: center; color: #333;">
                <img class="uploaded-img" src=${url} max-width="100%" width="auto" height="auto">
                <br>
                <div class="image-caption" style="min-width: 20%; max-width: 80%; height: 35px; display: inline-block; padding: 10px 10px 0px 10px; margin: 0 auto; border-bottom: 1px solid #d9d9d9; font-size: 16px; color: #999; content: "";"></div>
              </div>`)
    },
    // 点击插入链接
    insertLinkClick() {
      this.closeAlert()
      this.insertLinkSection.text = window.getSelection().toString()
      getCursor(this)
      this.isInsertLinkShow = true
    },
    // 插入链接
    insertLink(url, title) {
      restoreCursor(this)
      const node = getSelectedNode()
      if (node.localName === 'a') {
        node.outerHTML = `<a href=${url} target="_blank">${title}</>`
        return false
      }
      document.execCommand('insertHTML', false, `<a href=${url} target="_blank">${title}</>`)
    },
    // 取消插入链接
    insertLinkCancel() {
      this.closeAlert()
      this.isInsertLinkShow = false
    },
    // 点击插入链接
    insertVideoClick() {
      this.closeAlert()
      setTimeout(() => {
        this.isInsertVideoShow = true
      }, 200)
      getCursor(this)
    },
    // 插入链接
    insertVideo(text) {
      restoreCursor(this)
      document.execCommand('insertHTML', false, text)
      this.closeAlert()
    },
    // 取消插入链接
    insertVideoCancel() {
      this.closeAlert()
    },
    // 插入一条线
    insertLine() {
      this.closeAlert()
      document.execCommand('insertHTML', false, `<p><hr></p>`)
    },
    // 插入代码块
    insertBlock() {
      this.closeAlert()
      console.log(editorElement().innerHTML)
      document.execCommand('insertHTML', false, `<pre><code><span><br><span></code></pre>`)
      console.log(editorElement().innerHTML)
    },
    // 插入引用
    insertQuote() {
      const node = getSelectedNode()
      // console.log(node)
      if (node.className === 'input-area') {
        return false
      }
      if (node.localName === 'blockquote' && node.parentNode.className !== 'input-area') {
        const str = node.innerHTML
        console.log(node.parentNode)
        node.parentNode.outerHTML = ''
        document.execCommand('insertHTML', false, `<br><p>${str}</p>`)
        this.insertEmptyP()
      } else if (node.className === 'blockquote') {
        const str = node.innerHTML
        console.log(str)
        document.execCommand('insertHTML', false, `<p>${str}</p>`)
        this.insertEmptyP()
        node.outerHTML = ''
      } else if (node.innerHTML.length > 0 &&
        node.className !== 'smeditor' &&
        node.className !== editorElement().className &&
        node.className !== 'blockquote') {
        document.execCommand('insertHTML', false, `<div class="blockquote"><blockquote style="color: #B2B2B2; padding-left: 15px; border-left: 5px solid #B2B2B2; margin-top: 0px; margin-bottom: 0px;">${node.innerHTML}</blockquote></div>`)
        node.outerHTML = ''
      } else {
        document.execCommand('insertHTML', false, `<div class="blockquote"><blockquote style="color: #B2B2B2; padding-left: 15px; border-left: 5px solid #B2B2B2; margin-top: 0px; margin-bottom: 0px;"><span><br></span></blockquote></div>`)
      }
    },
    // 插入 有序/无序 列表
    insertList(name) {
      this.closeAlert()
      document.execCommand(`insert${name}`, false, '')
    },
    // 插入 todo , 暂时不做
    insertCheck() {
      this.closeAlert()
      document.execCommand('insertHTML', false, `
        <ul class="unchecked-list"><li class="unchecked">&nbsp</li></ul>
      `)
      document.querySelectorAll('.unchecked-list').forEach(ul => {
        ul.childNodes.forEach(li => {
          li.onclick = function(event) {
            const name = event.target.className === 'unchecked' ? 'checked' : 'unchecked'
            event.target.className = name
            console.log(event.target.className)
          }
        })
      })
    },
    // 缩进+
    indent() {
      document.execCommand('indent', false, null)
    },
    // 缩进-
    outdent() {
      document.execCommand('outdent', false, null)
    },
    // 对齐
    align(name) {
      execCmd(this, () => {
        document.execCommand(`Justify${name}`)
      })
    },
    // 备份
    backupClick() {
      window.localStorage.setItem('smeditor', editorElement().innerHTML)
    },
    // 恢复
    restoreClick() {
      editorElement().innerHTML = window.localStorage.getItem('smeditor') || ''
    },
    // 预览
    previewClick() {
      window.localStorage.setItem('smeditorPreview', editorElement().innerHTML)
      const routeData = this.$router.resolve({
        path: '/preview'
      })
      // this.$router.push('/preview')
      window.open(routeData.href, '_blank')
    },
    // 关闭弹窗
    closeAlert() {
      setTimeout(() => {
        this.isFontSizePickerShow = false
        this.isInsertShow = false
        this.isColorPickerShow = false
        this.isInsertVideoShow = false
        this.isTitlePickerShow = false
      }, 200)
    },
    insertEmptyP() {
      document.execCommand('insertHTML', false, '<p><span></br></span></p>')
    },
    getImg(url) {
      console.log(111)
      console.log(url)
      return url
    }
  }

}

function addEvents(self) {
  editorElement().onfocus = function(event) {
    self.closeAlert()
  }
  // 回车事件
  editorElement().onkeypress = function(event) {
    const el = getSelectedNode()
    if (event.keyCode === 13 && isImageCaption(el)) {
      document.execCommand('removeFormat', false, '')
      this.innerHTML = this.innerHTML + '<p><br></p>'
      document.getSelection().collapse(this, this.childNodes.length - 1)
      return false
    }
    if (event.keyCode === 13 && el.className === 'blockquote' && el.lastChild.innerHTML === '<br>') {
      el.lastChild.innerHTML = ''
      document.execCommand('removeFormat', false, '')
      this.innerHTML = this.innerHTML + '<p></p>'
      document.getSelection().collapse(this, this.childNodes.length - 1)
      return false
    }

    if (event.keyCode === 13 && el.localName === 'pre' && el.lastChild.innerHTML === '<br>') {
      el.lastChild.innerHTML = ''
      document.execCommand('removeFormat', false, '')
      this.innerHTML = this.innerHTML + '<p><span><br></span></p>'
      document.getSelection().collapse(this, this.childNodes.length - 1)
      return false
    }
  }
  // 删除事件
  editorElement().onkeydown = function(event) {
    const el = getSelectedNode()
    if (event.keyCode === 8 && isImageDesc(el)) {
      el.innerHTML = '<p></p>'
      return false
    }
    if (el.innerHTML.length <= 1 &&
        event.keyCode === 8 &&
        isImageCaption(el)) {
      el.innerHTML = ''
      return false
    }
    if (getSelectedNode().innerHTML.length === 0) {
      document.execCommand('insertHTML', false, '<p><span></br></span></p>')
    }
  }
  editorElement().addEventListener('paste', function(event) {
    const items = (event.clipboardData || event.originalEvent.clipboardData).items
    for (const index in items) {
      const item = items[index]
      if (item.kind === 'file') {
        event.preventDefault()
        const blob = item.getAsFile()
        self.upload(blob, (url) => {
          self.insertImageHtml(url)
        })
      }
    }
  }, false)
}

function execCmd(self, callback) {
  getCursor(self)
  restoreCursor(self)
  callback()
}

function getCursor(self) {
  self.cursor = window.getSelection().getRangeAt(0)
}

// function delHtmlTag (str) {
//   return str.replace(/<[^>]+>/g, '')
// }

function isImageCaption(el) {
  return el.className === 'image-caption'
}

function isImageDesc(el) {
  return el.className === 'image-desc'
}

function getSelectedNode() {
  if (document.selection) {
    return document.selection.createRange().parentElement()
  } else {
    const selection = window.getSelection()
    if (selection.rangeCount > 0) {
      return selection.getRangeAt(0).startContainer.parentNode
    }
  }
}

function restoreCursor(self) {
  self.closeAlert()
  self.isInsertLinkShow = false
  editorElement().focus()
  const savedRange = self.cursor
  if (window.getSelection) {
    var s = window.getSelection()
    if (s.rangeCount > 0) {
      s.removeAllRanges()
    }
    s.addRange(savedRange)
  } else if (document.createRange) {
    window.getSelection().addRange(savedRange)
  } else if (document.selection) {
    savedRange.select()
  }
}
</script>
<style>
.smeditor {
  width: 100%;
  margin: 0 auto;
  position: relative;
  z-index: 2;
}

.smeditor .input-area {
  OVERFLOW:auto;
  outline: none;
  height: 700px;
  width: 100%;
  padding: 10px;
  text-align: left;
  box-shadow: 0 1px 6px #ccc;
  background-color: #ffffff;
  border-color: transparent;
  letter-spacing: 1.5px;
  color: rgb(44, 62, 80);
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.smeditor .buttons {
  position: -webkit-sticky;
  display: flex;
  justify-content: baseline;
  align-items: center;
  width: 100%;
  padding: 10px 0;
  background-color: rgba(240, 240, 240, 1);
  transition: position 0.3s;
}

.smeditor .isFixed {
  position: fixed;
  top: 0px;
  width: 70%;
}

.smeditor .buttons button {
  border: none;
  color: #000000;
  height: 27px;
  width: 30px;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  outline: none;
  cursor: pointer;
  background-color: transparent;
  border: 1px solid transparent;
  position: relative;
}

.smeditor .buttons button:hover {
  border-color: #BFBFBF;
}

.smeditor .buttonsActive {
  border: 1px solid #BFBFBF !important;
}

.smeditor svg {
  fill: #555;
  height: 100%;
  width: 100%;
}

.smeditor input {
  border: none;
  color: #333;
  font-size: 16px;
  text-align: center;
  width: 100%;
}

.smeditor img {
  max-width: 100%;
  width: auto;
  height: auto;
  vertical-align: middle;
  border: 0;
}

.smeditor p {
  padding: 2px 0;
  margin: 0px;
}

.smeditor svg {
  cursor: pointer;
}

.smeditor a {
  color: #87AA99;
  margin-right: 3px;
}

.smeditor pre {
  display: block;
  padding: 9.5px;
  margin: 0 0 10px;
  font-size: 13px;
  line-height: 1.42857143;
  color: #333;
  word-break: break-all;
  word-wrap: break-word;
  background-color: #f5f5f5;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.smeditor pre code {
  display: block;
  background-color: #f1f1f1;
  border-radius: 3px;
  padding: 3px 5px;
  margin: 0 3px;
}

.smeditor .blockquote {
  margin: 15px 0px;
}

.smeditor .backup,
.smeditor .restore,
.smeditor .preview {
  min-width: 40px !important;
}

.smeditor .backup:before,
.smeditor .restore:before,
.smeditor .preview:before {
  color: rgb(51, 51, 51);
  font-family: Helvetica, Tahoma, Arial, "Hiragino Sans GB", "Microsoft YaHei", SimSun, sans-serif;
  line-height: 28px;
  font-size: 12px;
  float: left;
  margin-left: 8px;
}

.smeditor .backup:before {
  content: "保存";
}

.smeditor .restore:before {
  content: "恢复";
}

.smeditor .preview:before {
  content: "预览";
}

.smeditor .select-words {
  position: fixed;
  right: calc(50% - 0px);
  margin-right: -100px;
  bottom: 60px;
  width: 200px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  color: #898989;
  opacity: 1;
  z-index: 1;
  background-color: #fff;
  border-radius: 1px;
  -webkit-border-radius: 1px;
  -moz-border-radius: 1px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-transition: all .2s ease-in;
  -moz-transition: all .2s ease-in;
  transition: all .2s ease-in;
  -webkit-box-shadow: 0 2px 8px hsla(0, 0%, 70%, .8);
  -moz-box-shadow: 0 2px 8px hsla(0, 0%, 70%, .8);
  -ms-box-shadow: 0 2px 8px hsla(0, 0%, 70%, .8);
  -o-box-shadow: 0 2px 8px hsla(0, 0%, 70%, .8);
  box-shadow: 0 2px 8px hsla(0, 0%, 70%, .8);
  transition-property: right;
  transition: all 0.3s;
  font-size: 14px;
}

.smeditor .font-size,
.smeditor .insert-options {
  min-width: 40px !important;
}

.smeditor .font-size,
.smeditor .set-font {
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 600;
}

.smeditor .font-size span,
.smeditor .set-font span {
  font-size: 14px;
  color: #333;
  bottom: -0.5px;
  font-family: 'Helvetica,Tahoma,Arial,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif';
  position: relative;
}

.smeditor .insert-options:before {
  content: "\63D2\5165";
  color: #333;
  font-family: 'Helvetica,Tahoma,Arial,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif';
  line-height: 28px;
  font-size: 12px;
  float: left;
  margin-left: 8px;
}

.smeditor .insert-quote img {
  width: 20px;
  margin-bottom: 1px;
}

.unchecked-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.unchecked:before {
  content: "\F402";
  padding-left: 3px;
  margin-right: 6px;
  cursor: pointer;
  box-sizing: border-box;
}

.checked:before {
  content: "\F402";
}

</style>
