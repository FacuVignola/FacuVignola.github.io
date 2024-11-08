/* global fetch */

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
    
    // -------------------------------------------------------------------------------------
    // - - - - - - - - - - - - - - - - - BUSQUEDAS - - - - - - - - - - - - - - - - - - - - - 
    // -------------------------------------------------------------------------------------
    
    // Busqueda de cliente en carga para asignar a dispositivo
    let inputCarga, selectCarga, optionsCarga;
    inputCarga = document.getElementById("InputBusqClienteInCargaDisp");
    selectCarga = document.getElementById("spinnerBusqClienteInCargaDisp");
    optionsCarga = selectCarga.getElementsByTagName("option");
    
    // Evento lanzador de la busqueda para la carga
    inputCarga.addEventListener('keyup',function(){
        let filtroCarga = inputCarga.value.toUpperCase();
        filtroOpciones(filtroCarga,optionsCarga);
    });
    
    // Busqueda de cliente en administracion, en el apartado de busqueda de cliente
    
    let inputAdminBusquedaCliente, selectAdminCliBusq, optionsAdminBusqCli;
    inputAdminBusquedaCliente = document.getElementById("inputAdminBusquedaCliente");
    selectAdminCliBusq = document.getElementById("spinnerAdminCliBusq");
    optionsAdminBusqCli = selectAdminCliBusq.getElementsByTagName("option");
    
    // Evento lanzador de la busqueda para la modificacion
    
    inputAdminBusquedaCliente.addEventListener('keyup',function(){
        let filtroAdmin = inputAdminBusquedaCliente.value.toUpperCase();
        filtroOpciones(filtroAdmin,optionsAdminBusqCli);
    });
    
    /*
    // Busqueda de clientes en administracion de clientes, en el apartado de modificacion
    let inputModificarRegCli, selectModificarRegCli, optionModificarRegCli;
    inputModificarRegCli = document.getElementById("inputModificarRegCliente");
    selectModificarRegCli = document.getElementById("spinnerModificacionRegCli");
    optionModificarRegCli = selectModificarRegCli.getElementsByTagName("option");
    
    //Evento lanzador de la busqueda para la modificacion del registro
    inputModificarRegCli.addEventListener('keyup',function(){
       let filtroReg = inputModificarRegCli.value.toUpperCase(); 
       filtroOpciones(filtroReg,optionModificarRegCli);
    });
    */
    
    // Busqueda de dispositivos en administracion, en el apartado de busqueda de dispositivos
    let inputAdminBusquedaDisp,selectAdminDispBusq,optionAdminDispBusq;
    inputAdminBusquedaDisp = document.getElementById("inputAdminBusquedaDispositivo");
    selectAdminDispBusq = document.getElementById("spinnerAdminDispBusq");
    optionAdminDispBusq = selectAdminDispBusq.getElementsByTagName("option");
    
    //Evento lanzador
    inputAdminBusquedaDisp.addEventListener('keyup',function(){
       let filtroReg = inputAdminBusquedaDisp.value.toUpperCase();
       filtroOpciones(filtroReg,optionAdminDispBusq);
    });
    
    //Busqueda de cliente en administracion de dispositivos, apartado de modificacion de dispositivos
    let inputModificarRegDisp, selectModificarRegDisp, optionModificarRegDisp;
    inputModificarRegDisp = document.getElementById("inputModificarRegDisp");
    selectModificarRegDisp = document.getElementById("spinnerModificarRegDisp");
    optionModificarRegDisp = selectModificarRegDisp.getElementsByTagName("option");
    
    //Evento lanzador
    inputModificarRegDisp.addEventListener('keyup', function(){
        let filtroReg = inputModificarRegDisp.value.toUpperCase();
        filtroOpciones(filtroReg,optionModificarRegDisp);
    });
    
    // Filtro para ocultar o mostrar las opciones segun el filtro
    function filtroOpciones(filter,options){
        for (i = 0; i < options.length; i++) {
            if (options[i].text.toUpperCase().indexOf(filter) > -1) {
                options[i].classList.remove("ocultar"); // Muestra la opción
            } else {
                options[i].classList.add("ocultar");   // Oculta la opción
            }
        }
    };

    let inputListaPedido = document.getElementById("InputBusq");

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
            
            // Buscar solo en las columnas de Nombre, Apelliddo, Telefono, Dispositivo, Descripcion y Estado. 
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
    
    function CargarListaClientes(selectId){
        fetch("/ProyectoPracticas/svClienteCrearBuscarN")
                .then(response => response.json())
                .then(data =>{
                    const select = document.getElementById(selectId);
                    select.innerHTML='';
                
                data.forEach(cliente => {
                    const option = document.createElement('option');
                    option.value = cliente.id;
                    option.textContent = `${cliente.nombre} - ${cliente.apellido} - ${cliente.telefono}`;
                    select.appendChild(option);
                });         
        });
    }
    
    CargarListaClientes("spinnerCarga");
    CargarListaClientes("spinnerAdminCliBusq");
    CargarListaClientes("spinnerModificacionRegCli");
    CargarListaClientes("spinnerAdminDispBusq");
    CargarListaClientes("spinnerModificarRegDisp");
    
    document.getElementById("formCargarCliente").addEventListener('submit',function(event) {
        event.preventDefault();
        
        if (!nombreCargaInput.value || !apellidoCargaInput.value || !telefonoCargaInput.value) {
            alert("Por favor, complete todos los campos.");
            return;
        }
        
        const nombre = nombreCargaInput.value;
        const apellido = apellidoCargaInput.value;
        const telefono = telefonoCargaInput.value;
        
        fetch('/ProyectoPracticas/svClienteCrearBuscarN',{
                method: 'POST',
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify({nombre, apellido, telefono})
            })
                .then(response => {
                    if(response.ok){
                        CargarListaClientes("spinnerCarga");
                        CargarListaClientes("spinnerAdminCliBusq");
                        CargarListaClientes("spinnerModificacionRegCli");
                        CargarListaClientes("spinnerAdminDispBusq");
                        CargarListaClientes("spinnerModificarRegDisp");
                        document.getElementById("formCargarCliente").reset();
                        let msgexito = document.getElementById("msgExitoCli");
                        msgexito.style.display="block";
                        setTimeout(() => {
                            msgexito.style.display="none";
                        }, 3000);
                        
                    }
                })
                .catch(error => {
                    console.error('Error', error );
                    alert("Hubo un problema al cargar al cliente: "+ error.message );
                });  
    });
    
    const BotonBuscarAdminCliente = document.getElementById("BotonBuscarAdminCliente");
    
    BotonBuscarAdminCliente.disabled=true;
    
    selectAdminCliBusq.addEventListener('change',() => {
        /*
        if(selectAdminCliBusq.value){
            BotonBuscarAdminCliente.disabled=false;
        } else {
            BotonBuscarAdminCliente.disabled=true;
        }
        */
        selectAdminCliBusq.value !== null ? BotonBuscarAdminCliente.disabled=false : BotonBuscarAdminCliente.disabled=true;
    });
    
    //Inputs de modificacion de registro en el apartado de admin de clientes
    const inputModifClienteNombre = document.getElementById("inputModifClienteNombre");
    const inputModifClienteApellido = document.getElementById("inputModifClienteApellido");
    const inputModifClienteTelefono = document.getElementById("inputModifClienteTelefono");
    
    //Cajas de texto desabilitadas al cargar la pagina de admin clientes
    inputModifClienteNombre.disabled=true;
    inputModifClienteApellido.disabled=true;
    inputModifClienteTelefono.disabled=true;
    
    //Variable que guarda la id del cliente buscado
    let idclienteBusq='';
    
    //Funcion que busca y trae los registros de clientes para su modificacion
    document.getElementById("formAdminBusqCliente").addEventListener('submit',(event) => {
        //Previene que se recargue la pagina
        event.preventDefault();
        
        idclienteBusq = selectAdminCliBusq.value;
        
        //Verifica si la variable idcliente posee un valor
        if(idclienteBusq)
        fetch("/ProyectoPracticas/svClienteEditFind?id=" + idclienteBusq )
        .then(response => response.json())
        .then(cliente => {
            if(cliente){
                //Cargar cajas de texto
                inputModifClienteNombre.value = cliente.nombre;
                inputModifClienteApellido.value = cliente.apellido;
                inputModifClienteTelefono.value = cliente.telefono;
                
                //Habilitar Cajas de texto
                inputModifClienteNombre.disabled=false;
                inputModifClienteApellido.disabled=false;
                inputModifClienteTelefono.disabled=false;
                
                //Desabilitar boton de buscador y resetear valor del select
                BotonBuscarAdminCliente.disabled=true;
                selectAdminCliBusq.value=null;
                
                //Habilitar botones de Modificar y Borrar registro
                botonModifCliente.disabled=false;
                botonBorrarCliente.disabled=false;
            }
            else
            {
                alert("cliente no encontrado");
            }
        })
        .catch(error => {
            console.error('error: ',error);
            alert("Hubo un error al obtener los datos");
        });
    });
    
    const botonModifCliente = document.getElementById("botonModifCliente");
    botonModifCliente.disabled=true;
    
    const botonBorrarCliente = document.getElementById("botonBorrarCliente");
    botonBorrarCliente.disabled=true;
    
    const formAdminCliente = document.getElementById("formAdminCliente");
    
    formAdminCliente.addEventListener('submit', (event) => {
       event.preventDefault();
       
       if (!inputModifClienteNombre.value || !inputModifClienteApellido.value || !inputModifClienteTelefono.value) {
            alert("Por favor, complete todos los campos.");
            return;
        }
       
       const id = idclienteBusq;
       const nombre = inputModifClienteNombre.value;
       const apellido = inputModifClienteApellido.value;
       const telefono = inputModifClienteTelefono.value;
       
       const accion = event.submitter.value;
       
       let url='';
       let metodo='';
       
       if(accion==="modificar"){
           url="/ProyectoPracticas/svClienteEditFind";
           metodo="POST";
       }
       else if(accion==="borrar"){
           url="/ProyectoPracticas/svClienteDestroy";
           metodo="POST";
       }
       
        fetch(url,{
            method: metodo,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({id,nombre,apellido,telefono})
        })
                .then(response => response.json())
                .then(datos => {
                    if(datos.success) {
                        alert(accion + " realizado con exito");
                        alert(id + nombre + apellido + telefono);
                        formAdminCliente.reset();
                        botonModifCliente.disabled=true;
                        botonBorrarCliente.disabled=true;
                        CargarListaClientes("spinnerCarga");
                        CargarListaClientes("spinnerAdminCliBusq");
                        CargarListaClientes("spinnerModificacionRegCli");
                        CargarListaClientes("spinnerAdminDispBusq");
                        CargarListaClientes("spinnerModificarRegDisp");
                    }
                    else {
                        alert("Hubo un error al realizar la operacion");
                    }
        })
                .catch(error => {
                    console.error('error: ',error);
                    alert("Hubo un problema al enviar los datos");
        });
    });
    
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
