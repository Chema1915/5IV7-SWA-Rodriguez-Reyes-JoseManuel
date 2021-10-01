
function validarm(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron = /[a-z ]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}

function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron = /[0-9]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}