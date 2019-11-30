var isLogin=false;
$(document).ready(function () {
    $.ajax({
        type: 'get',
        url: '/user/getUserInfo',
        success: function (d) {
            if (d.code === 0) {
                $('#textarea').text('退出');
                isLogin=true;
            } else {
                $('#textarea').text('登录');
                isLogin=false;
            }
        }
    });

});

function loginOrLoginOut() {
    console.log("调配用");
    if(isLogin===true){
        $.ajax({
            type: 'get',
            url: '/user/loginOut',
            success: function (d) {
                if (d.code === 200) {
                    $('#textarea').text('登录');
                    isLogin=false;
                }
            }
        });
    }else {
        window.location.href='/login.html';
    }
}