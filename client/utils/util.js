// module.exports = "http://d7b4a055.ngrok.io/v1/"

const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function sayHello() {
  console.log('this is util.js say hello!')
}

function requestServer() {
}

function isEmpty(val) {
  if (val == null || val == "" || val == undefined) {
    return true;
  }
  return false;
}

function isNum(val) {
  if (val === "" || val == null || val == undefined) {
    return false;
  }
  return !isNaN(val)
}   

module.exports = {
  formatTime: formatTime,
  sayHello: sayHello,
  isEmpty: isEmpty,
  isNum: isNum
}
