window.onload=function(){

    var user = GetId('user');      //username
    var usertip = GetId('usertip');  //提示信息
    var password = GetId('password');  //pwd
    var pwd = GetId('pwd');           //密码提示
    var phone = GetId('phone');       //phone
    var ph_info = GetId('ph_info');    //手机号提示

    //用户名
    user.onblur=function(){
        if(this.value!=''){
            usertip.innerHTML = "<span class='pass'>ok</span>";
        }else{
            usertip.innerText="用户名不能为空！";
            this.focus();
        }
    };
    //密码
    password.onblur=function(){
        if(this.value!=''){
            pwd.innerHTML = "<span class='pass'>ok</span>";
        }else{
            pwd.innerText = "不能为空";
            this.focus();
        }
    };
    //手机号
    phone.onblur=function(){
        var regex = /^1\d{10}$/;
        var data = this.value;
        if(!regex.test(data) || data==""){
            ph_info.innerText="输入不合法";
            this.focus();
        }else{
            ph_info.innerHTML = "<span class='pass'>ok</span>";
        }
    };

   //得到id
    function GetId(id){
        return document.getElementById(id);
    }
};