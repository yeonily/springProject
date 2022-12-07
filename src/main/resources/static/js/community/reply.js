let replyService = (function () {
    function test(callback) {
        if(callback){
            callback("ABC");
        }
    }
    return {test: test}
})();