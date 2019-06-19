$().ready(function(){
    $("#usrForm").validate({
        rules:{
            vnaam:"required",
            anaam:"required",
            confirm_pwd:{
                    required:true,
                    minlength:8,
                    equalTo:("#pwd")
                }
        },
        messages:{}
    })


})