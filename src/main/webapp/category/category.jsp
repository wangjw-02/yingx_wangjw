<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        pageInit();
    });

    function pageInit() {
        jQuery("#categoryTable").jqGrid(
            {
                styleUI: "Bootstrap",
                url: "${path}/category/queryFirst",
                editurl:"${path}/category/dmlCategory",
                datatype: "json",
                height: "auto",
                autowidth: true,
                pager:"#pager",
                rowNum: 3,
                rowList: [3, 6, 9, 12],
                viewrecords:true,
                colNames: ['id', '名称', '级别'/*, '父类别id'*/],
                colModel: [
                    {name: 'id', index: 'id', width: 55},
                    {name: 'cateName', index: 'invdate', width: 90,editable:true,editrules:{required:true}},
                    {name: 'levels', index: 'name', width: 100,editable:true,edittype:'select',editrules:{required:true},
                        editoptions:{value: '1:1'}
                    },
                   // {name: 'parentId', index: 'amount', width: 80, align: "right"},
                ],

                subGrid: true,
               // caption: "Grid as Subgrid",
                subGridRowExpanded: function (subgridId, rowId) {
                    var subgridIdTable = subgridId+"Table";
                    var pagerId = subgridId+"Pager";

                    $("#" + subgridId).html(
                        "<table id='" + subgridIdTable
                        + "' class='scroll'></table><div id='"
                        + pagerId + "' class='scroll'></div>");
                    jQuery("#" + subgridIdTable).jqGrid(
                        {
                            styleUI: "Bootstrap",
                            url: "${path}/category/querySecond?parId="+rowId,
                            editurl:"${path}/category/dmlCategory?parentId="+rowId,
                            datatype: "json",
                            height: "auto",
                            autowidth: true,
                            pager:"#"+pagerId,
                            rowNum: 3,
                            rowList: [3, 6, 9, 12],
                            viewrecords:true,
                            colNames: ['id', '名称', '级别', '父类别id'],
                            colModel: [
                                {name: 'id', index: 'id', width: 55},
                                {name: 'cateName', index: 'invdate', width: 90,editable:true,editrules:{required:true}},
                                {name: 'levels', index: 'name', width: 100,editable:true,edittype:'select',editrules:{required:true},
                                    editoptions:{value: '2:2'}
                                },
                                {name: 'parentId', index: 'amount', width: 80, align: "right"},

                            ],

                        });
                    jQuery("#" + subgridIdTable).jqGrid('navGrid',
                        "#" + pagerId, {search:false},
                        {closeAfterEdit:true},
                        {closeAfterAdd:true},
                        {afterComplete:function (a) {  //afterSubmit:function (response){}
                                alert(a.responseText);
                            }}
                    );
                }
            });
        jQuery("#categoryTable").jqGrid('navGrid', '#pager', {search:false},
            {closeAfterEdit:true},
            {closeAfterAdd:true},
            {afterComplete:function (a) {
                alert(a.responseText);
                }}
        );
    }
</script>


<div class="panel panel-success">
    <div class="panel-heading">
        <h3>类别管理</h3>
    </div>
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#">类别管理</a></li>
        </ul>

        <table id="categoryTable"></table>
        <div id="pager"></div>
    </div>

</div>