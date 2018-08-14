//index.js
//获取应用实例
const app = getApp()

Page({
    data: {
      leftItems: [{ pic: "../../images/activity/1.jpg", height: 0 }, { pic: "../../images/activity/2.jpg", height: 0 }, { pic: "../../images/activity/3.jpg", height: 0 }, { pic: "../../images/activity/4.jpg", height: 0 }, { pic: "../../images/activity/5.jpg", height: 0 }, { pic: "../../images/activity/6.jpg", height: 0 }, { pic: "../../images/activity/7.jpg", height: 0 }],
      rightItems: [{ pic: "../../images/activity/11.jpg", height: 0 }, { pic: "../../images/activity/14.jpg", height: 0 }, { pic: "../../images/activity/8.jpg", height: 0 }, { pic: "../../images/activity/9.jpg", height: 0 }, { pic: "../../images/activity/10.jpg", height: 0 }, { pic: "../../images/activity/13.jpg", height: 0 }, { pic: "../../images/activity/12.jpg", height: 0 }],
      ITEM_WIDTH: wx.getStorageSync("win_width") * 0.48,
      ITEM_HEIGHT: wx.getStorageSync("win_width") * 0.6,
      img_height_rate: 0.65,
      img_height:0,
      x: wx.getStorageSync("win_width") - 60,
      y: wx.getStorageSync("win_height") - 100,
    },
    onLoad: function (options) {
      this.setData({
        img_height: this.data.ITEM_WIDTH * this.data.img_height_rate
      })
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

    //加载活动
    loadimg: function (e) {//图片加载完成执行
       
    }
})
