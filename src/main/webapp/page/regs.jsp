<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<script language="JavaScript" type="text/javascript" src="../scripts/jquery-1.8.3.js"></script>
<script language="javascript">
    $(function () {//入口函数
        $("#checkBut").click(function () {
            var name = $("#inputname").val();
            $.post("checkUsername", {"name": name}, function (data) {
                if (data.result) {
                    $("#notice").html("此用户名可以使用").css("color", "green");
                } else {
                    $("#notice").html("此用户名不可以使用").css("color", "red");
                }
            }, "json")
        })
    })
</script>
<script language="javascript">
    function check() {
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        if (password == repassword) {
            return true;
        } else {
            alert("两次输入密码不一致，请重新输入");
            return false;
        }
    }
</script>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新用户注册</DT>
            <DD class=past>填写个人信息</DD>
        </DL>
        <DIV class=box>
            <FORM action="regUsers" method="post" name="form1" onsubmit="return check()">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD><INPUT class=text id="inputname" type=text name=name><span id="notice"></span></span>
                                <input type="button" id="checkBut" value="检查用户是否存在"></TD>
                        </TR>
                        <TR>
                            <TD class=field>密　　码：</TD>
                            <TD><INPUT class=text type=password name=password id="password"></TD>
                        </TR>
                        <TR>
                            <TD class=field>确认密码：</TD>
                            <TD><INPUT class=text type=password name=repassword id="repassword"></TD>
                        </TR>
                        <TR>
                            <TD class=field>电　　话：</TD>
                            <TD><INPUT class=text type=text name=telephone></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT value=立即注册 type=submit>
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
</HTML>
