const url = "https://075b1d4f.ngrok.io"
const serverUrl = url + "/wxaserver/module"
const upLoadFileUrl = url + "/wxaserver/upLoadFile"
// serverUrl: "http://localhost:8089/wxaserver",
const pt = {
  login_1: "login.login",
  topic_1: "topic.createTopic",
  topic_2: "topic.findTopicList",
  topic_3: "topic.onOffLikeTopic",
  topic_4: "topic.commentTopic"
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