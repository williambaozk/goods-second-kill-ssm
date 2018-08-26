<%--
  Created by IntelliJ IDEA.
  User: baozhikuan
  Date: 2018/8/22
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-success text-center">
            <div class="panel-heading"><h2>${good.name}</h2></div>

            <div class="panel-body">
                <h2 class="text-center">
                    <%--显示time图标--%>
                    <span class="glyphicon glyphicon-time"></span>
                    <%--显示倒计时--%>
                    <span class="glyphicon" id="goodKill-box"></span>
                </h2>
            </div>
        </div>
    </div>

    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>秒杀电话：
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机" class="form-control">
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button type="button" id="killPhoneBth" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
<%--jQuery Cookie操作插件--%>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--jQuery countDown倒计时插件--%>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>

<script src="/js/goodsKill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        goodsKill.detail.init({
            goodId:${good.goodId},
            startTime:${good.startTime.time},
            endTime:${good.endTime.time}
        });
    })

</script>
</html>
