<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0050)http://localhost:8080/HouseRent/manage!list.action -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <TITLE>青鸟租房 - 用户管理</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">

    <META name=GENERATOR>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
    <DIV class=search>
        <LABEL class=ui-green><INPUT onclick='document.location="getHouseData"' value="返回" type=button name=search></LABEL>
        欢迎:${users.name}
    </DIV>
</DIV>
<DIV class="main wrap">
    <DIV id=houseArea>
        <TABLE class=house-list>
            <TBODY>
            <c:forEach items="${pageInfo.list}" var="h">
                <TR>
                    <TD class=house-thumb><SPAN><A href="showHouse?id=${h.id}" target="_blank"><img src="http://localhost:80/${h.path}" width="100" height="75"alt=""></A></SPAN></TD>
                    <TD>
                        <DL>
                            <DT>房子的标题:${h.title}</DT>
                            <DD>房子的区域的名字:${h.dname};房子的街道的名字${h.sname};房子的类型:${h.tname};房子的面积${h.floorage}平米<BR>联系方式：${h.contact}</DD>
                        </DL>
                        <LABEL class=ui-green><INPUT onclick='document.location="recoveryHouse?id=${h.id}"' value="恢复房子信息" type=button name=search></LABEL>
                        <LABEL class=ui-green><INPUT onclick='alert("不能删除，你能拿我怎么样☺")' value="删除" type=button name=search></LABEL>
                    </TD>
                </TR>

            </c:forEach>

            </TBODY>
        </TABLE>
    </DIV>
    <DIV class=pager>
        <UL>
        <c:forEach begin="1" end="${pageInfo.pages}" var="num">
                <LI class=current>
                    <A id=widget_338868862
                                     href="getHouseData?page=${num}"
                                     parseContent="true" showError="true" targets="houseArea"
                                     ajaxAfterValidation="false" validate="false"
                                     dojoType="struts:BindAnchor">${num}
                    </A>
                </LI>
        </c:forEach>
        </UL>

        <SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN></DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>

</HTML>
