function deal(obj,successUrl,errorUrl){
    if(1 != obj.code){
        sweetAlert({
            title: "OMG!",
            text: obj.message,
            type: "error"
        }, function () {
            location.href = errorUrl;
        });
    }else{
        sweetAlert({
            title: "Good!",
            text: obj.message,
            type: "success"
        }, function () {
            location.href = successUrl;
        });
    }
}