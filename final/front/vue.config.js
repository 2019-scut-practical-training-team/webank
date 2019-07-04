module.exports = {
  devServer: {
    public: "192.168.1.126:8080",
    disableHostCheck: true,
    // 代理API服务器
    proxy: "http://110.64.72.19:8080"
  }
};
