// pages/talkaround/index.js
var indexData = require('../../utils/data_index.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    x: 900,
    y: 0,
    indicatorDots: true,
    circular: true,
    interval: 5000,
    autoPlay: true,
    images: [
      {
        image: '/images/talkaround/swiper1.jpg',
        title: "1"
      },
      {
        image: '/images/talkaround/swiper2.jpg',
        title: "2"
      },
      {
        image: '/images/talkaround/swiper3.jpg',
        title: "3"
      }
    ],
    talkList: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      talkList: indexData.index.data
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

/**
 * 滚到顶部
 */
  upper : function() {

  },

  /**
   * 滚到底部
   */
  lower : function() {
    
  },

  /**
   * 进行留言
   */
  say : function() {
    wx.navigateTo({
      url: 'create/index',
    })
    console.log("创建留言")
  }
})