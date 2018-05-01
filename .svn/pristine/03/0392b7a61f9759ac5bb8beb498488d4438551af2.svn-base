Date.prototype.addDays = function(n) {
    // / <summary>
    // / 添加日期天数
    // / </summary>
    return new Date(this - 0 + n * 86400000);
}

Date.prototype.getOffDays = function(end) {
    // / <summary>
    // / 得到时间戳相减 得到以毫秒为单位的差
    // / </summary>

    var mm = Math.abs(end.getTime() - this.getTime());

    // 单位转换为天并返回

    return (mm / 3600000 / 24);

};

Date.prototype.getShortDate = function() {
    // / <summary>
    // / 获取日期
    // / </summary>
    var date = new Date(this.setSeconds(0));
    date = new Date(this.setMinutes(0));
    date = new Date(this.setHours(0));

    return date;
};

Date.prototype.toDateTimeString = function(formart) {
    // / <summary>
    // / 将日期转成指定格式的字符串
    // / 默认为 yyyy-MM-dd hh:mm:ss
    // / </summary>
    var yyyy = this.getFullYear();
    var MM = this.getMonth() + 1;
    MM = MM < 10 ? '0' + MM : MM;

    var dd = this.getDate();
    dd = dd < 10 ? '0' + dd : dd;

    var hh = this.getHours();
    hh = hh < 10 ? '0' + hh : hh;

    var mm = this.getMinutes();
    mm = mm < 10 ? '0' + mm : mm;

    var ss = this.getSeconds();
    ss = ss < 10 ? '0' + ss : ss;

    if (formart)
        return formart.replace("yyyy", yyyy).replace("MM", MM)
            .replace("dd", dd).replace("hh", hh).replace("mm", mm).replace(
                "ss", ss);
    else
        return yyyy + '-' + MM + '-' + dd + ' ' + hh + ':' + mm + ':' + ss
};

Date.prototype.toShortDateString = function(formart) {
    // / <summary>
    // / 将日期转成短日期格式的字符串
    // / 默认为 yyyy-MM-dd
    // / </summary>
    var yyyy = this.getFullYear();
    var MM = this.getMonth() + 1;
    MM = MM < 10 ? '0' + MM : MM;

    var dd = this.getDate();
    dd = dd < 10 ? '0' + dd : dd;

    if (formart)
        return formart.replace("yyyy", yyyy).replace("MM", MM)
            .replace("dd", dd);
    else
        return yyyy + '-' + MM + '-' + dd;
}

String.prototype.format = function() {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g, function(m, i) {
        return args[i];
    });
}
String.prototype.toDate = function() {
    return new Date(Date.parse(this.replace(/-/g, "/")));
}