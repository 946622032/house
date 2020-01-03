<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="../css/style.css">
<META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../scripts/jquery-1.8.3.js"></script>
  <script>
    $(function () {
      $.post("getTypeAll",null,function (data) {
        //遍历所有街道信息
        for (var i = 0; i <data.length ; i++) {
          var optionnode = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
          //把街道信息追加到select里面
          $("#type_id").append(optionnode);

          //设置类型的选中项
          $("#type_id").val(${condition.tid})
        }
      },"json")

      $.post("getDistrictAll",null,function (data) {
        //遍历所有街道信息
        for (var i = 0; i <data.length ; i++) {
          var optionnode = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
          //把街道信息追加到select里面
          $("#district_id").append(optionnode);
          //1.选中区域的选中项
          $("#district_id").val(${condition.did})

          var did = $("#district_id").val();
          $("#street_id>option:gt(0)").remove();
          $.post("getStreetByDid",{"did":did},function (data) {
            //遍历所有街道信息
            for (var i = 0; i <data.length ; i++) {
              var optionnode = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
              //把街道信息追加到select里面
              $("#street_id").append(optionnode);
              //给街道回显数据
              locadStreet();
            }
          },"json")
        }
      },"json")

      $("#district_id").change(function () {
        locadStreet();
      })
    })

    function locadStreet() {
      var did = $("#district_id").val();
      $("#street_id>option:gt(0)").remove();
      $.post("getStreetByDid",{"did":did},function (data) {
        //遍历所有街道信息
        for (var i = 0; i <data.length ; i++) {
          var optionnode = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
          //把街道信息追加到select里面
          $("#street_id").append(optionnode);
          $("#street_id").val(${condition.sid})

        }
      },"json");
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action=getBroswerHouse>
    <input type="hidden" name="page" value="1" id="savePage">
    标题:<input type="text" name="title" id="" value="${condition.title}">
    区域: <select name="did" id="district_id">
             <option value="">请选择</option>
          </select>
    街道: <select name="sid" id="street_id">
            <option value="">请选择</option>
          </select>
    类型: <select name="tid" id="type_id">
            <option value="">请选择</option>
          </select>
    价格: <input type="text" name="startPrice" id="" size="10" value="${condition.startPrice}">-
    <input type="text" name="endPrice" id="" size="10" value="${condition.endPrice}">
    <input type="submit" value="搜索">
  </FORM>
</DL>
</DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>
  <c:forEach items="${pageInfo.list}" var="house">
  <TR>
    <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="../images/thumb_house.gif" width="100" height="75" alt=""></a></span></TD>
    <TD>
      <DL>
        <DT><A href="details.jsp" target="_blank">${house.title}</A></DT>
        <DD>${house.dname}--${house.sname},${house.floorage}平米<BR>联系方式：${house.contact} </DD></DL></TD>
    <TD class=house-type>${house.tname}</TD>
    <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD>
  </TR>
  </c:forEach>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:goPage(1)">首页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
  <LI><A href="javascript:goPage(${pageInfo.pages})">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum==0?1:pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
<script>
  function goPage(pageNum) {
      $("#savePage").val(pageNum);
      document.getElementById("sform").submit();
  }
</script>
