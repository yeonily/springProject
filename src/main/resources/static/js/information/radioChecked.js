/*
* information/readyTest.html
* */

// 헤더 검색창 없애기
$(".search_form").css("visibility", "hidden");

//서브헤더 보이기
$(document).ready(function () {
    $("#sub_header").css("display", "block");
})

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(5)").children("a").addClass("selected-header");
})

//서브헤더 소카 이름
$(document).ready(function () {
    $('div.menuList ul li:nth-child(1)').children().text("청년정책");
    $('div.menuList ul li:nth-child(2)').children().text("농업정보");
    $('div.menuList ul li:nth-child(3)').children().text("준비도 테스트");
    $('div.menuList ul li:nth-child(4)').children().text("단계별 체크리스트");
})

var btnList = [];
btnList = $('div.menuList ul li button');
console.log(btnList);
$(btnList[0]).attr("onclick", "location.href='/information/policy'");
$(btnList[1]).attr("onclick", "location.href='/information/crops'");
$(btnList[2]).attr("onclick", "location.href='/information/ready'");
$(btnList[3]).attr("onclick", "location.href='/information/step'");


// 체크 리스트 개수
$(document).ready(function(){
    $("input[type=radio].check-radios").on('change', function(){
        $yes = $("input[type=radio][value='Y']:checked").length;
        $no = $("input[type=radio][value='N']:checked").length;

        $('td.score01').text($yes);
        $('td.score02').text($no);
    });
});

// 준비도 테스트 점수
$(document).ready(function(){
    $("input[type=radio].radios-btn").on('change', function(){
        const $scoreResult = $('div#result li.scoreResult');
        const $resultText = $('div#result li.resultText');
        let tempRange = $scoreResult.html();
        let tempText = $resultText.html();

        let step1 = 0;
        let step2 = 0;
        let step3 = 0;
        let step4 = 0;
        let step5 = 0;
        let total = 0;

        let resultText1 = "귀농에 대한 적응력이나 의욕, 준비정도가 상당히 높은 것으로 판단되어지며, 귀농조건은 일단 갖추어졌다고 보여 집니다. \
        귀농에 성공하기 위해서는 귀농선배들을 통한 체험담 뿐만이 아니라 실제 연수과정이나 농촌체험을 통해 귀농에 대한 지식을 높여 가시기 바랍니다. \
        또한 각종 지원제도 등도 변경되거나 신설되어 시행되는 경우도 있으므로 늘 염두에 두시고, \
        농촌에 들어가셔서 처음 알게 되는 일들도 아주 많을 것이므로 농촌생활도 영농의 일부라는 생각으로 준비해 가시기 바랍니다."

        let resultText2 = "귀농하기까지 갖추어야 할 것이 많이 남아 있지만, \
        장기적인 계획을 갖고 교육기관이나 전문가와의 상담을 통해 준비를 해 나간다면 좋은 성과들이 나타날 것입니다. \
        귀농 선배들로부터 체험담을 듣거나, 농촌체험 과정을 통해 귀농 비전을 세워 보시기 바랍니다. \
        농업의 비전을 명확히 하고, 자신의 스타일에 맞는 농업이나 자신에게 지금 무엇이 필요한지를 확인할 필요가 있습니다."

        // 체크한 값들 더해서 각각 담아주기
        for(let i = 1; i < 7; i++) {
            step1 = step1 + Number($("input[type=radio][name='rTest1" + i + "']:checked").val());
            step2 = step2 + Number($("input[type=radio][name='rTest2" + i + "']:checked").val());
            step3 = step3 + Number($("input[type=radio][name='rTest3" + i + "']:checked").val());
            step4 = step4 + Number($("input[type=radio][name='rTest4" + i + "']:checked").val());
            step5 = step5 + Number($("input[type=radio][name='rTest5" + i + "']:checked").val());
        }

        total = step1 + step2 + step3 + step4 + step5;

        // 값 수정
        $('td.test-result1').text(step1);
        $('td.test-result2').text(step2);
        $('td.test-result3').text(step3);
        $('td.test-result4').text(step4);
        $('td.test-result5').text(step5);
        $('td span.total-result').text(total);

        console.log(step1);
        console.log(step2);
        console.log(total);

        // 각 점수에 맞는 문구 출력
        if (total > 119) {
            $('li.scoreResult').text('120 ~ 150');
            $('li.resultText').text(resultText2);
        } else if (total < 75) {
            $scoreResult.text('30 ~ 74');
            $resultText.text(resultText2);
        } else if (total < 120 && total > 74){
            $scoreResult.html(tempRange);
            $resultText.text(tempText);
        }

    });
});

//검색 없애기
$(".search_form").hide();