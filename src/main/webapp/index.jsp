<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>测试调用JAVA存储过程</title>
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css"/>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">

    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>


    <!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

    <!--请在下方写此页面业务相关的脚本-->
    <script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
    <script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
    <script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript" src="lib/datatables/dataTables.colReorder.min.js"></script>
    <script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
    <script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
</head>
<body>
<div>
    <form id="form-search" class="page-container">
        <div class="text-c"> 日期范围：
            <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'maxDate\')||\'%y-%M-%d\'}' })" id="minDate"
                   name="minDate" class="input-text Wdate" style="width:120px;">
            -
            <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'minDate\')}',maxDate:'%y-%M-%d' })"
                   id="maxDate" name="maxDate" class="input-text Wdate" style="width:120px;">
            <input type="text" name="searchKey" id="searchKey" placeholder="科室名称" style="width:250px"
                   class="input-text">
            <button name="" id="searchButton" type="submit" class="btn btn-success"><i class="Hui-iconfont">&#xe665;</i>
                搜科室
            </button>
        </div>
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" onclick="dept_add('添加科室','dept-add')" ><i class="Hui-iconfont">&#xe600;</i> 添加科室</a></span> <span class="r">共有数据：<strong id="itemListCount">0</strong> 条</span> </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="5%"><input name="" type="checkbox" value=""></th>
                        <th width="5%">ID</th>
                        <th width="10%">科室名称</th>
                        <th width="5%">父科室</th>
                        <th width="40%">科室简介</th>
                        <th width="10%">科室位置</th>
                        <th width="10%">创建日期</th>
                        <th width="10%">更新日期</th>
                        <th width="5%">删除</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </form>
</div>


<br>
<hr>
<div>
    <div>测试调用JAVA调用存储过程</div>
    <form method="post" id="loginForm">
        <input name="id" placeholder="科室编号" type="text" id="id"><br>
        <input name="parent_dept_id" placeholder="父科室ID"
               type="text" id="parent_dept_id"><br>
        <input id="btn" value="调用" type="submit">
    </form>
    <hr>
    <form method="post" id="testResultSetFrom">
        <input name="parId" placeholder="父科室编号" type="text" id="parId"><br>
        <input id="btnparId" value="调用结果集存储过程" type="submit">
    </form>
    <hr>
</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function () {
        loadTable();
        product_count();

        loginFormSubmit();
        testResultSetFromSubmit();
    });
    function dept_add(title,url){
        var ti=title;
        var u=url;
        /*var index = layer.open({
         type: 2,
         title: title,
         content: url
         });
         layer.full(index);*/
        $(location).attr('href','${pageContex.request.contextPath}/add');
    }

    function loadTable() {
        $('.table').DataTable({
            "serverSide": true,//开启服务器模式
            "processing": false,//加载显示提示
            "searching" : false,
            "ajax": {
                url: "/par1",
                type: 'POST',
                data: {
                    "parId": -1
                },
                error: function (XMLHttpRequest) {
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status + ' 错误信息:' + JSON.parse(XMLHttpRequest.responseText).message, {
                        title: '错误信息',
                        icon: 2
                    });
                }
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return "<input name=\"checkbox\" value=\"" + row.deptId + "\" type=\"checkbox\" value=\"\">";
                    }
                },
                {"data": "deptId"},
                {"data": "detpName"},
                {"data": "parentDeptId"},
                {"data": "desc"},
                {"data": "location"},
                {"data": "dtCreate"},
                {"data": "dtUpdate"},
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return "<a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"product_edit('商品编辑','product-edit'," + row.deptId + ")\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"delDataRow('" + row.deptId + "')\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            ],
            "aaSorting": [[1, "asc"]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                {"orderable": false, "aTargets": [0,4,8]}// 制定列不参与排序
            ],
            language: {
                url: '/lib/datatables/Chinese.json'
            },
            colReorder: true
        });
    }
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }
    function product_del(obj, id) {
        msgSuccess(id);
    }
    function product_edit(obj1, obj2, obj3) {
        msgSuccess(obj3);
    }
    function product_count(){
        $.ajax({
            url:"/count",
            type: 'GET',
            success:function (result) {
                $("#itemListCount").html(result.count);
            },
            error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }
        });
    }
    /*批量删除*/
    function datadel() {
        var ids="";
        var cks=document.getElementsByName("checkbox");
        var count=0;
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                ids+=cks[i].value+",";
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            $.ajax({
                type: 'DELETE',
                url: '/del/'+ids,
                dataType: 'json',
                data: {
                    _method: 'DELETE'
                },
                success:function(data){
                    if(data.isok!=true){
                        layer.alert("删除错误，请重试！",{title: '错误信息',icon: 2});
                    }else
                    {
                        layer.msg('已删除!',{icon:1,time:1000});
                        product_count();
                        refresh();
                    }
                },
                error:function(XMLHttpRequest){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    function refresh(){
        var table = $('.table').DataTable();
        table.ajax.reload(null,false);// 刷新表格数据，分页信息不会重置
    }
    function msgSuccess(content){
        layer.confirm(content,{icon:0});
    }
    function  delDataRow(id) {
        layer.confirm('确认要删除所选的数据吗？',{icon:0},function(index){
            $.ajax({
                type: 'DELETE',
                url: '/del/'+id+",",
                dataType: 'json',
                data: {
                    _method: 'DELETE'
                },
                success:function(data){
                    if(data.isok!=true){
                        layer.alert("删除错误，请重试！",{title: '错误信息',icon: 2});
                    }else
                    {
                        layer.msg('已删除!',{icon:1,time:1000});
                        product_count();
                        refresh();
                    }
                },
                error:function(XMLHttpRequest){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                }
            });
        });
    }
    /*多条件查询*/
    $("#form-search").validate({
        rules:{
            minDate:{
                required:true
            },
            maxDate:{
                required:true,
            },
            searchKey:{
                required:false,
            },
        },
        onkeyup:false,
        focusCleanup:false,
        success:"valid",
        submitHandler:function(form){
            //var searchKey= $('#searchKey').val();
            //var minDate= $('#minDate').val();
            // maxDate= $('#maxDate').val();
            var param =$("#form-search").serializeObject();
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.url('/search').load();
        }
    });
    jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
        minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
        rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
        range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: jQuery.validator.format("请输入一个最大为{0} 的值"),
        min: jQuery.validator.format("请输入一个最小为{0} 的值")
    });
    function chooseCategory(){
        layer_show("选择商品分类","choose-category",300,510);
    }


    function loginFormSubmit() {
        var url = "/login";
        $("#loginForm").submit(function () {
            var params = $("#loginForm").serializeObject();
            var ax = $.ajax({
                url: url,
                type: "POST",
                data: JSON.stringify(params),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                timeout: 30000,
                async: true,
                beforeSend: function () {
                },
                error: function (data, textStatus, e) { //出错处理
                    alert("error");
                },
                success: function (data, textStatus, args) { //成功处理
                    alert(data.name);
                }
            });
            return false;
        });
    }
    function testResultSetFromSubmit() {
        var url1 = "/par";
        $("#testResultSetFrom").submit(function () {
            var params = $("#testResultSetFrom").serializeObject();
            var ax = $.ajax({
                url: url1,
                type: "POST",
                data: JSON.stringify(params),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                timeout: 30000,
                async: true,
                beforeSend: function () {
                },
                error: function (data, textStatus, e) { //出错处理
                    alert("error");
                },
                success: function (data, textStatus, args) { //成功处理
                    $.each(data, function (index, item) {
                        alert(item.deptId + "<br>" + item.detpName + "<br>" + item.parentDeptId + "<br>" + item.desc + "<br>" + item.location);
                    });
                }
            });
            return false;
        });
    }
</script>
