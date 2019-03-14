<%--
  Created by IntelliJ IDEA.
  User: bukbuk
  Date: 2017/12/7
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>登录</title>
    <%@include file="common/head.jsp" %>
    <link href="<%=basePath%>css/signin.css" rel="stylesheet">
    <style>
        a{text-decoration:none;}
        .clr{clear:both;}
        *{margin:0;padding:0;}
        .verify{border:1px solid #ccc;padding:10px;width:300px;position:relative;box-shadow:0 0 1px 1px #ccc;background:#337ab7;}
        .verify .verify-head{overflow:hidden;}
        .verify .label{font-size:12px;float:left;height:40px;line-height:40px}
        .verify .img-txt{display:inline-block;margin:0;margin-left:10px;float:right;padding:10px;width:135px;text-align:center;}
        .verify .verify-img{margin:10px 0;position:relative;height:230px;overflow:hidden;}
        .verify .imgsBg{position:absolute;min-width:10px;height:100%;top:0;}
        .verify .verify-foot{}
        .verify .verify-foot .btn-txt{color:#666;font-size:12px;float:left;height:30px;line-height:30px}
        .verify .verify-foot .btn-sub{float:right;background:#f95700;width:120px;height:30px;line-height:30px;text-align:center;border-radius:3px;color:#fff;display:inline-block;cursor:pointer;font-size:16px;}
        .verify .verify-foot .btn-gray{background:#999 !important;cursor:default;}
        .verify .arrTxt{position:absolute;font-size:22px;}
        .verify .verify-coor{position:absolute;font-size:16px;color:#fff;border:1px solid #fff;width:15px;height:15px;text-align:center;line-height:15px;border-radius:50%}
    </style>
</head>
<body>

<div class="container">

    <form class="form-signin" action="/select/session" method="post">
        <h2 class="form-signin-heading">请登录</h2>
        <label class="sr-only" for="inputUsername">Username</label>
        <input name="username" class="form-control" id="inputUsername"  autofocus="" required="" type="username" placeholder="Username">
        <label class="sr-only" for="inputPassword">Password</label>
        <input name = "password" class="form-control" id="inputPassword" required="" type="password" placeholder="Password">
        <div class="verify">
            <div class="verify-head">
                <span class="label">请顺序点击大图中的文字</span>
                <p class="img-txt"></p>
            </div>
            <div class="verify-img">

            </div>
            <div class="verify-foot">
                <a href="javascript:;" class="btn-txt">点击刷新图片</a>
                <a href="javascript:;" class="btn-sub btn-gray">验证</a>
            </div>
            <div class="clr"></div>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="checkbutton" disabled="true">登录</button>
    </form>
    <strong>${message}</strong>
</div>

<script>
    window.onload = function(){
        //verify();
        var aa = new verfily();

    }


    function strToDom(str){
        var ele = document.createElement("div");
        ele.innerHTML = str;
        return ele.childNodes;
    }

    function randDom(arr){
        if(Array.isArray(arr)) return parseInt(Math.random()*arr.length);
        else if(typeof arr == "number") return parseInt(Math.random()*arr);
    }

    function verfily(){
        //参数
        var defaults = {
            verImg : document.querySelector('.verify-img'),
            arrTxt : ["全雪峰", "崔恒宇", "龚红杰", "黄钰凯"],
            arrImg : ["1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg"],
            imgUrl : "images/",
            oTxt : document.querySelector(".img-txt"),
            oImg : document.querySelector(".verify-img"),
            refreshBtn : document.querySelector(".btn-txt"),
            check : document.querySelector(".btn-sub"),
            num:0,
        }
        init();

        function init(){
            //返回随机的文本及背景图
            txt = defaults.arrTxt[randDom(defaults.arrTxt)],
                img = defaults.arrImg[randDom(defaults.arrImg)];
            //文本及背景显示在DOM上
            defaults.oTxt.innerHTML = txt;
            defaults.oTxt.style.background = "url("+defaults.imgUrl+img+") center center no-repeat";
            defaults.oImg.style.background = "url("+defaults.imgUrl+img+") 100% 100% no-repeat";
            //文本随机显示在背景图上
            /*
             1.先把位置随机好
             2.然后判断左边距，右边区，如果有重叠适当增或减像素
             3.追加要显示的文本DOM
            */
            var arr = txt.split(""), left=[],top = [], temp= "";
            for(var i=0; i<arr.length; i++){
                left.push(randDom(defaults.verImg.clientWidth - 22));
                top.push(randDom(defaults.verImg.clientHeight - 40));
            }
            for(var i=0; i<arr.length; i++){
                for(var j=1; j<arr.length; j++ ){
                    if( Math.abs(left[i]-left[j]) < 5 ) left[j] = left[j]+15 < defaults.verImg.clientWidth-22 ? left[j]+15 : left[j] -15;
                    if( Math.abs(top[i]-top[j]) < 5 ) top[j] = top[j]+15 < defaults.verImg.clientHeight-22 ? top[j]+15 : top[j] -15;
                }
            }
            for(var i=0; i<arr.length; i++){
                temp += "<span class='arrTxt' style='transform:rotate(" + randDom(361) + "deg);left:" + left[i] +"px;top:" +top[i]+ "px;'>" +txt[i]+ "</span>";
                defaults.verImg.appendChild(strToDom(temp)[i])
            }
        }

        function reset(){
            //多余的DOM清除
            while(defaults.verImg.hasChildNodes()) //当div下还存在子节点时 循环继续
            {
                defaults.verImg.removeChild(defaults.verImg.firstChild);
            }
            defaults.num = 0;
            defaults.check.className +=" btn-gray";
            defaults.check.innerHTML = "验证";
            init();
        }

        //Dom绑定事件
        defaults.verImg.addEventListener("click", function(e){
            defaults.num++;
            if(defaults.num> txt.length){
                return false;
            }
            var e = window.event || e,
                x = e.pageX - this.offsetLeft -531.5,
                y = e.pageY - this.offsetTop -225,
                node = "<b class='verify-coor' style='top:" +y+ "px;left:"+x+"px'>"+defaults.num+"</b>";
            defaults.verImg.appendChild(strToDom(node)[0]);
            defaults.check.className = "btn-sub";
        }, false)

        defaults.check.addEventListener("click", function(){
            if(this.innerHTML == "验证成功" || document.querySelectorAll(".verify-coor").length<1) return;
            var b = document.querySelectorAll(".verify-coor"), t = document.querySelectorAll(".arrTxt");
            if(b.length< t.length) {defaults.refreshBtn.innerHTML = "验证失败，刷新重新验证";return;}
            var flag = 0;
            for(var i=0; i<t.length; i++){
                if( Math.abs(parseInt(b[i].style.left) - parseInt(t[i].style.left)) >= 12 || Math.abs(parseInt(b[i].style.top) - parseInt(t[i].style.top)) >= 12){
                    defaults.refreshBtn.innerHTML = "验证失败，刷新重新验证";
                    flag++;
                }
            }
            if(!flag) {
                defaults.check.innerHTML = "验证成功";
                defaults.refreshBtn.innerHTML = "刷新重新验证";
                document.getElementById("checkbutton").disabled=false;
            }
        }, false);

        defaults.refreshBtn.addEventListener("click", function(){
            reset();
            document.getElementById("checkbutton").disabled=true;
        }, false);

    }

</script>
<script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
