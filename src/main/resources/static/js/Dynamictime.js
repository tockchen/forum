$(function () {
    showTime(); //文档加载后。就开始显示时间
    var setTime = setInterval(showTime, 1000); //定义一个计时器。1s执行一次
});

//显示时间
function showTime() {
    //获取当前时间
    var data = new Date();
    //向年的span标签中添加数据
    $("#Year").text(data.getFullYear());
    //获取月份
    var m = data.getMonth();
    //应为下标为0所以要+1等于现在的月份
    //判断是否是月份是否小于10
    if (m + 1 < 10) {
        //月份小10就输出0+月份数
        $("#Month").text("0" + (data.getMonth() + 1));
    }
    else {
        //月份大于等于10就显示m值
        $("#Month").text((data.getMonth() + 1));
    }
    //获取日
    var m = data.getDate();

    //日如果小于10
    if (m < 10) {
        //如果小于10就显示0+日期
        $("#Date").text("0" + data.getDate());
    }
    else {
        //大于等于10显示m的值日
        $("#Date").text(data.getDate());
    }
    //获取时
    var m = data.getHours();
    if (m < 10) {
        //小于10显示0+时
        $("#Hours").text("0" + data.getHours());
    } else {
        //大于等于10显示m的值时
        $("#Hours").text(data.getHours());
    }
    //获取分
    var m = data.getMinutes();
    if (m < 10) {
        //小于10显示0 + 时输出分
        $("#Minutes").text("0" + data.getMinutes());
    }
    else {
        //大于等于10显示m的值分
        $("#Minutes").text(data.getMinutes());
    }

    //获取秒
    var m = data.getSeconds();
    if (m < 10) {
        //小于10显示0 + 时输出秒
        $("#Secondes").text("0" + data.getSeconds());
    }
    else {
        //大于等于10时就显示m的值秒
        $("#Secondes").text(data.getSeconds());
    }
    //声明是一个变量获取星期信息
    var week
    //获取星期几
    var w = data.getDay();
    switch (w) {
        case 0:
            week = "日";
            break;
        case 1:
            week = "一";
            break;
        case 2:
            week = "二";
            break;
        case 3:
            week = "三";
            break;
        case 4:
            week = "四";
            break;
        case 5:
            week = "五";
            break;
        case 6:
            week = "六";
            break;
    }
    $("#week").text(week);
}