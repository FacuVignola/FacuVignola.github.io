document.addEventListener('DOMContentLoaded',function(){
    let nombreCargaInput=document.getElementById('txtNombreCarga');
    let apellidoCargaInput=document.getElementById('txtApellidoCarga');
    let telefonoCargaInput=document.getElementById('txtTelefonoCarga');

    let msgerror1=document.getElementById('msgerror1');
    let msgerror2=document.getElementById('msgerror2');
    let msgerror3=document.getElementById('msgerror3');

    const regExpNombre=/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s'-]+$/;
    const regExpApellido=/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s'-]+$/;
    const regExpTelefono=/^\(?(\d{3})\)?[-]?(\d{3})[-]?(\d{4})$/;

    nombreCargaInput.addEventListener('input',function() {
        validarInput(nombreCargaInput,regExpNombre,msgerror1);
    });
    apellidoCargaInput.addEventListener('input', function() {
        validarInput(apellidoCargaInput,regExpApellido,msgerror2);
    })
    telefonoCargaInput.addEventListener('input', function() {
        validarInput(telefonoCargaInput,regExpTelefono,msgerror3);
    });

    function validarInput(Input,RegExp,msgerror)
    {
        if (RegExp.test(Input.value)){
            msgerror.style.display='none';
            Input.style.borderColor='green';
        } else {
            msgerror.style.display='inline';
            Input.style.borderColor='red';
        }
    }
});

// function validarInput(Input,RegExp,msgerror){
//     if (RegExp.test(Input.value)){
//         msgerror.style.display='none';
//         Input.style.borderColor='green';
//     }else{
//         msgerror.style.display='inline';
//         Input.style.display='red';
//     }
// }