const appApi = {
  // 用户管理
  logIn: '/login', // 登入
  logOut: '/logout', // 登出
  upload_file: '/file/uploadImg',

  listArticles: '/article/listArticles',
  getArticleById: '/article/getArticleById',
  insertArticle: '/article/insertArticle',
  editArticle: '/article/editArticle',
  deleteArticleById: '/article/deleteArticleById',

  // category
  listCategory: '/category/listCategory',
  getCategoryById: '/category/getCategoryById',
  insertCategory: '/category/insertCategory',
  editCategory: '/category/editCategory',
  deleteCategoryById: '/category/deleteCategoryById',

  // statistics
  statisticsArticleCategory: '/statistics/statisticsArticleCategory',
  statisticsArticleByDate: '/statistics/statisticsArticleByDate',
  statisticsPopularArticles: '/statistics/statisticsPopularArticles'
}

export default appApi
