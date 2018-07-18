// pages/talkaround/index.js
// var indexData = require('../../utils/data_index.js');
var uPt = require("../../utils/protocol.js")

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
        image: '/images/topic/swiper1.jpg',
        title: "昨天"
      },
      {
        image: '/images/topic/swiper2.jpg',
        title: "今天"
      },
      {
        image: '/images/topic/swiper3.jpg',
        title: "明天"
      }
    ],
    topicList: [],
    like_icon: "../../images/topic/like.png",
    no_like_icon: "../../images/topic/no_like.png",
    move_height: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log("---------------1onLoad-------------------")
    var that = this
    var body = {
      beginIndex: 0
    }
    wx.request({
      url: uPt.serverUrl,
      data: {
        uid: wx.getStorageSync("openId"),
        mod: uPt.pt.topic_2,
        body: body
      },
      success: function (res) {
        that.setData({
          topicList: res.data.topicInfosList,
        })
      }
    })

    wx.getSystemInfo({
      success : function(res) {
        that.setData ({
          move_height : res.windowHeight
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    // console.log("---------------3onReady-------------------")
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("---------------2onShow-------------------")
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
    console.log("------------top刷新--------------")
    wx.showNavigationBarLoading()
    this.setData({
      topicList: []
    })
    this.refresh("up");
    setTimeout(function () { wx.hideNavigationBarLoading(); wx.stopPullDownRefresh(); }, 2000);
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    console.log("------------bottom刷新--------------")
    wx.showNavigationBarLoading();
    var that = this;
    setTimeout(function () { 
      wx.hideNavigationBarLoading()
      that.refresh("down")
      }, 1000)
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  refresh: function (dir) {
    var that = this
    var size = 0
    var topicLength = this.data.topicList.length

    wx.showToast({
      title: '刷新中',
      icon: 'loading',
      duration: 3000
    });
    var body = {
      beginIndex: topicLength
    }
    wx.request({
      url: uPt.serverUrl,
      data: {
        uid: wx.getStorageSync("openId"),
        mod: uPt.pt.topic_2,
        body: body
      },
      success: function (res) {
        if(res.data.topicInfosList.length > 0) {
        that.setData({
          topicList: that.data.topicList.concat(res.data.topicInfosList),
        });
        }
      }
    });
    setTimeout(function () {
      if (dir == "down" && topicLength == that.data.topicList.length) {
        wx.showToast({
          title: '我是有底线的',
          icon: 'none',
          duration: 2000
        })
      } else {
      wx.showToast({
        title: '刷新成功',
        icon: 'success',
        duration: 2000
      })
    }
    }, 3000)
  },

 /**
 * 创建话题
 */
  createTopic: function () {
    wx.navigateTo({
      url: 'create/index',
    })
  },

  /**
   * 点赞
   */
  like: function (e) {
    var that = this
    var idx = e.currentTarget.dataset.idx
    var topicId = e.currentTarget.dataset.topicId
    var like = this.data.topicList[idx].like == 0 ? 1 : 0

    var body = {
      topicId: topicId,
      onOffLike: like
    }
    wx.request({
      url: uPt.serverUrl,
      data: {
        uid: wx.getStorageSync("openId"),
        mod: uPt.pt.topic_3,
        body: body
      },
      success: function (res) {
        that.setData({
          ['topicList[' + idx + '].like']: like
        })
      }
    });
  },

/**
 * 评论
 */
  comment: function (e) {
    console.log("---------" + e + "评论---------")
  }
})