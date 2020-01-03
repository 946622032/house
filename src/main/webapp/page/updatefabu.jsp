<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
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
                    $("#type_id").val(${house.typeId})
                }
            },"json")

            $.post("getDistrictAll",null,function (data) {
                //遍历所有街道信息
                for (var i = 0; i <data.length ; i++) {
                    var optionnode = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                    //把街道信息追加到select里面
                    $("#district_id").append(optionnode);
                    //1.选中区域的选中项
                    $("#district_id").val(${house.districtId})

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
                    $("#street_id").val(${house.streetId})

                }
            },"json");
        }
    </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新房屋信息发布</DT>
            <DD class=past>填写房屋信息</DD>
        </DL>
        <DIV class=box>
            <FORM id=add_action method=post name=add.action
                  action=upHouse enctype="multipart/form-data">
                <input type="hidden" name="id" value="${house.id}">
                <input type="hidden" name="oldPicPath" value="${house.path}">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>标　　题：</TD>
                            <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD>
                                <SELECT class=text name=typeId id="type_id">
                                    <option value="">请选择</option>
                                </SELECT>
                            </TD>

                        </TR>
                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD><INPUT id=add_action_floorage class=text type=text
                                       name=floorage value="${house.floorage}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"></TD>
                        </TR>
                        <TR>
                            <TD class=field>房产证日期：</TD>
                            <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${house.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>"></TD>
                        </TR>
                        <TR>
                            <TD class=field>位　　置：</TD>
                            <TD>区：<SELECT class=text name=district_id id="district_id">
                                <option value="">请选择</option>
                            </SELECT> 街：<SELECT class=text
                                                name=streetId id="street_id">
                                <option value="">请选择</option>
                            </SELECT></TD>
                        </TR>
                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"></TD>
                        </TR>
                        <TR>
                          <TD class=field>图片： </TD>
                          <TD><img width="100" height="100" src="http://localhost:80/${house.path}"><input type="file" name="pfile"></TD>
                        </TR>
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons><INPUT  value=更新 type=submit>
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
