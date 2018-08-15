//  const url = "http://154.8.138.76:8080"
const url = "http://localhost:8089/wxaserver"
const serverUrl = url + "/module"
const upLoadFileUrl = url + "/upLoadFile"
const pt = {
    login_1: "login.login",

    topic_1: "topic.createTopic",
    topic_2: "topic.findTopicList",
    topic_3: "topic.onOffLikeTopic",
    topic_4: "topic.commentTopic",

    activity_1: "activity.createActivity",
    activity_2: "activity.findActivityList",
    activity_3: "activity.joinActivity",
    activity_4: "activity.cancelActivity",
    activity_5: "activity.getActivityInfo",
    activity_6: "activity.deleteActivity",

    user_1:"user.getUserInfo"
}
// function requestServer(mod, body) {
//   wx.request({
//     url: serverUrl,
//     data: {
//       uid: wx.getStorageSync("openId"),
//       mod: mod,
//       body: body
//     },
//     success: function (res) {
//         return res
//     }
//   });
// }

module.exports = {
    serverUrl: serverUrl,
    upLoadFileUrl: upLoadFileUrl,
    pt: pt
}             