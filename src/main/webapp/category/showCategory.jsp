<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function(){
        pageInit();
    });



    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/category/queryUserPage",
            editurl:"${path}/category/edit",  //增删改走的路径  oper:add ,edit,del
            datatype : "json",
            height : "auto",
            autowidth:true,
            styleUI:"Bootstrap",
            rowNum : 8,
            rowList : [ 8, 10, 20, 30 ],
            pager : '#catePage',
            viewrecords : true,
            colNames : [ 'Id', '类别名', '级别', '上级别id'],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cate_name',editable:true,index : 'invdate',width : 90},
                {name : 'levels',index : 'name',width : 100},
                {name : 'parent_id',index : 'note',width : 150,sortable : false}
            ],
            subGrid : true, //开启子表格
            // subgrid_id是在创建表数据时创建的div标签的ID
            //点击二级类别按钮，动态创建一个div,此div来容纳子表格，subgrid_id就是这个div的id
            // row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGird(subgrid_id, row_id);
            }
        });
        //分页工具栏
        $("#cateTable").jqGrid('navGrid', '#catePage', {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑",deltext:"删除"});
    }

    //创建子表格
    function addSubGird(subgridId, rowId) {

        //声明两个变量
        // 子表格table的id
        var subgridTableId= subgridId + "Table";
        // 子表格分页工具栏的id
        var pagerId= subgridId + "Page";

        //在div中创建子表格和分页工具栏
        $("#"+subgridId).html("<table id="+subgridTableId+" /><div id="+pagerId+" />");
        //初始化子表格
        $("#" + subgridTableId).jqGrid({
            //url : "/category/queryTwoCategory?categoryId="+rowId,
            url:"${path}/category/queryUserPage2?parent_id="+rowId,
            editurl:"${path}/category/edit",  //增删改走的路径  oper:add ,edit,del
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            height : "auto",
            autowidth:true,
            styleUI:"Bootstrap",
            colNames : [ 'Id', '类别名', '级别', '上级别id'],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cate_name',editable:true,index : 'invdate',width : 90},
                {name : 'levels',index : 'name',width : 100},
                {name : 'parent_id',index : 'note',width : 150,sortable : false}
            ]
        });

        //子表格的分页工具栏
        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true,addtext:"添加",edittext:"编辑",deltext:"删除"});
    }
    function updateUserStatus(id,status) {
        if(status==0){
            $.post("${path}/px/edit",{"id":id,"status":"1","oper":"edit"},function (data){
                //刷新表单
                $("#userTable").trigger("reloadGrid");
            })
        }else {
            $.post("${path}/px/edit",{"id":id,"status":"0","oper":"edit"},function (data){
                //刷新表单
                $("#userTable").trigger("reloadGrid");
            })
        }

    }

</script>

<%--
1.实现类别功能
   删除数据要给一个提示
2.手机验证码发送
--%>

<%--创建一个面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <span>类别信息</span>
    </div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别信息</a></li>
    </ul><br>

    <%--创建表格--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage"/>

</div>