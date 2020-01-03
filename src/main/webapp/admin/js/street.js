$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getStreet',
        pagination:true,  //开启分页
        pageSize:3,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar', //指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'区域名称',width:100,align:'right'},
            {field:'opt',title:'操作',width:100,align:'right',
                formatter: function(value,row,index){
                    return "<a href='javascript:goEdit("+row.id+");'>添加</a>&nbsp;<a href='javascript:geDel("+row.id+");'>删除</a>";}}
        ]]
    });
});
function goadd() {
    $("#AddDialog").dialog("open").dialog('setTitle',"添加区域");
}
function SaveDialog() {
    //方式一:使用jquery发送异步请求
    //$.post(地址,参数,回调函数,"json")
    //方式二:借助easyui异步提交表单
    $('#addDialogForm').form('submit', {
        url:"addStreet",
        success:function(data){
            var obj=$.parseJSON(data);
            if (obj.result>0){
                $("#AddDialog").dialog("close");
                $("#dg").datagrid('reload');
            }else {
                $.message.alert("友情提示","添加失败","info");
            }
        }
    });
}

function CloseDialog(dialogId) {
    $("#"+dialogId).dialog("close");
}

function goUpdate() {
    /*//1.获取datagrid的选中行
    var selObjs=$("#dg").datagrid("getSelections");
    //2.验证是否选中一行
    if(selObjs.length==1){
        $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");
        //还原表单数据  查询数据库，通过id获取单行记录的对象，进行回显？
        // $("#upDialogForm").form('load',json对象:{"表单对象名称":值});
        $("#upDialogForm").form('load',selObjs[0]);

    }else{
        $.messager.alert('友情提示','你可能没有选中行，获者选中多行，请选择一行修改，真他妈笨，选一行都不会','info');
    }*/
    //1.获取datagrid的选中行
    var selObjs=$("#dg").datagrid("getSelections");
    if(selObjs.length==1){
        $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");
        var id = selObjs[0].id;
        $.post("updateStreet",{"id":id},
            function (data) {
                $("#upDialogForm").form('load',data);
            },
            "json")
    }else {
        $.messager.alert('友情提示','你可能没有选中行，获者选中多行，请选择一行修改，真他妈笨，选一行都不会','info');
    }
}

function goEdit(id) {
    $("#upDialog").dialog("open").dialog('setTitle',"编辑区域");
    //发送异步请求回显数据
    $.post("updateStreet",{"id":id},
        function (data) {
            $("#upDialogForm").form('load',data);
        },
        "json")
}
function updateSaveDialog() {
    //使用easyui提交表单
    $('#upDialogForm').form('submit', {
        url:"updateStreetById",
        success:function(data){
            console.log(data);
            var obj=$.parseJSON(data);          //将json字符串转化为json对象
            if (obj.result>0){
                $('#dg').datagrid('reload');        //刷新
                $("#upDialog").dialog("close");
            }else {
                $.message.alert("友情提示","修改失败","info");
            }
        }
    });
}
//编辑列中的删除
function geDel(id) {
    $.messager.confirm('友情提示', '是否确认删除吗？', function(r){
        if (r){
            $.post("delStreet",{"id":id},function (data) {
                if (data.result>0){
                    $("#dg").datagrid('reload');
                }else{
                    $.messager.alert('友情提示','删除失败，请联系管理员:13260601227!','info');
                }
            },"json");
        }
    });
}

//工具栏中的删除
function goDeleteMore() {
    //1.获取datagrid的选中行
    var selObjs=$("#dg").datagrid("getSelections");
    if (selObjs.length>0){
        $.messager.confirm('友情提示', '是否确认删除吗', function(r){
            if (r){
                //获取选中项的值
                var delValue = "";
                for (var i = 0; i < selObjs.length; i++) {
                    delValue = delValue + selObjs[i].id + ",";
                }
                delValue = delValue.substring(0,delValue.length-1);
                //alert(delValue);

                //发送异步请求到服务器
                $.post("delMoreStreet",{"ids":delValue},function (data) {
                    if (data.result>0){
                        $("#dg").datagrid('reload');
                        $.messager.alert("提示框","成功删除"+data.result+"行");
                    }else{
                        //alert("sss");
                        $.messager.alert('友情提示','删除失败，请联系管理员:13260601227!','info');
                    }
                },"json");
            }
        });
    }else {
        $.messager.alert('提示框','你还没有选中行，请过细点');
    }
}