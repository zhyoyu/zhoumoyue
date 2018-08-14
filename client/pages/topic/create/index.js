// pages/talkaround/create/index.js

var uPt = require('../../../utils/protocol.js');
var util = require('../../../utils/util.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    images: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
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
   * 上传图片
   */
  chooseImage: function() {
    if(this.data.images.length >= 9) {
      wx.showToast({
        title: '最多只能选择9张图片',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    var that = this
    console.log("imageSize:" + this.data.images.length)
    wx.chooseImage({
      success: function(res) {
        var imageLength = that.data.images.length
        var addLength = res.tempFilePaths.length
        if (imageLength + addLength > 9) {
          wx.showToast({
            title: '最多只能选择9张图片',
            icon: 'none',
            duration: 2000
          })
        }
        var addIamgeArray = res.tempFilePaths.slice(0, 9 - imageLength)
        console.log(res.tempFilePaths)
        that.setData({
          images: that.data.images.concat(addIamgeArray)
        })
      },
    })
  },

  /**
   * 发表
   */
  bindFormSubmit: function(e) {
    var content = e.detail.value.content
    if (util.isEmpty(content) && this.data.images.length == 0) {
      wx.showToast({
        title: '话题不能为空',
        icon: 'none',
        duration: 2000
      })
      return
    }
    var body = {
      content: content,
      images: ''
    }
    if(this.data.images.length > 0) {
      this.uploadimage(body, this.data.images, 0)
    } else {
      wx.request({
        url: uPt.serverUrl,
        data: {
          uid: wx.getStorageSync("openId"),
          mod: uPt.pt.topic_1,
          body: body
        },
        success: function (res) {
          wx.reLaunch({
            url: '../index',
          });
        }
      });
    }
  },

  uploadimage: function (body, images, i) {
    var that = this
    wx.uploadFile({
      url: uPt.upLoadFileUrl,
      filePath: images[i],
      name: 'image' + (i + 1),
      formData: {
        'type': 1
      },
      success: function (res) {
        var data = res.data
        //do something
        if(data.length > 0) {
          body.images = body.images + data + ";"
        }
      },
      fail: function(res) {
        console.log('')
      },
      complete: function() {
        i++;
        if(i == images.length) {
          console.log('success')
          wx.request({
            url: uPt.serverUrl,
            data: {
              uid: wx.getStorageSync("openId"),
              mod: uPt.pt.topic_1,
              body: body
            },
            success: function (res) {
              wx.reLaunch({
                url: '../index',
              });
            }
          });
        } else {
          console.log('i:' + i)
          that.uploadimage(body, images, i)
        }
      }
    })
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
})