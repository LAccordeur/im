/**
 * Created by 16481 on 2016/8/23.
 **/

var db = openDatabase("ThemeData","1.0","storage text",1024*100);



function themewhite() {
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundImage="url(img/background/5-120601095334.jpg)";
    var time = new Date().getTime();
    addData("test","url(img/background/5-120601095334.jpg)",time);
}

function themegrey(){
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundImage="url(img/background/5-150212233924.gif)";
    var time = new Date().getTime();
    addData("test","url(img/background/5-150212233924.gif)",time);

}

function themeblue() {
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundImage="url(img/background/5-120601095943-50.jpg)";
    var time = new Date().getTime();
    addData("test","url(img/background/5-120601095943-50.jpg)",time);
}

function themered(){
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundImage="url(img/background/5-121106095002.gif)";
    var time = new Date().getTime();
    addData("test","url(img/background/5-121106095002.gif)",time);
}

function init (){
    showData();
}

function showData(){
    db.transaction(function(tx){
        tx.executeSql("CREATE TABLE IF NOT EXISTS TmData(username TEXT,theme TEXT,time TEXT)",[]);
        tx.executeSql("SELECT * FROM TmData",[],function(tx,rs){

                 var i = rs.rows.length - 1;
            if(i>=0) {
                changetheme(rs.rows.item(i));
            }
        })
    })

}

function changetheme(row){
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundImage= row.theme;
}

function addData(username,theme,time){
    db.transaction(function(tx){
        tx.executeSql("INSERT INTO TmData VALUES(?,?,?)",[username,theme,time],function(tx,rs){
                alert("成功");
            },
            function (tx,error){
                alert(error.source+"::"+error.message);
            })
    })
}

function jump_index(){
    window.navigator("index.html");
}
var bounce = {
    x: 0, y: 0, w: 500, h: 300, dx: 0, dy: 5, interval: 100, win: null, timer: null,
    start: function () {
        this.x = (screen.width - this.w) / 2;
        this.y = (screen.height - bounce.h) / 2;
        bounce.win = window.open("more_theme.html", "", "left=" + this.x + ",top=" + this.y + ",width=" + this.w + ",height=" + this.h + ",status=yes");
        this.timer = setInterval(this.nextFrame, this.interval);
    },
    stop: function () {
        claearInterval(bounce.timer);
        if (!bounce.win.closed) this.win.close();
    },
    nextFrame: function () {
        if (this.win.closed) {
            claearInterval(bounce.timer);
            return;
        }
        if ((bounce.x + bounce.dx > (screen.availWidth - bounce.w)) || (bounce.x + bounce.dx < 0))
            bounce.dx = -bounce.dx;
        if ((bounce.y + bounce.dy > (screen.availHeight - bounce.h)) || (bounce.y + bounce.dy < 0))
            bounce.dy = -bounce.dy;
        bounce.x += bounce.dx;
        bounce.y += bounce.dy;

        bounce.win.moveTo(bounce.x, bounce.y);
        bounce.win.defaultStatus = "(" + bounce.x + "," + bounce.y + ")";
    }
}