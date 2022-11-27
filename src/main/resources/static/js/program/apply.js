/*
* program/apply.html
* */

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(2)").children("a").addClass("selected-header");
})


//버튼 클릭 시 숫자 증가,감소
function count(type)  {
    // 결과를 표시할 element
    const resultElement = document.getElementById('apply-count');

    // 현재 화면에 표시된 값
    let number = resultElement.innerText;

    // 더하기/빼기
    if (type === 'plus') {
        number = parseInt(number) + 1;
    } else if (type === 'minus' && number > 1) {
        number = parseInt(number) - 1;
    }

    // 1 이상일 때 빼기 활성화
    if(number > 1){
        $("button.down").attr("disabled", false);
        $("button.down").css({"background":"url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAiIGhlaWdodD0iMzAiIHZpZXdCb3g9IjAgMCAzMCAzMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yMCAxNHYySDEwdi0yeiIgZmlsbD0iIzMzMyIgZmlsbC1ydWxlPSJub256ZXJvIi8+Cjwvc3ZnPgo=)"});
    }else{
        $("button.down").attr("disabled", true);
        $("button.down").css({"background":"url(data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMzAiIGhlaWdodD0iMzAiIHZpZXdCb3g9IjAgMCAzMCAzMCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgIDxwYXRoIGQ9Ik0yMCAxNHYySDEwdi0yeiIgZmlsbD0iI0RERCIgZmlsbC1ydWxlPSJub256ZXJvIi8+Cjwvc3ZnPgo=)"});

    }

    // 결과 출력
    resultElement.innerText = number;
}

//유효성 검사
$(document).ready(function () {

    //이름
    const partname = document.getElementById("username");
    partname.addEventListener('click', () => {
        //이름을 안 썼을 때
        if (!applyForm.name.value) {
            $("p.caption1").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 내용을 입력해주세요.");
            $("p.caption1").css("color", "red");
            $("p.caption1").parent().siblings().children('input').css("border-color", "red");
            return;
        }

        //실명 작성 안내
        if(applyForm.name.value){
            $("p.caption1").html("<img width='10px' height='10px' src='/image/program/fullmark.gif'> 실명을 입력해주세요.");
            $("p.caption1").css("color", "gray");
            $("p.caption1").parent().siblings().children('input').css("border-color", "#e1e4e6");
            return;
        }
    })

    //작성하면 실명작성 안내로 바뀜.
    partname.addEventListener('keyup', function () {
        $("p.caption1").html("<img width='10px' height='10px' src='/image/program/fullmark.gif'> 실명을 입력해주세요.");
        $("p.caption1").css("color", "gray");
        $("p.caption1").parent().siblings().children('input').css("border-color", "#e1e4e6");
    })

    partname.addEventListener('blur', () => {
        //이름 작성 완료 시 js 사라지기
        if(applyForm.name.value){
            $("p.caption1").text("");
            $("p.caption1").parent().siblings().children('input').css("border-color", "#e1e4e6");
            return;
        }
    })


    //휴대폰
    const partphone = document.getElementById("userphone");
    partphone.addEventListener('click', () => {
        //폰번호를 안 썼을 때
        if (!applyForm.phone.value) {
            $("p.caption2").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 내용을 입력해주세요.");
            $("p.caption2").css("color", "red");
            $("p.caption2").parent().siblings().children('input').css("border-color", "red");
            return;
        }

        //올바른 번호 작성 안내
        if(applyForm.phone.value){
            $("p.caption2").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 휴대폰 번호를 입력해주세요.");
            $("p.caption2").css("color", "red");
            return;
        }
    })
    //작성하면 올바른 번호 작성 안내로 바뀜.
    partphone.addEventListener('keyup', function () {
        $("p.caption2").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 휴대폰 번호를 입력해주세요.");
        $("p.caption2").css("color", "red");
    })

    partphone.addEventListener('blur', () => {
        //폰번호 작성 완료 시 js 사라지기
        if(applyForm.phone.value){
            $("p.caption2").text("");
            $("p.caption2").parent().siblings().children('input').css("border-color", "#e1e4e6");
            return;
        }
    })

    //이메일
    const partemail = document.getElementById("useremail");
    partemail.addEventListener('click', () => {
        //이메일을 안 썼을 때
        if (!applyForm.email.value) {
            $("p.caption3").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 내용을 입력해주세요.");
            $("p.caption3").css("color", "red");
            $("p.caption3").parent().siblings().children('input').css("border-color", "red");
            return;
        }

        //올바른 이메일 작성 안내
        if(applyForm.email.value){
            $("p.caption3").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 이메일 주소를 입력해주세요.");
            $("p.caption3").css("color", "red");
            return;
        }
    })

    //작성하면 올바른 이메일 작성 안내로 바뀜.
    partemail.addEventListener('keyup', function () {
        $("p.caption3").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 이메일 주소를 입력해주세요.");
        $("p.caption3").css("color", "red");
    })

    partemail.addEventListener('blur', () => {
        //이메일 작성 완료 시 js 사라지기
        if(applyForm.email.value){
            $("p.caption3").text("");
            $("p.caption3").parent().siblings().children('input').css("border-color", "#e1e4e6");
            return;
        }
    })

    //거주지
    const parthome = document.getElementById("userhome");
    parthome.addEventListener('click', () => {
        //거주지을 안 썼을 때
        if (!applyForm.home.value) {
            $("p.caption4").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 내용을 입력해주세요.");
            $("p.caption4").css("color", "red");
            $("p.caption4").parent().siblings().children('input').css("border-color", "red");
            return;
        }

        //올바른 거주지 작성 안내
        if(applyForm.home.value){
            $("p.caption4").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 시군구를 입력해주세요.");
            $("p.caption4").css("color", "red");
            return;
        }
    })

    //작성하면 올바른 거주지 작성 안내로 바뀜.
    parthome.addEventListener('keyup', function () {
        $("p.caption4").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 시군구를 입력해주세요.");
        $("p.caption4").css("color", "red");
    })

    parthome.addEventListener('blur', () => {
        //거주지 작성 완료 시 js 사라지기
        if(applyForm.home.value){
            $("p.caption4").text("");
            $("p.caption4").parent().siblings().children('input').css("border-color", "#e1e4e6");
            return;
        }
    })

    //생년월일
    const partbirth = document.getElementById("userbirth");
    partbirth.addEventListener('click', () => {
        //생년월일을 안 썼을 때
        if (!applyForm.birth.value) {
            $("p.caption5").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 내용을 입력해주세요.");
            $("p.caption5").css("color", "red");
            $("p.caption5").parent().siblings().children('input').css("border-color", "red");
            return;
        }

        //올바른 생년월일 작성 안내
        if(applyForm.birth.value){
            $("p.caption5").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 생년월일을 입력해주세요.");
            $("p.caption5").css("color", "red");
            return;
        }
    })

    //작성하면 올바른 거주지 작성 안내로 바뀜.
    partbirth.addEventListener('keyup', function () {
        $("p.caption5").html("<img width='10px' height='10px' src='/image/program/fullredmark.gif'> 올바른 생년월일을 입력해주세요.");
        $("p.caption5").css("color", "red");
    })

    partbirth.addEventListener('blur', () => {
        //생년월일 작성 완료 시 js 사라지기
        if(applyForm.birth.value){
            $("p.caption5").text("");
            $("p.caption5").parent().siblings().children('input').css("border-color", "#e1e4e6");
            return;
        }
    })

})

// input에 이름 입력 시 실시간으로 출력
$("input[name='name']").keyup(function(){
    $("span#name").html($(this).val());
});


// input 값 모두 입력했을 때 회원 가입하기 버튼 활성화
applyForm.name.addEventListener("keyup", inputfin)
applyForm.phone.addEventListener("keyup", inputfin)
applyForm.email.addEventListener("keyup", inputfin)
applyForm.home.addEventListener("keyup", inputfin)
applyForm.birth.addEventListener("keyup", inputfin)

function inputfin() {
    if (!(applyForm.name.value && applyForm.phone.value && applyForm.email.value && applyForm.home.value && applyForm.birth.value)) {
        $(".apply-button").prop("disabled", true);
    } else {
        $(".apply-button").prop("disabled", false);
    }
}

