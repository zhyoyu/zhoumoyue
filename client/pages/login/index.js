const app = getApp();
var uPt = require("../../utils/protocol.js")

Page({
  data: {
    remind: '加载中',
    angle: 0,
    userInfo: {
      avatarUrl: '../../images/logo.jpg'
    }
  },
  login() {
    let userInfo = wx.getStorageSync('userInfo')
    if(!userInfo) {
      let dialogComponent = this.selectComponent('.wxc-dialog');
      dialogComponent && dialogComponent.show();
    } else {
      // wx.switchTab({
      //   url: '../talkaround/index',
      // }); 
      var openId = wx.getStorageSync('openId')
      var body = {
        openId: openId,
        nickName: userInfo.nickName,
        sex: userInfo.gender,
        city: userInfo.city,
        iconUrl: userInfo.avatarUrl
      }
      wx.request({
        url: uPt.serverUrl,
        data: {
          uid: openId,
          mod: uPt.pt.login_1,
          body: body
        },
        success: function (res) {
          wx.switchTab({
            url: '../topic/index',
          });
        }
      });
   
    }
  },
  onLoad() {
  },
  onReady() {
    setTimeout(() => {
      this.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange((res) => {
      let angle = -(res.x * 30).toFixed(1);
      if (angle > 14) { angle = 14; }
      else if (angle < -14) { angle = -14; }
      if (this.data.angle !== angle) {
        this.setData({
          angle: angle
        });
      }
    });
  },
  onShow() {
    let userInfo = wx.getStorageSync('userInfo')
    let dialogComponent = this.selectComponent('.wxc-dialog');
    if (!userInfo) {
      dialogComponent && dialogComponent.show();
    } else {
      this.setData({
        userInfo: userInfo
      })
      dialogComponent && dialogComponent.hide();
    }
  },
  onConfirm(e) { // 点击允许
    let dialogComponent = this.selectComponent('.wxc-dialog');
    dialogComponent && dialogComponent.hide();
    let userInfo = JSON.parse(e.detail.detail.rawData)
    if (!userInfo) {
      return;
    }
    this.setData({
      userInfo: userInfo
    })
    wx.setStorageSync('userInfo', userInfo)
  },
  onCancel() { // 点击拒绝
    let dialogComponent = this.selectComponent('.wxc-dialog');
    dialogComponent && dialogComponent.hide();
  }
});