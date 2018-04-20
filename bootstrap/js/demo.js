
$(document).ready(function () {
    //默认隐藏所以列表
    $('ul ul').hide();
    //点击显示和隐藏列表
    $('ul>li>a').click(function () {
        $(this).next().slideToggle("slow");
    });
    //点击收缩文件树
    $('.control').children('span').click(function () {
        if (!$('#aaa').hasClass('mini')) {
            $('#aaa').addClass('mini');
            $('.mini>.left_nav').animate({
                width: '8.33333333%',
            });
            $('#aaa .right_content').animate({
                width: '91.66666667%',
                marginLeft: '8.33333333%'
            });
        } else {
            $('.mini').removeClass("mini");
            $('#aaa>.left_nav').animate({
                width: '25%',
            });
            $('#aaa .right_content').animate({
                width: '75%',
                marginLeft: '25%'
            });
        }
    })
    //全部选择按钮
    $('#all_select').change(function () {
        if ($('#all_select').is(':checked')) {
            $("input[type=checkbox]").prop({
                'checked': 'checked'
            });
        } else {
            $('input[type=checkbox]').prop({
                'checked': false
            });
        }
    });
    //部分全选
    $('li>input[type=checkbox]').change(function () {
        if ($(this).is(':checked')) {
            $(this).next().next().find('input[type=checkbox]').prop({
                'checked': true
            });
        } else {
            // $(this).parent().next().find('input[type=checkbox]').removeAttr('checked');
            $(this).next().next().find('input[type=checkbox]').prop({
                'checked': false
            });
        }
    })

    for (let i = 0; i < $('.left_btn').children().length; i++) {
        // //下拉菜单的显示和隐藏
        showAndHide(i)  
        // //元素放在父元素的div上也能显示
       
    }
    function showAndHide(i){
        $('.left_btn>a').eq(i).mouseover(function () {
            $('.left_bottom_nav').eq(i).css('left',i*119+'px')
            $('.left_bottom_nav').eq(i).show();;
        });
        $('.left_btn>a').eq(i).mouseout(function () {
            $('.left_bottom_nav').eq(i).hide();;
        });
        $('.left_bottom_nav').eq(i).mouseover(function () {
            $(this).show();;
            $('.left_btn>a').eq(i).addClass("mouse_over");
        });
        $('.left_bottom_nav').eq(i).mouseout(function () {
            $(this).hide();;
            $('.left_btn>a').eq(i).removeClass("mouse_over");
        });
    }

})
