

const abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
    'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
    'w', 'x', 'y', 'z'];

//llave
let key = "";


$(document).ready(function () {
    $("#ci").click(function () {

        //para cifrar vamos a usar la funcion
        // y = (x+z)mod27 pq estamos usando la ñ

        //vamos a traer los datos de los campos de texto
        key = document.getElementById('llave').value;
        //vamos a verificar los datos
        key = key.replace(/ /g, '');

        //obtener el mensaje
        let mess = document.getElementById('mess').value;

        mess = mess.replace(/ /g, '');

        let newMess = "";

        let keyComplete = "";

        //algoritmo

        if (revision(mess, key)) {

            for (var i = 0; i < mess.length; i++) {
                keyComplete += key.charAt((i % Number(key.length)));
            }


            for (var i = 0; i < mess.length; i++) {
                //obtener la poscion de la letra por letra del mensaje
                let charr = mess.charAt(i);
                let posm = getPosition(charr);

                charr = keyComplete.charAt(i);
                let posk = getPosition(charr);

                //ejecutamos el algoritmo

                let newVal = change(posm, posk);

                newMess += abc[newVal];  //mensaje cifrado
            }
            //imprimir el resultado
            document.getElementById('rs').value = newMess;
        } else {
          document.getElementById('rs').value = "Error";
        }


    });
    $("#de").click(function () {
        alert("WTF");
        //para cifrar vamos a usar la funcion
        // y = (x+z)mod27 pq estamos usando la ñ

        //vamos a traer los datos de los campos de texto
        key = document.getElementById('llave').value;
        //vamos a verificar los datos
        key = key.replace(/ /g, '');

        //obtener el mensaje
        let mess = document.getElementById('mess').value;

        mess = mess.replace(/ /g, '');

        let newMess = "";

        let keyComplete = "";

        //algoritmo

        if (revision(mess, key)) {

            for (var i = 0; i < mess.length; i++) {
                keyComplete += key.charAt((i % Number(key.length)));
            }


            for (var i = 0; i < mess.length; i++) {
                //obtener la poscion de la letra por letra del mensaje
                let charr = mess.charAt(i);
                let posm = getPosition(charr);

                charr = keyComplete.charAt(i);
                let posk = getPosition(charr);

                //ejecutamos el algoritmo

                let newVal = rechange(posm, posk);

                newMess += abc[newVal];  //mensaje decifrado
            }
            //imprimir el resultado
            document.getElementById('rs').value = newMess;
        } else {
            //aqui es si no se cumple las condiciones
        }


    });

});

//cambio

function change(posm, posk) {
    //aplicamos y = (x+z)mod27
    let y = (posm + posk) % 27;
    return y;
}

function rechange(posm, posk) {
    let val = 0;
    if ((posm - posk) >= 0) {
        val = (posm + posk) % 27;
    } else {
        val = (posm - posk + 27) % 27;
    }
    return val;
}

function getPosition(letra) {
    let position = abc.indexOf(letra);
    return position;
}

function revision(mess, desp) {
    //validar la entrada de los datos
    //expresion regular
    const re = /^([a-zñ?]+([]*[a-zñ?]?['-]?[a-zñ?]+)*)$/

    var acc = true;

    if (!re.test(mess)) {
        sd();
        acc = false;
    }
    if (!re.test(desp)) {
        sdd();
        acc = false;
    }
    if (desp.length > mess.length) {
        sz();
    }
    return acc;
}

function sd() {
    //alert para decir que el texto no ha sido aceptado
   

    alert("El texto ingreso no ha sido aceptado, ingrese solo minuscUlas y evite numeros y simbolos");

}


function sdd() {
    //alert para decir que el texto no ha sido aceptado
    

    alert("La clave ingresa es incorrecta, no cumple con las normas de solo minusculas y no usar numeros y/o simbolos");
}

function sz() {
    //alert para decir que el texto no ha sido aceptado
    

    alert("La clave no puede ser mayor que el mensaje");
}