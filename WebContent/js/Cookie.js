/**
 * Created by vic-sun on 2016/9/8.
 */
function Cookie (name) {
    this.$name = name;
    var allcookies = document.cookie;
    if (allcookies == "") return;

    /*  split() 方法用于把一个字符串分割成字符串数组。  */

    var cookies = allcookies.split(";");
    var cookie = null;
    for (var i = 0;i < cookies.length;i++){

        /*  substring()用于提取两个参数中间字符  */

        if (cookies[i].substring(0,name.length+1) == (name + "="))
        {
            cookie = cookies[i];
            break;
        }
    }
    if (cookie == null) return;
    var cookieval = cookie.substring(name.length+1);
    for (var i = 0;i < a.length; i++) {
        a[i] = a[i].split(":");
    }
    for (var i = 0; i < a.length; i++) {
        this[a[i][0]] = decodeURIComponent(a[i][1]);
    }
}
Cookie.prototype.store = function(daysToLive,path,domain,secure) {
    var cookieval = "";
    for (var prop in this) {
        if ((prop.charAt(0) == '$') || ((typeof  this[prop]) == 'function')) continue;
        if (cookieval != "") cookieval += '&';
        cookieval += prop + ':' + encodeURIComponent(this[prop]);
        /*  作用：可把字符串作为URI 组件进行编码  */

    }
    var cookie = this.$name + '=' + cookieval;
    if (daysToLive || daysToLive == 0) {
        cookie += "; max-age=" + (daysToLive*24*60*60);
    }
    if (path) cookie += "; path=" + path;
    if (domain) cookie += ";domain=" + domain;
    if (secure) cookie += "; secure";
    document.cookie = cookie;
}
Cookie.prototype.remove = function(path,domain,secure) {
    for (var prop in this) {
        if (prop.charAt(0) != '$' && typeof this[prop] != 'function')  // 从指定位置获取字符串字符
            delete this[prop];
    }
    this.store(0,path,domain,secure);
}
Cookie.enabled = function () {
    if (Cookie.enabled.cache != undefined) return Cookie.enabled.cache;
    document.cookie = "testcookie=test;max-age=10000";
    var cookie = document.cookie;
    if (cookie.indexOf("testcookie=test") == -1) {
        return Cookie.enabled.cache = false;
    }
    else {
        document.cookie = "testcookie=test;max-age=0";
        return Cookie.enabled.cache = true;
    }
}

var red_cookie = {
    git: function (name) {
        var allcookies = document.cookie;
        if (allcookies == "") return;
        else allcookies = allcookies.substring(name.length+1,allcookies.length+1);

        var cookies = allcookies.split("&");

        return cookies;
    },
    get_name: function (cookie_get) {
        var cookie_names = this.git(cookie_get)[0];
        var cookie_name = cookie_names.substring(5,cookie_names.length+1);
        return decodeURIComponent(cookie_name);
    },
    get_color: function (cookie_get) {
        var cookie_names = this.git(cookie_get)[1];
        var cookie_name = cookie_names.substring(6,cookie_names.length+1);
        return decodeURIComponent(cookie_name);
    }
}