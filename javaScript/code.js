document.addEventListener('DOMContentLoaded',function(){
    // Inputs
    let nombreCargaInput=document.getElementById('txtNombreCarga');
    let apellidoCargaInput=document.getElementById('txtApellidoCarga');
    let telefonoCargaInput=document.getElementById('txtTelefonoCarga');
    
    // Labels de error
    let msgerror1=document.getElementById('msgerror1');
    let msgerror2=document.getElementById('msgerror2');
    let msgerror3=document.getElementById('msgerror3');
    
    // Expresiones regulares
    const regExpNombre=/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s'-]+$/;
    const regExpApellido=/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s'-]+$/;
    const regExpTelefono=/^\(?(\d{3})\)?[-]?(\d{3})[-]?(\d{4})$/;
    
    // Eventos lanzadores de validacion en tiempo real
    nombreCargaInput.addEventListener('input',function() {
        validarInput(nombreCargaInput,regExpNombre,msgerror1);
    });
    apellidoCargaInput.addEventListener('input', function() {
        validarInput(apellidoCargaInput,regExpApellido,msgerror2);
    });
    telefonoCargaInput.addEventListener('input', function() {
        validarInput(telefonoCargaInput,regExpTelefono,msgerror3);
    });
    
    // Funcion que valida testea las expresiones regulares
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
    
    // Busqueda de cliente en administracion, en el apartado de modificar para cambiar un dueño.
    let inputModfAdmin, selectAdmin, optionsAdmin, i;
    inputModfAdmin = document.getElementById("inputAdminBusqueda");
    selectAdmin = document.getElementById("spinnerModificacionAdmin");
    optionsAdmin = selectAdmin.getElementsByTagName("option");
    
    // Evento lanzador de la busqueda para la modificacion
    inputModfAdmin.addEventListener('keyup',function(){
        let filtroAdmin = inputModfAdmin.value.toUpperCase();
        filtroOpciones(filtroAdmin,optionsAdmin,i);
    });
    
    // Busqueda de cliente en carga para asignar a dispositivo
    let inputCarga, selectCarga, optionsCarga, j;
    inputCarga = document.getElementById("inputCarga");
    selectCarga = document.getElementById("spinnerCarga");
    optionsCarga = selectCarga.getElementsByTagName("option");
    
    // Evento lanzador de la busqueda para la carga
    inputCarga.addEventListener('keyup',function(){
        let filtroCarga = inputCarga.value.toUpperCase();
        filtroOpciones(filtroCarga,optionsCarga,j);
    });
    
    // Filtro para ocultar o mostrar las opciones segun el filtro
    function filtroOpciones(filter,options,i){
        for (i = 0; i < options.length; i++) {
            if (options[i].text.toUpperCase().indexOf(filter) > -1) {
                options[i].classList.remove("ocultar"); // Muestra la opción
            } else {
                options[i].classList.add("ocultar");   // Oculta la opción
            }
        }
    };

    let inputListaPedido = document.getElementById("InputBusq")

    inputListaPedido.addEventListener("keyup",function(){
        let tablaListPedidos = document.getElementById("TablaListaPedidos");
        let filastablaPedidos = tablaListPedidos.getElementsByTagName("tr");
        filtroTabla(inputListaPedido.value.toLowerCase(),filastablaPedidos);
    });

    function filtroTabla(input, rows) {
        // const input = document.getElementById("InputBusq").value.toLowerCase();
        // const options = document.getElementById("Tabla");
        // const rows = table.getElementsByTagName("tr");
        
        // Iterar sobre cada fila (exceptuando la cabecera)
        for (let i = 1; i < rows.length; i++) {
            const cells = rows[i].getElementsByTagName("td");
            let found = false;
            
            // Buscar solo en las columnas de Nombre, Apellido y Teléfono
            for (let j = 0; j < 5; j++) {
                if (cells[j].innerText.toLowerCase().includes(input)) {
                    found = true;
                    break;
                }
            }
            
            // Mostrar u ocultar la fila dependiendo de la búsqueda
            rows[i].style.display = found ? "" : "none";
        }
    }
});

// function filtroOpciones() {
//     var input, filtro, select, options, i;
//     input = document.getElementById("inputBusquedaAdmin");
//     filtro = input.value.toUpperCase();
//     select = document.getElementById("spinner");
//     options = select.getElementsByTagName("option");

//     // Recorre todas las opciones y las muestra u oculta según el filtro
//     for (i = 0; i < options.length; i++) {
//         if (options[i].text.toUpperCase().indexOf(filter) > -1) {
//             options[i].classList.remove("ocultar"); // Muestra la opción
//         } else {
//             options[i].classList.add("ocultar");   // Oculta la opción
//         }
//     }
// }
// function validarInput(Input,RegExp,msgerror){
//     if (RegExp.test(Input.value)){
//         msgerror.style.display='none';
//         Input.style.borderColor='green';
//     }else{
//         msgerror.style.display='inline';
//         Input.style.display='red';
//     }
// }

// function filtroOpciones() {
//     var input, filtro, select, options, i;
//     input = document.getElementById("inputBusquedaAdmin");
//     filtro = input.value.toUpperCase();
//     select = document.getElementById("spinner");
//     options = select.getElementsByTagName("option");

//     // Recorre todas las opciones y las muestra u oculta según el filtro
//     for (i = 0; i < options.length; i++) {
//         if (options[i].text.toUpperCase().indexOf(filter) > -1) {
//             options[i].classList.remove("ocultar"); // Muestra la opción
//         } else {
//             options[i].classList.add("ocultar");   // Oculta la opción
//         }
//     }
// }
