function getfilename(){
    var val = document.querySelector("input[type=file]").files[0].name;
    var pic_name = document.getElementById('file_name');
    pic_name.innerHTML = val;
}
<!-- 编辑器编辑后，提交时执行js，获得编辑器的内容，赋值给textarea，用于向后台提交存入数据库 -->
function modifyContent() {
    var introduce = document.getElementById("introduce");
    introduce.value = editor.txt.html();
}