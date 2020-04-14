<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    function queryAllTwoCate(){
        var options="";
        var i = 0;
        $.ajax({
            async: false,  //千万要记住加这个属性配置
            url:"${path}/category/queryAllTwo",
            type:"POST",
            dataType:"JSON",
            success:function (data) {
                for(i; i < data.length; i++) {
                    if (i != data.length - 1) {
                        options += data[i].id + ":" + data[i].cateName + ";";
                    } else {
                        options += data[i].id + ":" + data[i].cateName;
                    }
                }
               /* $.each(data, function (index, province) {
                    options += province.id + ":" + province.cateName+";";
                })*/
            }
        })
        return options;
    }
    $(function () {

        $("#myTable").jqGrid({
            //访问后台的url
            url:"${path}/video/queryByPage",
            // 增删改url
            editurl:"${path}/video/dml",
            styleUI:"Bootstrap",
            datatype:"json",
            //父元素width的100%
            autowidth:true,
            height:"auto",
            // 分页
            pager:"#pager",
            //每一页显示多少条数据
            rowNum:2,
            //手动选择每页显示数据条数
            rowList:[2,4,8,10],
            //排序的列
            //sortname:"createDate",
            // 总记录数
            viewrecords:true,
            // multiselect:true,
            colNames:["id","标题","描述","视频","上传时间","用户id","类别id","分组id"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true,editrules:{required:true},align:"center"},
                {name:"brief",editable:true,editrules:{required:true}},
                {name:"path",editable:true,edittype:'file',editrules:{required:true},
                    //  参数：列的值，操作，对象
                    formatter:function (cellvalue,options,rowObject) {
                        return "<video src='"+cellvalue+"' controls width='600px' heigt='300px' poster='"+rowObject.cover+"'/>";
                    }},
                {name:"publishDate",edittype:'date'},
                {name:"userId",editable:true,editrules:{required:true}},
                {name:"categoryId",editable:true,edittype:'select',editrules:{required:true},
                    editoptions:{value:queryAllTwoCate()}
                },
                {name:"groupId",editable:true,editrules:{required:true}}
            ]
        }).jqGrid("navGrid","#pager",{search:false,edit:false,add:true,del:true,addtext:"添加",deltext:"删除"},
            {

            },
            {
                closeAfterAdd:true,//关闭对话框
                // 做异步的文件上传
                afterSubmit:function(response){
                    /*
                    * 数据入库
                    * 在提交之后做文件上传
                    * 图片路径  id
                    * */
                    $.ajaxFileUpload({
                        url:"${path}/video/videoUpload",
                        type:"post",
                        fileElementId:"path",
                        data:{id:response.responseText},
                        success:function () {
                            // 刷新表单
                            $("#myTable").trigger("reloadGrid");
                        }

                    })
                    return "close";
                }
            },
            {
                afterSubmit:function(data){
                    //向警告框中追加错误信息
                    $("#showMsg").html(data.responseJSON.message);
                    //展示警告框
                    $("#deleteMsg").show();

                    //自动关闭
                    setTimeout(function(){
                        $("#deleteMsg").hide();
                    },3000);
                    return "close";
                }
            }
        )
    })
</script>


<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 >视频信息</h3>
    </div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#">视频信息</a></li>
        </ul>
        <%--警告提示框--%>
        <div id="deleteMsg" class="alert alert-danger" style="height: 50px;width: 250px;display: none" align="center">
            <span id="showMsg"/>
        </div>


    </div>
    <table id="myTable"></table>
    <div id="pager"></div>
</div>

