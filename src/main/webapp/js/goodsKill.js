var goodsKill={
    URL:{
        now:function () {
            return '/goodsKill/time/now';
        },
        exposer:function (goodId) {
            return '/goodsKill/'+goodId+'/exposer'
        },
        execution:function (goodId,md5) {
            return '/goodsKill/'+goodId+'/'+md5+'/execution';
        }
    },
    validatePhone:function (phone) {
        if(phone && phone.length==11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    handlegoodKill:function (goodId,box) {
        box.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(goodsKill.URL.exposer(goodId),{},function (result) {
            if(result && result['success']){
                var exposer=result['data'];
                if(exposer['exposed']){
                    //开启秒杀
                    var md5=exposer['md5'];
                    var killUrl=goodsKill.URL.execution(goodId,md5);
                    console.log("killUrl:"+killUrl);
                    //绑定一次事件
                    $('#killBtn').one('click',function () {
                        //1:禁用按钮
                        $(this).addClass('disabled');
                        console.log('hello');
                        //2:发送秒杀请求
                        $.post(killUrl,{},function (result) {
                            console.log("hello execution");
                            if(result && result['success']){
                                var killResult=result['data'];
                                var state = killResult['state'];
                                var stateInfo=killResult['stateInfo'];
                                box.html('<span class="label label-success">'+stateInfo+'</span>');
                            }
                        });
                    });
                    box.show();
                }else{
                    //未开启秒杀
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end =exposer['end'];
                    goodsKill.countdown(goodId,now,start,end);
                }
            }else{
                console.log('result:'+'error');
            }
        })
    },
    countdown:function (goodId,nowTime,startTime,endTime) {
        //秒杀倒计时
        var goodKillBox=$('#goodKill-box');
        if(nowTime>endTime){
            goodKillBox.html('秒杀结束！');
        }else if(nowTime<startTime){
            var killTime=new Date(startTime+1000);
            goodKillBox.countdown(killTime,function (event) {
                var format=event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                goodKillBox.html(format);
            }).on('finish.countdown',function () {
                goodsKill.handlegoodKill(goodId,goodKillBox);
            });
        }else{
            goodsKill.handlegoodKill(goodId,goodKillBox);
        }
    },
    detail:{
        init:function(params){
            //在cookie中查找手机
            var killPhone=$.cookie('killPhone');
            //验证手机号
            if(!goodsKill.validatePhone(killPhone)){
                var killPhoneModal=$("#killPhoneModal");
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',
                    keyboard:false//关闭esc
                });
                $("#killPhoneBth").click(function () {
                    var inputPhone=$('#killPhoneKey').val();
                    if(goodsKill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('killPhone',inputPhone,{expires:7,path:'/goodsKill'});
                        //刷新页面
                        window.location.reload();
                    }else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            var startTime=params['startTime'];
            var endTime=params['endTime'];
            var goodId=params['goodId'];
            $.get(goodsKill.URL.now(),{},function (result) {
                if(result && result['success']){
                    var nowTime=result['data'];
                    goodsKill.countdown(goodId,nowTime,startTime,endTime);
                }else{
                    console.log('result:'+result);
                    alert('result:'+result);
                }
            })
        }
    }
}
