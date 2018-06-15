const app = getApp();
Page({
  data: {
    remind: '加载中',
    angle: 0,
    userInfo: {
      avatarUrl: 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJxdSrVq2KbypLn5KMlxVWM0JQCPcleLVJmY1mUG52n7Elffl0HWvrNryfbb44ly71y1riccHDyiaSg/132'
    }
  },
  goToIndex() {
    wx.switchTab({
      url: '../activity/index',
    });
  },
  onLoad() {
    // wx.setNavigationBarTitle({
    //   title: `狗蛋TV`
    // })
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