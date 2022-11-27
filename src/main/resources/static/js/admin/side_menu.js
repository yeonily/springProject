$openClose = $('.toggleBtn2');


const $lis = $('#side-menu li.sub-menu>a span.toggleBtn2'); // 테이블의 모든 ul
$('#side-menu li.sub-menu>a').on('click', function(){
    $lis.each(function(i, li){
        console.log(li.innerHTML);
        console.log($(this)[0]);

        // if(li.innerHTML == '+'){
        // 	li.innerHTML = '-';
        // } else {
        // 	li.innerHTML = '+';
        // }
    });

});


( function( $ ) {
    $( document ).ready(function() {
        $('#side-menu li.sub-menu>a').on('click', function(){

            $(this).removeAttr('href');
            var element = $(this).parent('li');

            if (element.hasClass('open')) {
                element.removeClass('open');
                element.removeClass('openClose');
                element.find('li').removeClass('open');
                element.find('li').removeClass('openClose');
                element.find('ul').slideUp();
            }
            else {
                element.addClass('open');
                element.children('ul').slideDown();
                element.siblings('li').children('ul').slideUp();
                element.siblings('li').removeClass('open');
                element.siblings('li').find('li').removeClass('open');
                element.siblings('li').find('ul').slideUp();
            }

        });
    });
} )( jQuery );


