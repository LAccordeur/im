/**
 * Created by 16481 on 2016/8/23.
 **/

var db = openDatabase("ThemeData","1.0","storage text",1024*100);

function themewhite() {
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundColor="#F0FFFF";
    var time = new Date().getTime();
    addData("test","#F0FFFF",time);
}

function themegrey(){
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundColor="#E5E5E5";
    var time = new Date().getTime();
    addData("test","#E5E5E5",time);
}

function themeblue() {
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundColor="#6495ED";
    var time = new Date().getTime();
    addData("test","#6495ED",time);
}

function themered(){
    var mychar=document.getElementById("BODY");
    mychar.style.backgroundColor="#CD8162";
    var time = new Date().getTime();
    addData("test","#CD8162",time);
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
    mychar.style.backgroundColor= row.theme;
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