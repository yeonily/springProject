$(document).ready(function() {
    $('.summernote').summernote({
        height: 400,
        resize: false,
        lang: "ko-KR",
        // 에디터에 커서 이동
        focus : true,
        disableResizeEditor: true,
        toolbar: [ // 툴바 설정
            // 글꼴 설정
            ['fontname', ['fontname']],
            // 글자 크기 설정
            ['fontsize', ['fontsize']],
            // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            // 글자색
            ['color', ['forecolor','color']],
            ['table', ['table']],
            // 글머리 기호, 번호매기기, 문단정렬
            ['para', ['ul', 'ol', 'paragraph']],
            // 줄간격
            ['height', ['height']],
            ['insert', ['picture']],
            ['view', []]
        ],
        // 추가한 글꼴
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
        // 추가한 폰트사이즈
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],

        callbacks: {
            // 글자 수 체크
            onChange: function(contents, $editTable) {
                editorText = f_SkipTags_html(contents);
                editorText = editorText.replace(/\s/gi,""); //줄바꿈 제거
                editorText = editorText.replace(/&nbsp;/gi, ""); //공백제거
                $("#letter-length").text(editorText.length);
            },
            //여기 부분이 이미지를 첨부하는 부분
            onImageUpload: function (files) {
                RealTimeImageUpdate(files, this);
                console.log(files);
            }
        }
    });

    // 글자 수 체크 콜백 함수
    function f_SkipTags_html(input, allowed) {
        // 허용할 태그는 다음과 같이 소문자로 넘겨받습니다. (<a><b><c>)
        allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
        var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi,
            commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
        return input.replace(commentsAndPhpTags, '').replace(tags, function ($0, $1) {
            return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';
        });
    }

});