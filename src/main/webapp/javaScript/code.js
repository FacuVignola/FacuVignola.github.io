/* global fetch */

document.addEventListener('DOMContentLoaded',function(){
    // Inputs carga clientes
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
            for (let j = 0; j < 6; j++) {
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
    
    CargarListaClientes("spinnerBusqClienteInCargaDisp");
    CargarListaClientes("spinnerAdminCliBusq");
    CargarListaClientes("spinnerModificacionRegCli");
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
                        CargarListaClientes("spinnerBusqClienteInCargaDisp");
                        CargarListaClientes("spinnerAdminCliBusq");
                        CargarListaClientes("spinnerModificacionRegCli");
                        CargarListaClientes("spinnerModificarRegDisp");
                        cargarLista();
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
    
    function CargarListaDispositivos(selectId){
        fetch('/ProyectoPracticas/svDispositivoCrearBuscar')
                .then(response => response.json())
                .then(data => {
                    const select = document.getElementById(selectId);
                    select.innerHTML='';
                    
                    data.forEach(dispositivo =>{
                        const option = document.createElement('option');
                        option.value=dispositivo.id;
                        option.textContent = `${dispositivo.tipo} - ${dispositivo.modelo} - ${dispositivo.nombre} - ${dispositivo.apellido}`;
                        select.appendChild(option);
                    });
                });
    }
    
    //Inputs carga de dispositivos
    let tipoCarga=document.getElementById("txtTipoCarga");
    let modeloCarga=document.getElementById("txtModeloCarga");
    let descripcionCarga=document.getElementById("txtDescCarga");

    CargarListaDispositivos("spinnerAdminDispBusq");
    
    let idDispBusq;
    let idClienteInDisp;
    let idPedidoInDisp;
    
    //Carga de dispositivos
    document.getElementById("formCargarDispositivo").addEventListener('submit',function(event) {
        event.preventDefault();
        
        if (!tipoCarga.value || !modeloCarga.value || !descripcionCarga.value || !selectCarga.value) {
            alert("Por favor, complete todos los campos.");
            return;
        }
        
        //Dar valor a variables con los inputs de carga dispositivos
        const tipo = tipoCarga.value;
        const modelo = modeloCarga.value;
        const descripcion = descripcionCarga.value;
        const dueno = selectCarga.value;
        
        //Solicitar enviar los datos a traves de url
        fetch('/ProyectoPracticas/svDispositivoCrearBuscar',{
                method:'POST',
                headers:{
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify([ tipo, modelo, descripcion, dueno ])
            })
                .then(response => {
                    if(response.ok){
                        //Actualiza las listas de dispositivos
                        CargarListaDispositivos("spinnerAdminDispBusq");
                        //Actualiza la tabla de pedidos
                        cargarLista();
                        //Vacia los inputs del formulario de carga de dispositivos
                        document.getElementById("formCargarDispositivo").reset();
                        //Mensaje de Carga exitoso
                        const msgExitoDisp = document.getElementById("msgExitoDisp");
                        msgExitoDisp.style.display="block";
                        setTimeout(() => {
                            msgExitoDisp.style.display="none";
                        }, 3000 );
                        //alert("Dispositivo cargado exitosamente!");
                    }
                })
                .catch(error => {
                    console.error('Error', error );
                    alert("Hubo un problema al cargar al dispositivo: " + error.message );
                });  
    });
    //Boton para busqueda de clientes en el apartado de admin de clientes
    const BotonBuscarAdminCliente = document.getElementById("BotonBuscarAdminCliente");
    
    BotonBuscarAdminCliente.disabled=true;
    
    selectAdminCliBusq.addEventListener('change',() => {
        selectAdminCliBusq.value !== null ? BotonBuscarAdminCliente.disabled=false : BotonBuscarAdminCliente.disabled=true;
    });
    
    //Inputs de modificacion de registro en el apartado de admin de clientes
    const inputModifClienteNombre = document.getElementById("inputModifClienteNombre");
    const inputModifClienteApellido = document.getElementById("inputModifClienteApellido");
    const inputModifClienteTelefono = document.getElementById("inputModifClienteTelefono");
    
    //Labels de error para los input de admin de cliente
    const msgerror4=document.getElementById('msgerror4');
    const msgerror5=document.getElementById('msgerror5');
    const msgerror6=document.getElementById('msgerror6');
    
    //Validacion inputs en admin de clientes
    inputModifClienteNombre.addEventListener('input',function() {
        validarInput(nombreCargaInput,regExpNombre,msgerror4);
    });
    
    inputModifClienteApellido.addEventListener('input',function() {
        validarInput(nombreCargaInput,regExpApellido,msgerror5);
    });
    
    inputModifClienteTelefono.addEventListener('input',function() {
        validarInput(nombreCargaInput,regExpTelefono,msgerror6);
    });
    
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
        fetch("/ProyectoPracticas/svClienteEditFind?id=" + idclienteBusq)
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
           url="/ProyectoPracticas/svClienteEditFind?id="+id;
           metodo="POST";
       }
       else if(accion==="borrar"){
           url="/ProyectoPracticas/svClienteDestroy?id="+id;
           metodo="POST";
       }
       
        fetch(url,{
            method: metodo,
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify([ nombre, apellido, telefono ])
        })
                .then(response => response.json())
                .then(datos => {
                    if(datos.success) {
                        //alert(accion + " realizado con exito");
                        
                        //Mensaje de exito de modificaion de cliente 
                        const msgExitoAdminCli = document.getElementById("msgExitoAdminCli");
                        msgExitoAdminCli.textContent=accion+" realizado con exito";
                        msgExitoAdminCli.style.display="block";
                        setTimeout(() => {
                            msgExitoAdminCli.style.display="none";
                        }, 3000 );
                        
                        //Se vacian los input de la modificacion de clientes.
                        formAdminCliente.reset();
                        
                        //Se deshabilitan los input de modificacion de cliente
                        inputModifClienteNombre.disabled=true;
                        inputModifClienteApellido.disabled=true;
                        inputModifClienteTelefono.disabled=true;
                        
                        //Se deshabilitan los botones de modificacion y borrar clientes
                        botonModifCliente.disabled=true;
                        botonBorrarCliente.disabled=true;
                        
                        //Actualiza las listas 
                        CargarListaClientes("spinnerBusqClienteInCargaDisp");
                        CargarListaClientes("spinnerAdminCliBusq");
                        CargarListaClientes("spinnerModificacionRegCli");
                        CargarListaClientes("spinnerModificarRegDisp");
                        CargarListaDispositivos("spinnerAdminDispBusq");
                        cargarLista();
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
    
    //Carga la lista de pedidos apenas se entre en la pagina.
    cargarLista();
    
    //Tabla de carga de registros de pedidos con tablas relacionadas (cliente,dispositivo y pedido)
    function cargarLista() {
    fetch('/ProyectoPracticas/svLista')
        .then(response => response.json())
        .then(data => {
            //console.log(data);
            const tabla = document.getElementById('TablaListaPedidos').getElementsByTagName('tbody')[0];
            tabla.innerHTML = ''; // Limpiar contenido de la tabla
            // Recorrer los datos y crear filas
            data.forEach(pedido => {
                const fila = tabla.insertRow();
                fila.innerHTML = `
                    <td>${pedido.nombre}</td>
                    <td>${pedido.apellido}</td>
                    <td>${pedido.tipo}</td>
                    <td>${pedido.telefono}</td>
                    <td>${pedido.descripcion}</td>
                    <td>${pedido.estado}</td>
                `;
            });
                     
             
        })
        .catch(error => console.error('Error al cargar pedidos:', error));
    }
    
    //Select de busqueda de dispositivos.
    const spinnerAdminDispBusq = document.getElementById("spinnerAdminDispBusq");
    
    //Boton de modificar registro de dispositivo.
    const btnModifDisp = document.getElementById("btnModifDisp");
    btnModifDisp.disabled=true;
    
    //Boton de borrar registro de dispositivo
    const btnBorrarDisp = document.getElementById("btnBorrarDisp");
    btnBorrarDisp.disabled=true;
    
    //Boton de busqueda de registro de dispositivo
    const btnBusqDisp = document.getElementById("btnBusqDisp");
    btnBusqDisp.disabled=true;
    
    //Evento que deshabilita y habilita el boton de buscar registro.
    spinnerAdminDispBusq.addEventListener('change',() => {
        spinnerAdminDispBusq.value !== null ? btnBusqDisp.disabled=false : btnBusqDisp.disabled=true;
    });
    
    //Formulario de busquedaa de dispositivo
    const formBusqDispositivo = document.getElementById("formBusqDispositivo");
    
    //Input y selects del formulario de modificacion de registro.
    let inputTipoDisp = document.getElementById("inputTipoDisp");
    let inputModeloDisp = document.getElementById("inputModeloDisp");
    let spinnerEstadoPed = document.getElementById("spinnerEstadoPed");
    let inputModifDesc = document.getElementById("inputModifDesc");
    let spinnerModificarRegDisp = document.getElementById("spinnerModificarRegDisp");
    
    //Formulario que trae el dispositivo seleccionado y trae los datos relacionados al formulario regsitro adyacente.
    formBusqDispositivo.addEventListener('submit',(event) => {
        //Previene que se recargue la pagina
        event.preventDefault();
        
        idDispBusq = spinnerAdminDispBusq.value;
        
        //Verifica si la variable idDisp posee un valor
        if(idDispBusq)
        fetch("/ProyectoPracticas/svDispositivoEditFind?id=" + idDispBusq)
        .then(response => response.json())
        .then(datos => {
            if(datos){
                datos.forEach( pedido =>{
                //Cargar cajas de texto
                inputTipoDisp.value = pedido.tipo;
                inputModeloDisp.value = pedido.modelo;
                inputModifDesc.value = pedido.descripcion;
                
                //id de pedido y cliente
                idPedidoInDisp = pedido.pedido_id;
                idClienteInDisp = pedido.cliente_id;
                
                //Se le da el nombre y apellido del dueño del dispositivo al input de buscador en
                //registro de dispositivos
                document.getElementById("inputModificarRegDisp").value = pedido.nombre + " - " + pedido.apellido;
                /*
                for(i=0;i<spinnerModificarRegDisp.options.length;i++)
                {
                    const opcion = spinnerModificarRegDisp.options[i];
                    
                    if(opcion.value === pedido.cliente_id)
                    {
                        opcion.selected = true;
                    }
                }
                */
                
                //Busca que opcion tiene el mismo valor que pedido.estado.
                
                for(i=0;i<spinnerEstadoPed.options.length;i++)
                {
                    const opcion2 = spinnerEstadoPed.options[i];
                    
                    if(opcion2.value === pedido.estado)
                    {
                        opcion2.selected=true;
                    }
                }
                
                });
                //Habilitar Cajas de texto
                inputTipoDisp.disabled=false;
                inputModeloDisp.disabled=false;
                inputModifDesc.disabled=false;
                inputModificarRegDisp.disabled=false;
                spinnerModificarRegDisp.disabled=false;
                spinnerEstadoPed.disabled=false;
                
                //Desabilitar boton de buscador y resetear valor del select
                btnBusqDisp.disabled=true;
                spinnerAdminDispBusq.value=null;
                
                //Habilitar botones de Modificar y Borrar registro
                btnModifDisp.disabled=false;
                btnBorrarDisp.disabled=false;
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
    
    //Formulario de modificar dispositivos
    const formModifDispositivo = document.getElementById("formModifDispositivo");
    
    //Deshabilita todos los inputs del form de modificar registros de dispositivo entrando a la pagina.
    inputTipoDisp.disabled=true;
    inputModeloDisp.disabled=true;
    inputModifDesc.disabled=true;
    inputModificarRegDisp.disabled=true;
    spinnerModificarRegDisp.disabled=true;
    spinnerEstadoPed.disabled=true;
    btnModifDisp.disabled=true;
    btnBorrarDisp.disabled=true;
    
    //Formulario de envio de datos en modificar o borrar registros
    formModifDispositivo.addEventListener('submit', (event) => {
       event.preventDefault();
       //Verifica que todos los inputs tengan valores
       if (!inputTipoDisp.value || !inputModeloDisp.value || !inputModifDesc.value || !spinnerModificarRegDisp.value ||!spinnerEstadoPed.value) {
            alert("Por favor, complete todos los campos.");
            return;
        }
       // Aplicar los valores de los inputs y selects a variables.
       const id = idDispBusq;
       const tipo = inputTipoDisp.value;
       const modelo = inputModeloDisp.value;
       const descripcion = inputModifDesc.value;
       const idcliente = spinnerModificarRegDisp.value;
       const idpedido = idPedidoInDisp;
       const estado = spinnerEstadoPed.value;
       
       //alert("iddisp: " + id + ", tipodisp: " + tipo + ", modelo: " + modelo + ", desc: " + descripcion + ", clienteid: " + idcliente + ", pedidoid: " + idpedido + ", estado: " + estado );
       
       //Variable que determina segun el boton presionado que accion tomar, modificar o borrar.
       const accion = event.submitter.value;
       
       let url='';
       let metodo='';
       // Define variables para llamar al servlet de modificar
       if(accion==="modificar"){
           url="/ProyectoPracticas/svDispositivoEditFind?id="+id;
           metodo="POST";
       } // Define variables para llamar al servlet de borrar
       else if(accion==="borrar"){
           url="/ProyectoPracticas/svDispositivoDestroy?id="+id;
           metodo="POST";
       }
        //Conexion con el servlet y datos a enviar mediante json
        fetch(url,{
            method: metodo,
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify([ tipo, modelo, descripcion, idcliente, idpedido, estado ])//Array de valores.
        })
                .then(response => response.json())
                .then(datos => {
                    if(datos.success) {
                        //alert(accion + " realizado con exito");
                        
                        //Mensaje de exito al modificar/borrar el registro.
                        const msgExitoAdminDisp = document.getElementById("msgExitoAdminDisp");
                        msgExitoAdminDisp.textContent=accion+" realizado con exito";
                        msgExitoAdminDisp.style.display="block";
                        setTimeout(() => {
                            msgExitoAdminDisp.style.display="none";
                        }, 3000 );
                        
                        //Resetear valores de las cajas y selects del form
                        formModifDispositivo.reset();
                        
                        //deshabilitar los inputs y selects del form modif
                        inputTipoDisp.disabled=true;
                        inputModeloDisp.disabled=true;
                        inputModifDesc.disabled=true;
                        inputModificarRegDisp.disabled=true;
                        spinnerModificarRegDisp.disabled=true;
                        spinnerEstadoPed.disabled=true;
                        
                        //Habilitar el boton de busqueda en form disp
                        btnBusqDisp.disabled=false;
                        
                        //Deshabilitar los botones del form modif
                        btnModifDisp.disabled=true;
                        btnBorrarDisp.disabled=true;
                        
                        //Actualizar valores de selects.
                        CargarListaClientes("spinnerBusqClienteInCargaDisp");
                        CargarListaClientes("spinnerAdminCliBusq");
                        CargarListaClientes("spinnerModificacionRegCli");
                        CargarListaClientes("spinnerModificarRegDisp");
                        CargarListaDispositivos("spinnerAdminDispBusq");
                        
                        //Actualizar tabla de pedidos
                        cargarLista();
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