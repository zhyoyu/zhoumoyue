const serverUrl = "https://be360e88.ngrok.io/wxaserver"
// serverUrl: "http://localhost:8089/wxaserver",
const pt = {
  login_1: "login.login",
  topic_1: "topic.commentTopic",
  topic_2: "topic.findTopicList",
  topic_3: "topic.onOffLikeTopic"
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
  pt: pt
}             