<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/estilos.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Inicio de Sesion</title>
</head>
<!--Login Facundo y Martin -->
<body class="bodybg">
    <header class="pt-5 pb-5 headerbg">
    </header>
    <section class="cajas grid text-center ">
        <div class="caja position-absolute top-50 start-50 translate-middle p-5 border-success">
            <h1 class="border-bottom border-3">Inicio de Sesion</h1>
                <form action="svLogin" method="POST">
                    <label for="User" class="fs-5">Usuario:</label><br>
                    <input type="text"placeholder="Usuario" class="textboxform form-control" name="username"><br>
                    <label for="Contra" class="fs-5">Clave:</label><br>  
                    <input type="password"placeholder="Clave" class="textboxform form-control" name="pass"><br>
                    <br>
                    
                    <button type="submit" class="btn btn-light">Iniciar Sesion</a>
                </form>
        </div>
    </section>
     <% 
        Boolean validacion = (Boolean) session.getAttribute("validacion");
        if (validacion != null && !validacion) { 
    %>
        <script>
            alert("Usuario o Contraseña Incorrecta");
        </script>
    <% 
            session.removeAttribute("validacion");
        } 
    %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>