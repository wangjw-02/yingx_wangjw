<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
    <script>
        $(function () {
            $("#myTable").jqGrid({
                //访问后台的url
                url:"${path}/user/queryAllByPage",
                // 增删改url
                editurl:"${path}/user/dml",
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
                colNames:["编号","头像","用户名","手机号","个性签名","微信","状态","注册时间"],
                colModel:[
                    {name:"id"},
                    {name:"headImg",editable:true,edittype:'file',editrules:{required:true},align:"center",
                        //  参数：列的值，操作，对象
                        formatter:function (cellvalue) {
                            return "<img src='"+cellvalue+"' width='80px' height='60px'>"
                        }
                    },
                    {name:"username",editable:true,editrules:{required:true}},
                    {name:"phone",editable:true,editrules:{required:true}},
                    {name:"sign",editable:true,editrules:{required:true}},
                    {name:"wechat",editable:true,editrules:{required:true}},
                    {name:"status",edittype:'button',
                        formatter:function (cellvalue,options,rowObject) {
                            if (cellvalue==1){
                                return "<button class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")'>正常</button>";

                            } else{
                                return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+cellvalue+"\")'>冻结</button>";
                            }
                        }
                    },
                    {name:"createDate",edittype:'date'}
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
                            url:"${path}/user/fileUpload",
                            type:"post",
                            fileElementId:"headImg",
                            data:{id:response.responseText},
                            success:function () {
                                // 刷新表单
                                $("#myTable").trigger("reloadGrid");
                            }

                        })
                        return "close";
                    }
                },
                {}
            );
            // 发送手机验证码
            $("#phoneCode").click(function () {
                var phone = $("#phoneCodeValue").val();
                $.ajax({
                    url:"${path}/user/phoneCode",
                    type:"post",
                    data:{phone:phone},
                    success:function (data) {
                        //向警告框中追加信息
                        $("#showMsg").html(data.responseJSON.message);
                        //展示警告框
                        $("#deleteMsg").show();

                        //自动关闭
                        setTimeout(function(){
                            $("#deleteMsg").hide();
                        },3000);
                    }
                })
            })
        })
        function updateStatus(id,status) {

            $.ajax({
                url:"${path}/user/dml",
                type:"post",
                data:{id:id,status:status,oper:"edit"},
                success:function (data) {
                    $("#myTable").trigger("reloadGrid");
                }
            })
        }
        $(function () {
            $("#export").click(function () {
                $.ajax({
                    url:"${path}/user/exportAllUser",
                    type:"post",
                    success:function (data) {
                        var message=data.data;
                        alert(message);
                    }
                })
            })
        })

    </script>


<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 >用户信息</h3>
    </div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#">用户管理</a></li>
        </ul>
        <%--警告提示框--%>
        <div id="deleteMsg" class="alert alert-danger" style="height: 50px;width: 250px;display: none" align="center">
            <span id="showMsg"/>
        </div>
        <br>
        <div class="row-fluid">
            <div class="col-md-8">
                <button id="export" type="button" class="btn btn-success" data-toggle="button" aria-pressed="false" autocomplete="off">
                    导出用户信息
                </button>
                <button type="button" class="btn btn-info"  data-toggle="button" aria-pressed="false" autocomplete="off">
                    导入用户
                </button>
                <button type="button" class="btn btn-warning" data-toggle="button" aria-pressed="false" autocomplete="off">
                    测试按钮
                </button>
            </div>
            <div class="col-md-4">
                <form class="form-horizontal">
                    <div class="form-group">
                        <div class="input-group">
                            <input id="phoneCodeValue" type="text" class="form-control" placeholder="请输入手机号">
                            <span class="input-group-btn">
                                <button id="phoneCode" class="btn btn-default" type="button">发送验证码</button>
                            </span>
                        </div><!-- /input-group -->
                    </div>
                </form>
            </div>
        </div>

    </div>
        <table id="myTable"></table>
        <div id="pager"></div>
</div>

