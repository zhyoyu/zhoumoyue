// pages/me/index.js
var util = require('../../utils/util.js')
var uPt = require("../../utils/protocol.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userBasicInfo:{},
    // activityNum:0,
    // topicNum:0,
    concernUsers:[],
    fans:[],
    myActivity:[],
    activityDynamic:[],
    myTopic:[],
    topicDynamic:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let userInfo = wx.getStorageSync('userInfo')
    this.setData({
      userBasicInfo: userInfo
    })

    var that = this
    wx.request({
      url: uPt.serverUrl,
      data: {
        uid: wx.getStorageSync("openId"),
        mod: uPt.pt.user_1
      },
      success: function (res) {
        console.log(res.data)
        that.setData({
          concernUsers: res.data.concernUsers,
          fans: res.data.fans,
          myActivity: res.data.myActivity,
          activityDynamic: res.data.activityDynamic,
          myTopic: res.data.myTopic,
          topicDynamic: res.data.topicDynamic
        })
      }
    })
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

  activityDynamic: function() {
    wx.navigateTo({
      url: `activityDynamic/index`
    })
  },

  topicDynamic: function () {
    wx.navigateTo({
      url: `topicDynamic/index`
    })
  },

  myActivity: function() {
    wx.navigateTo({
      url: `myActivity/index`
    })
  },

  myTopic: function () {
    wx.navigateTo({
      url: `myTopic/index`
    })
  },

  concern: function() {
    wx.navigateTo({
      url: `concern/index`
    })
  },
  fans: function () {
    wx.navigateTo({
      url: `fans/index`
    })
  },
  // getUserInfo: function () {
  //   var that = this
  //   wx.getUserInfo({
  //     success: function (res) {
  //       console.log('用户信息', res.userInfo)
  //       that.globalData.userInfo = res.userInfo
  //     }
  //   })
  // },
})