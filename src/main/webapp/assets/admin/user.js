/**
 * Created by tan on 2016/1/24.
 */

$(function(){
    $('#userTable').on("click", ".js_edit", function(e){
        var action = $(this).data("action");
        var $view  = $($(this).data("id"));
        var width  = $view.data("width");
        var height = $view.data("height");
        $.getJSON(action, function(data){
            laytpl($view.html()).render(data, function(html){
                layer.open({
                    type: 1,
                    title: '<span class="am-icon-file-text-o"></span> 编辑',
                    scrollbar: false,
                    skin: 'layui-layer-rim',
                    area: [width == null ? '680px' : width, height == null ? '420px' : height],
                    content: html
                });
            });
        });
    });
    $('#userTable').on("click", ".js_del", function(e){
        var action = $(this).data("action");
        alert(action);
//        $.getJSON(action, function(data) {
//
//        });
    });

});