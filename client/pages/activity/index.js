//index.js
//获取应用实例
var uPt = require("../../utils/protocol.js")

Page({
    data: {
      leftActivitys: [],
      rightActivitys: [],
      ITEM_WIDTH: wx.getStorageSync("win_width") * 0.48,
      ITEM_HEIGHT: wx.getStorageSync("win_width") * 0.6,
      img_height_rate: 0.65,
      img_height:0,
      x: wx.getStorageSync("win_width") - 60,
      y: wx.getStorageSync("win_height") - 100,
      move_width: wx.getStorageSync("win_width"),
      move_height: wx.getStorageSync("win_height") - 40,
    },
    onLoad: function (options) {
      this.setData({
        img_height: this.data.ITEM_WIDTH * this.data.img_height_rate
      })
      var that = this
      var body = {
        dir: 2,
        activityId: 0
      }
      wx.request({
        url: uPt.serverUrl,
        data: {
          uid: wx.getStorageSync("openId"),
          mod: uPt.pt.activity_2,
          body: body
        },
        success: function (res) {
          var dataList = res.data.activityInfoList
          var dataLength = dataList.length
          if (dataLength > 0) {
              var leftLength = that.data.leftActivitys.length
              var rightLength = that.data.rightActivitys.length
              for (var i = 0; i < dataLength; i++) {
                if (leftLength > rightLength) {
                  that.data.rightActivitys = that.data.rightActivitys.concat(dataList[i])
                  rightLength++
                } else {
                  that.data.leftActivitys = that.data.leftActivitys.concat(dataList[i])
                  leftLength++
                }
              }
              that.setData({
                leftActivitys: that.data.leftActivitys,
                rightActivitys: that.data.rightActivitys
              });
          }
        }
      });
    },
    onReady: function () {
        // 页面渲染完成

    },
    onShow: function () {
        // 页面显示

    },
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭
    },

    //创建活动
    createActivity: function() {
      wx.navigateTo({
        url: 'create/index',
      })
    },

  /**
    * 页面相关事件处理函数--监听用户下拉动作 
    */
  onPullDownRefresh: function () {
    console.log("------------top刷新--------------")
    wx.showNavigationBarLoading()
    var activityId = this.getMaxActivityId()
    var that = this
    setTimeout(function () {
      that.refresh(1, activityId)
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
    var activityId = this.getMinActivityId()
    var that = this;
    setTimeout(function () {
      that.refresh(2, activityId)
      wx.hideNavigationBarLoading()
    }, 1000)
  },

  getMinActivityId: function() {
    var activityId = 0
    var leftLength = this.data.leftActivitys.length
    if (leftLength > 0) {
      var leftMinActivityId = this.data.leftActivitys[leftLength - 1].activityId
      activityId = leftMinActivityId
    }
    var rightLength = this.data.rightActivitys.length
    if (rightLength > 0) {
      var rightMinActivityId = this.data.rightActivitys[rightLength - 1].activityId
      if (rightMinActivityId < activityId) {
        activityId = rightMinActivityId
      }
    }
    return activityId
  },

  getMaxActivityId: function () {
    var activityId = 0
    var leftLength = this.data.leftActivitys.length
    if (leftLength > 0) {
      var leftMaxActivityId = this.data.leftActivitys[0].activityId
      activityId = leftMaxActivityId
    }
    var rightLength = this.data.rightActivitys.length
    if (rightLength > 0) {
      var rightMaxActivityId = this.data.rightActivitys[0].activityId
      if (rightMaxActivityId > activityId) {
        activityId = rightMaxActivityId
      }
    }
    return activityId
  },

  /**
    * 刷新实现
    */
  refresh: function (dir, activityId) {
    var that = this
    var size = 0
    var body = {
      dir: dir,
      activityId: activityId
    }
    wx.request({
      url: uPt.serverUrl,
      data: {
        uid: wx.getStorageSync("openId"),
        mod: uPt.pt.activity_2,
        body: body
      },
      success: function (res) {
        var dataList = res.data.activityInfoList
        var dataLength = dataList.length
        console.log(res.data)
        if (dataLength > 0) {
          var leftLength = that.data.leftActivitys.length
          var rightLength = that.data.rightActivitys.length
          if (dir == '1') {
            for (var i = 0; i < dataLength; i++) {
              if(leftLength >  rightLength) {
                that.data.rightActivitys = dataList[i].concat(that.data.rightActivitys)
                rightLength ++
              } else {
                that.data.leftActivitys = dataList[i].concat(that.data.leftActivitys)
                leftLength ++
              }
            }
            that.setData({
              leftActivitys: that.data.leftActivitys,
              rightActivitys: that.data.rightActivitys
            });
          } else {
            for (var i = 0; i < dataLength; i++) {
              if (leftLength > rightLength) {
                that.data.rightActivitys = that.data.rightActivitys.concat(dataList[i])
                rightLength ++
              } else {
                that.data.leftActivitys = that.data.leftActivitys.concat(dataList[i])
                leftLength ++
              }
            }
            that.setData({
              leftActivitys: that.data.leftActivitys,
              rightActivitys: that.data.rightActivitys
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
  },

  /**
 * 查看图片
 */
  previewImage: function (e) {
    var image = e.currentTarget.dataset.image
    var that = this
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: new Array(image) // 需要预览的图片http链接列表
    })
  },

  //活动详情
  getActivityInfo(e) {
    let activity = e.currentTarget.dataset.activity
    // var activityJson = JSON.stringify(activity)
    wx.navigateTo({
      // url: `info/index?activity=${activityJson}`
      url: `info/index?activityId=${activity.activityId}`
    })
  },
})
