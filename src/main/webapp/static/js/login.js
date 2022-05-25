window.onload=function(){
  
    $("#cif").blur(validarIfExist);
    $("#username").blur(validarIfExist);
};

function validarIfExist(e){
    let url="./api?accion=comprobar"+e.currentTarget.id+"&valor="+$(e.currentTarget).val();
    $.ajax({
        url:url,
        dataType: 'JSON',
        success:function(result){
            if(result.response){
                //alert(e.currentTarget.id+" ya existe");
                let selector="#"+e.currentTarget.id;
                $(selector).focus();
                $(selector).val("");
                $(selector).attr('placeholder',e.currentTarget.id+" ya existe");
                $("#btnRegistrarse").attr('disabled', true);
                $(selector).addClass("error");
            }else{
                $("#btnRegistrarse").attr('disabled', false);
                $(selector).removeClass("error");
            }
        },
        error:function(err){
            console.log(err);
        }
    });
}