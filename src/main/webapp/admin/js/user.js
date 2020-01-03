$(function(){
    //1.使用datagrid组件绑定数据
    $('#dg').datagrid({
        url:'getUsers',
        pagination:true,  //开启分页
        pageSize:3,  //初始化页大小
        pageList:[3,5,8,10,20],  //页大小选项
        toolbar:'#ToolBar', //指定工具栏
        columns:[[
            {checkbox:true,width:100,align:'right'},
            {field:'id',title:'编号',width:100,align:'right'},
            {field:'name',title:'姓名',width:100,align:'right'},
            {field:'telephone',title:'电话',width:100,align:'right'},
            {field:'isadmin',title:'是否是管理员',width:100,align:'right'},
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
        url:"addDistrict",
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

function searchUser() {
    var inputName = $("#inputName").val();
    var inputTel = $("#inputTel").val();
    //设置条件查询
    $('#dg').datagrid({
        queryParams: {
            name: inputName,
            tel: inputTel
        }
    });


}