<%--
  Created by IntelliJ IDEA.
  User: baozhikuan
  Date: 2018/8/22
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h2>秒杀列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>创建时间</th>
                            <th>详情页</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="good" items="${list}">
                            <tr>
                                <td>${good.name}</td>
                                <td>${good.number}</td>
                                <td><fmt:formatDate value="${good.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><fmt:formatDate value="${good.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><fmt:formatDate value="${good.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><a class="btn btn-info" href="/goodsKill/${good.goodId}/detail" target="_blank">more</a> </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>