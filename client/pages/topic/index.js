// pages/talkaround/index.js
// var indexData = require('../../utils/data_index.js');
var uPt = require("../../utils/protocol.js")

Page({
    /**
     * 页面的初始数据
     */
    data: {
        x: wx.getStorageSync("win_width") - 60,
        y: wx.getStorageSync("win_height") - 100,
        move_width: wx.getStorageSync("win_width"),
        move_height: wx.getStorageSync("win_height") - 40,
        indicatorDots: true,
        circular: true,
        interval: 5000,
        autoPlay: true,
        comment: false,
        authorUid: 0,
        authorNickName: 0,
        topicid: 0,
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
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        // console.log("---------------1onLoad-------------------")
        var that = this
        var body = {
          dir: 2,
          topicId: 0
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
      var topicId = 0
      var topicListLength = this.data.topicList.length
      if (topicListLength > 0) {
        topicId = this.data.topicList[0].topicId
      }
      var that = this
      setTimeout(function () {
        that.refresh(1, topicId)
        wx.hideNavigationBarLoading()
        wx.stopPullDownRefresh()
      }, 1000);
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {
        console.log("------------bottom刷新--------------")
        wx.showNavigationBarLoading();
        var topicId = 0
        var topicListLength = this.data.topicList.length
        if (topicListLength > 0) {
          topicId = this.data.topicList[topicListLength - 1].topicId
        }
        var that = this;
        setTimeout(function () {
          that.refresh(2, topicId)
          wx.hideNavigationBarLoading()
        }, 1000)
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },

    /**
     * 刷新实现
     */
    refresh: function (dir, topicId) {
        var that = this
        var size = 0
        var body = {
            dir: dir,
            topicId: topicId
        }
      // wx.showToast({
      //   title: '刷新中',
      //   icon: 'loading',
      //   duration: 3000
      // });
        wx.request({
            url: uPt.serverUrl,
            data: {
              uid: wx.getStorageSync("openId"),
              mod: uPt.pt.topic_2,
              body: body
            },
            success: function (res) {
                if (res.data.topicInfosList.length > 0) {
                  if(dir == '1') {
                    that.setData({
                      topicList: res.data.topicInfosList.concat(that.data.topicList),
                    });
                  } else {
                    that.setData({
                      topicList: that.data.topicList.concat(res.data.topicInfosList),
                    });
                  }
                  wx.showToast({
                    title: '刷新成功',
                    icon: 'success',
                    duration: 1500
                  })
                } else {
                  if (dir == '2') {
                    wx.showToast({
                    title: '我是有底线的',
                    icon: 'none',
                    duration: 1500
                    })
                  } else {
                    wx.showToast({
                      title: '刷新成功',
                      icon: 'success',
                      duration: 1500
                    })
                  }
                }
            }
        });
        // setTimeout(function () {
        //     if (dir == "down" && topicLength == that.data.topicList.length) {
        //         wx.showToast({
        //             title: '我是有底线的',
        //             icon: 'none',
        //             duration: 2000
        //         })
        //     } else {
        //         wx.showToast({
        //             title: '刷新成功',
        //             icon: 'success',
        //             duration: 2000
        //         })
        //     }
        // }, 3000)
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
        // var that = this
        var authoruid = e.currentTarget.dataset.authoruid
        var authornickname = e.currentTarget.dataset.authornickname
        console.log(authornickname);
        this.setData({
            comment: this.data.comment == true ? false : true,
            authorUid: authoruid,
            authorNickName: authornickname,
            topicId: e.currentTarget.dataset.topicid
        })
        console.log("---------" + e + "评论---------")
    },
    /**
     * 发送评论
     */
    bindFormSubmit: function (e) {
        // var that = this
        var content = e.detail.value.content
        var idx = e.currentTarget.dataset.idx
        console.log("评论：" + content)
        //var authoruid = e.currentTarget.dataset.authoruid
        //var authornickname = e.currentTarget.dataset.authornickname
        var replyid = wx.getStorageSync("openId")
        var userInfo = wx.getStorageSync("userInfo")
        this.setData({
            comment: false
        })
        var body = {
            commentInfo: {
                topicId: this.data.topicId,
                commentUserId: replyid,
                commentUserName: userInfo.nickName,
                content: content,
                replyUserId: this.data.authorUid,
                replyUserName: this.data.authorNickName
            }
        }
        console.log(body);
        wx.request({
            url: uPt.serverUrl,
            data: {
                uid: wx.getStorageSync("openId"),
                mod: uPt.pt.topic_4,
                body: body
            },
            success: function (res) {
              if(res.ok == 1) {

              }
            }
        });
        console.log("---------" + e + "评论---------")
    },

    /**
     * 查看图片
     */
  previewImage: function (e) {
    var images = e.currentTarget.dataset.images
    var imageUrl = e.currentTarget.dataset.imageUrl
    var that = this
    wx.previewImage({
      current: imageUrl, // 当前显示图片的http链接
      urls: images // 需要预览的图片http链接列表
    })
  },
})
