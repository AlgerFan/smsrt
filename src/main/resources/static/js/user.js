
$(function () {

    //改变表格背景颜色
    var len = $('#tab').find('tr').length;
    for(i = 0; i < len; i++){
        if(i == 0){
            $('#tab').find('tr')[0].className = "th";
        } else{
            if(i % 2 == 0){
                $('#tab').find('tr')[i].className = "evenrowcolor";
            }else{
                $('#tab').find('tr')[i].className = "oddrowcolor";
            }
        }
    }
    //删除用户按钮
    $('.userdel').on('click',function () {
        if(confirm('确认要删除吗?')){
            var userId=$(this).prev().val();    //得到用户id
            $.ajax({
                url:'/admin/deleteById?userId='+ userId,
                type: 'DELETE',
                contentType:"application/json",//设置请求参数类型为json字符串
                dataType: 'json',
                success: function(data){
                    //打开窗口的父窗口刷新，本子窗口关闭
                    window.parent.location.reload();
                    $('body').html(data);
                },
                error: function () {
                    alert('删除信息失败！');
                }
            });
        }else{
            return false;
        }
    });

    //删除商品按钮
    //删除按钮
    $('.goddel').on('click',function () {
        if(confirm('确认要删除吗?')){
            var goodsId=$(this).prev().val();    //得到商品id
            $.ajax({
                url:'/admin/delete?goodsId='+ goodsId,
                type: 'DELETE',
                contentType:"application/json",//设置请求参数类型为json字符串
                dataType: 'json',
                success: function(data){
                    //打开窗口的父窗口刷新，本子窗口关闭
                    window.parent.location.reload();
                    $('body').html(data);
                },
                error: function () {
                    alert('删除信息失败！');
                }
            });
        }else{
            return false;
        }
    });

});
