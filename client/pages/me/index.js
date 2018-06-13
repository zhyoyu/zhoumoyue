// pages/me/index.js
var bsurl = require('../../utils/bsurl.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userBasicInfo = {
      openId: 'yc_1',
      userName: '言辰',
      sex: '男',
      city: '杭州'
    }
    var mod = 'login.login';
    var body = {
      memo: 'hello'
    }
    wx.request({
      url: bsurl,
      data: {
        ubi: userBasicInfo,
        mod: mod,
        body: body
      },
      success: function (res) {
        // that.setData({
        //   subcount: res.data
        // });
        console.log(res);
      }
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
  
  }
})