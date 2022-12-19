/*
* program/pay.html
* */

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(2)").children("a").addClass("selected-header");
})



// input에 이름 입력 시 실시간으로 출력
$("input[name='name']").keyup(function(){
    $("span#name").html($(this).val());
});


//이용약관
$("#ruleB").on("click",function () {
    if($(this).parent().siblings("#ruleD").css("display")=="block"){
        $(this).parent().siblings("#ruleD").hide('fast');
        $(".rule-button").css({'transform':'rotate('+180+'deg)'});
    }else{
        $(this).parent().siblings("#ruleD").show('fast');
        $(".rule-button").css({'transform':'rotate('+90+'deg)'});
    }
})
//
// function doDisplay(){
//     var con = document.getElementById("ruleD");
//     if(con.style.display=='none'){
//         con.style.display = 'block';
//     }else{
//         con.style.display = 'none';
//     }
// }

//결제방법 선택
$("li.a").on("click", function(){
    $("#inicis").prop("checked", true);
    $("#kakaopay").prop("checked", false);
});
$("li.b").on("click", function(){
    $("#inicis").prop("checked", false);
    $("#kakaopay").prop("checked", true);
});
