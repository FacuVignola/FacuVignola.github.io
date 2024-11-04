<%-- 
    Document   : index
    Created on : 28/10/2024, 20:00:39
    Author     : Tacho
--%>

<%@page import="java.util.List"%>
<%@page import="com.logica.cliente"%>
<%@page import="com.persistencia.controladoraPersistencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <title> Menu </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="CSS/estilosindex.css">
</head>
<body>
    <% //controladoraPersistencia ctrlP = new controladoraPersistencia();%>
    <!--navbar Martin -->
    <nav class="navbar navbar-expand-lg mb-1 p-3 border-bottom border-success">
        <div class="container-fluid">
            <a href="#inicio" class="navbar-brand">Menu</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse p-2" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="#cargar" class="nav-link">Cargar pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a href="#adminCliente" class="nav-link">Administracion de clientes</a>
                    </li>
                    <li class="nav-item">
                        <a href="#adminDispositivo" class="nav-link">Administracion de dispositivos</a>
                    </li>
                    <li class="nav-item">
                        <a href="#lista" class="nav-link">Listas</a>
                    </li>
                    <!-- 
                    <li class="nav-item">
                        <a href="#obsoletos" class="nav-link">Pedidos obsoletos</a>
                    </li>
                    -->
                    <li class="nav-item">
                        <a href="login.jsp" class="nav-link">Cerrar Sesion</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <section id="Inicio">
        
    </section>
    
    <section id="cargar" class="p-3">
        <!--Formulario de carga Francisco -->
        <h2 class="p-1">Carga de datos</h2><br>
        <!-- Los input estan supuestos a cambiar -->
        <div class="container-lg">
            <div class="row d-flex justify-content-evenly">
                <div class="col-md-5 adminchargebox rounded p-5">
                    <form action="svClienteCrearBuscarN" method="POST" id="formCargarCliente">
                        <h3 class="mb-4">Datos del Cliente</h3>
                        <input type="text" class="form-control" placeholder="Nombre" name="nombreCarga" id="txtNombreCarga">
                        <label id="msgerror1" class="error">El nombre no es valido</label><br>
                        <input type="text" class="form-control" placeholder="Apellido" name="apellidoCarga" id="txtApellidoCarga">
                        <label id="msgerror2" class="error">El apellido no es valido</label><br>
                        <input type="text" class="form-control" placeholder="Telefono" name="telefonoCarga" id="txtTelefonoCarga">
                        <label id="msgerror3" class="error">El telefono no es valido</label><br>
                        <button type="submit" class="btn btn-light">Cargar cliente</button>
                        <div id="mensajeExito" style="display:none; color: green; margin-top: 10px;"></div>
                    </form>
                </div>
                <div class="col-md-5 adminchargebox rounded p-5">
                    <form action="" method="">
                        <h3 class="mb-4">Datos del Dispositivo</h3>
                        <input type="text" class="form-control mb-3" placeholder="Tipo de dispositivo" name="" id="">
                        <textarea class="form-control mb-3" name="" placeholder="Descripcion" id="" rows="3"></textarea>
                        <input type="text" class="form-control mb-2" id="inputCarga" placeholder="Asignar dueño del dispositivo">
                        <select id="spinnerCarga" class="form-select mb-3" size="5">
                            <option value="1">Juan</option>
                            <option value="2">Carlos</option>
                            <option value="3">Maria</option>
                        </select>
                        <button type="submit" class="btn btn-light">Cargar Dispositivo</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <section id="adminCliente" class="p-3">
        <!--Formularios de admin Facundo,Francisco y Martin-->
        <!-- Los input estan supuestos a cambiar -->
        <h2> Administracion de clientes</h2>
        <div class="container-lg">
            <div class="row justify-content-evenly">
                <div class="col-md-5 p-5 rounded adminchargebox">
                    <h3 class="mb-3">Busqueda</h3>
                    <form action="" method="">
                        <!-- <select class="form-select mb-3" id="inputGroupSelect01">
                            <option selected>Seleccione la tabla de busqueda</option>
                            <option value="1">Cliente</option>
                            <option value="2">Dispositivo</option>
                        </select>
                        <select class="form-select mb-3" id="inputGroupSelect01">
                            <option selected>Seleccione el dato de busqueda</option>
                            <option value="1">Nombre</option>
                            <option value="2">Apellido</option>
                            <option value="3">Dispositivo</option> 
                        </select>
                        <input type="text" class="form-control mb-3" name="" id="" placeholder="Inserte el dato"> -->
                        <input type="text" class="form-control mb-2" id="inputAdminBusquedaCliente" placeholder="Buscar cliente...">
                                <select id="spinnerAdminCliBusq" class="form-select mb-3" size="10">
                                    
                                    <option value="1">Este anda</option>
                                    <option value="2">Carlos</option>
                                    <option value="3">Maria</option>
                                    
                                </select>
                        <button type="button" class="btn btn-light">Buscar</button>
                    </form>
                </div>
                <div class="col-md-6 p-5 rounded adminchargebox">
                    <form action="" method="" class="container-fluid">
                        <div class="row">
                            <h3 class="mb-3">Registro</h3>
                            <div class="mb-3 input-group col-5">
                                <input type="text" aria-label="Nombre" class="form-control" placeholder="Nombre">
                                <input type="text" aria-label="Apellido" class="form-control" placeholder="Apellido">
                            </div>
                            <div class="mb-3 col-7">
                                <input type="text" class="form-control" placeholder="Telefono">
                            </div>
                            <!--
                            <div class="mb-3 col-9">
                                
                                <input type="text" class="form-control mb-2" id="inputModificarRegCliente" placeholder="Buscar cliente...">
                                <select id="spinnerModificacionRegCli" class="form-select mb-3" size="3">
                                    <option value="1">Juan</option>
                                    <option value="2">Carlos</option>
                                    <option value="3">Maria</option>
                                </select>
                                
                                <select class="form-select mb-3" id="inputGroupSelect01">
                                    <option selected>Estado del dispositivo</option>
                                    <option value="1">En reparacion</option>
                                    <option value="2">Listo</option>
                                    <option value="3">Obsoleto</option>
                                    <option value="4">Entregado</option>                                    
                                </select>
                            </div>
                                -->
                        </div>
                        <div class="pb-3">
                            <!--
                            <div class="input-group has-validation mb-3">
                                <textarea rows="2" cols="50" class="form-control" placeholder="Descripcion del problema"></textarea>
                            </div>
                            -->
                            <button type="button" class="btn btn-light">Modificar</button>
                            <button type="button" class="btn btn-light">Borrar</button>
                        </div>
                    </div>
                    
                </form>
            </div>
        </div>
    </section>
    <section id="adminDispositivo" class="p-3">
        <!--Formularios de admin Facundo,Francisco y Martin-->
        <!-- Los input estan supuestos a cambiar -->
        <h2> Administracion de dispositivo</h2>
        <div class="container-lg">
            <div class="row justify-content-evenly">
                <div class="col-md-5 p-5 rounded adminchargebox">
                    <h3 class="mb-3">Busqueda</h3>
                    <form action="" method="">
                        <!-- <select class="form-select mb-3" id="inputGroupSelect01">
                            <option selected>Seleccione la tabla de busqueda</option>
                            <option value="1">Cliente</option>
                            <option value="2">Dispositivo</option>
                        </select>
                        <select class="form-select mb-3" id="inputGroupSelect01">
                            <option selected>Seleccione el dato de busqueda</option>
                            <option value="1">Nombre</option>
                            <option value="2">Apellido</option>
                            <option value="3">Dispositivo</option> 
                        </select>
                        <input type="text" class="form-control mb-3" name="" id="" placeholder="Inserte el dato"> -->
                        <input type="text" class="form-control mb-2" id="inputAdminBusquedaDispositivo" placeholder="Buscar dispositivo...">
                                <select id="spinnerAdminDispBusq" class="form-select mb-3" size="10">
                                    
                                    <option value="1">impresora</option>
                                    <option value="2">telefono</option>
                                    <option value="3">Monitor</option>
                                    
                                </select>
                        <button type="button" class="btn btn-light">Buscar dispositivo</button>
                    </form>
                </div>
                <div class="col-md-6 p-5 rounded adminchargebox">
                    <form action="" method="" class="container-fluid">
                        <div class="row">
                            <h3 class="mb-3">Registro</h3>
                            <div class="mb-3 input-group col-5">
                                <input type="text" aria-label="Nombre" class="form-control" placeholder="Nombre">
                                <input type="text" aria-label="Apellido" class="form-control" placeholder="Apellido">
                            </div>
                            <div class="mb-3 col-7">
                                <input type="text" class="form-control" placeholder="Dispositivo">
                            </div>
                            <div class="mb-3 col-9">
                                <input type="text" class="form-control mb-2" id="inputModificarRegDisp" placeholder="Buscar dispositivo...">
                                <select id="spinnerModificarRegDisp" class="form-select mb-3" size="3">
                                    <option value="1">Juan</option>
                                    <option value="2">Carlos</option>
                                    <option value="3">Maria</option>
                                </select>
                                <select class="form-select mb-3" id="inputGroupSelect01">
                                    <option selected>Estado del dispositivo</option>
                                    <option value="1">En reparacion</option>
                                    <option value="2">Listo</option>
                                    <option value="3">Obsoleto</option>
                                    <option value="4">Entregado</option>                                    
                                </select>
                            </div>
                        </div>
                        <div class="pb-3">
                            <div class="input-group has-validation mb-3">
                                <textarea rows="2" cols="50" class="form-control" placeholder="Descripcion del problema"></textarea>
                            </div>
                            <button type="button" class="btn btn-light">Modificar</button>
                            <button type="button" class="btn btn-light">Borrar</button>
                        </div>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
</section>
<section id="lista" class="p-1">
    <!--Lista Pedidos/Cliente Facundo -->
    <h2 class="ps-2">Lista</h2>
    <div class="container mt-5">
        <input type="text" id="InputBusq" class="form-control rounded-2" placeholder="Buscar en la lista..."> <br>
        <table class="table table-striped" name="" id="TablaListaPedidos">
            <thead class="thead-dark">
                <tr class="border-bottom border-success">
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Dispositivo</th>
                    <th>Nro. de Telefono</th>
                    <th>Descripcion</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Juan</td>
                    <td>Perez</td>
                    <td>Telefono</td>
                    <td>3434343434</td>
                    <td>Pantalla rota</td>
                    <td>Reparacion</td>
                </tr>
                <tr>
                    <td>Ana</td>
                    <td>Garcia</td>
                    <td>Computadora</td>
                    <td>4343434343</td>
                    <td>Bateria no carga</td>
                    <td>Lista</td>
                </tr>
                <tr>
                    <td>Maria</td>
                    <td>Lopez</td>
                    <td>Tablet</td>
                    <td>53535353</td>
                    <td>Pantalla rota</td>
                    <td>Obsoleto</td>
                </tr>
                <tr>
                    <td>Carlos</td>
                    <td>Martinez</td>
                    <td>Telefono</td>
                    <td>1313131313</td>
                    <td>Altavoz no funciona</td>
                    <td>Lista</td>
                </tr>
            </tbody>
        </table>
    </div>
</section>
<!--
<section id="obsoletos" class="p-1">
    -->
    <!--Lista de Pedidos Obsoletos Facundo -->
    <!--
    <h2>Pedidos obsoletos</h2>
    <div class="container mt-5">
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr class="border-bottom border-success">
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Dispositivo</th>
                    <th>Problema</th>
                    
                </tr>
            </thead>
            <tbody>
                <tr class="">
                    <td>Pedro</td>
                    <td>Juarez</td>
                    <td>Notebook</td>
                    <td>Ruptura de pantalla</td>
                    
                </tr>
                <tr>
                    <td>Ana</td>
                    <td>Garcia</td>
                    <td>Computadora</td>
                    <td>Bateria no carga</td>
                </tr>
                <tr>
                    <td>Maria</td>
                    <td>Lopez</td>
                    <td>Tablet</td>
                    <td>Botones no responden</td>
                </tr>
                <tr>
                    <td>Carlos</td>
                    <td>Martinez</td>
                    <td>Telefono</td>
                    <td>Altavoz no funciona</td>
                </tr>
            </tbody>
        </table>
    </div>
</section>
-->

<script src="/javaScript/code.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="javaScript/code.js"></script>
</body>
</html>
