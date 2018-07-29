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

    }
    // loadimg: function (e) {//图片加载完成执行
    //     var index = e.currentTarget.id;
    //     console.log(index)
    //     var oImageIndex = this.data.oImageIndex;
    //     var tempIndex = 0;
    //     for (var i = 0; i < oImageIndex.length; i++) {
    //         if (oImageIndex[i] == index) {
    //             tempIndex = i;
    //             break;
    //         }
    //     }
    //     var imgWidth = this.data.imgWidth;//图片设置的宽度
    //     var oImgW = e.detail.width;//图片原始宽度
    //     var scal = imgWidth / oImgW;//比例计算
    //     var oImgH = e.detail.height;//图片原始高度
    //     var _imgHeight = oImgH * scal;//自适应高度
    //     var images = this.data.images;
    //     images[index].height = _imgHeight;
    //     oImageIndex.splice(tempIndex, 1);
    //     this.setData({
    //         oImageIndex: oImageIndex,
    //         images: images
    //     })

    //     var oneimages = this.data.oneimages;
    //     var twoimages = this.data.twoimages;
    //     if (oImageIndex.length == 0) { //当加载完最后一张图片执行
    //         var oneImageH = 0;
    //         var twoImageH = 0;
    //         for (var i = 0; i < images.length; i++) {
    //             if (i > 0) { //第一张除外
    //                 if (oneImageH > twoImageH) {
    //                     twoImageH += images[i].height;
    //                     twoimages.push(images[i]);
    //                 } else {
    //                     oneImageH += images[i].height;
    //                     oneimages.push(images[i]);
    //                 }
    //             } else {
    //                 oneImageH += images[i].height;
    //                 oneimages.push(images[i])
    //             }
    //         }
    //     }

    //     this.setData({
    //         oneimages: oneimages,
    //         twoimages: twoimages
    //     })
    // }
})
