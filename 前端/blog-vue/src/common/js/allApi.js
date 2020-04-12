const appApi = {
  // 用户管理
  logIn: '/sso/login', // 登入
  logOut: '/sso/logout', // 登出
  upload_file: '/sso/file/uploadImg',

  listArticles: '/api/article/listArticles',
  getArticleById: '/api/article/getArticleById',
  insertArticle: '/api/article/insertArticle',
  editArticle: '/api/article/editArticle',
  deleteArticleById: '/api/article/deleteArticleById',

  // category
  listCategory: '/api/category/listCategory',
  getCategoryById: '/api/category/getCategoryById',
  insertCategory: '/api/category/insertCategory',
  editCategory: '/api/category/editCategory',
  deleteCategoryById: '/api/category/deleteCategoryById',

  // statistics
  statisticsArticleCategory: '/api/statistics/statisticsArticleCategory',
  statisticsArticleByDate: '/api/statistics/statisticsArticleByDate',
  statisticsPopularArticles: '/api/statistics/statisticsPopularArticles'
}

export default appApi
