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
    <section class="grid text-center ">
        <div class="caja position-absolute top-50 start-50 translate-middle p-5 border-success">
            <h1 class="">Inicio de Sesion</h1>
                <form action="user" action="svLogin" method="POST">
                    <label for="User">Usuario:</label><br>
                    <input type="text"placeholder="Usuario" class="textboxform" name="username"><br>
                    <label for="Contra">Clave:</label><br>  
                    <input type="password"placeholder="Clave" class="textboxform" name="pass"><br>
                    <br><!-- No funciona el boton de abajo -->
                    <!--
                    <button class="log_button" onclick="window.location.href='index.html'"> Iniciar Sesion </button>
                    -->
                    <a class="btn btn-light">Iniciar Sesion</a>
                </form>
        </div>
    </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>