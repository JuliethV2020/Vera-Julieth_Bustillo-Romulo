window.addEventListener('load', function () {
    /* ---------------------- obtenemos variables globales ---------------------- */
    const form = document.forms[0];
    const matricula = document.querySelector('#inputMatricula');
    const nombre = document.querySelector('#inputNombre');
    const apellido = document.querySelector('#inputApellido');
    
    const url = 'http://localhost:8081/odontologos';

    /* -------------------------------------------------------------------------- */
    /*            FUNCIÓN 1: Escuchamos el submit y preparamos el envío           */
    /* -------------------------------------------------------------------------- */
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        //creamos el cuerpo de la request
        const payload = {
            matricula: matricula.value,
            firstName: nombre.value,
            lastName: apellido.value
            
        };
        //configuramos la request del Fetch
        const settings = {
            method: 'POST',
            body: JSON.stringify(payload),
            headers: {
                'Content-Type': 'application/json'
            }
        };
        //lanzamos la consulta de login a la API
        realizarRegister(settings);

        //limpio los campos del formulario
        form.reset();
    });
    

    /* -------------------------------------------------------------------------- */
    /*                    FUNCIÓN 2: Realizar el signup [POST]                    */
    /* -------------------------------------------------------------------------- */
    function realizarRegister(settings) {
        console.log("Lanzando la consulta a la API");
        fetch(`${url}/registrar`, settings)
            .then(response => {
                console.log(response);

                if (response.ok != true) {
                    alert("Alguno de los datos es incorrecto.")
                }

                return response.json();

            })
            .then(data => {
                console.log("Promesa cumplida:");
                console.log(data);

                if (data.jwt) {
                    //guardo en LocalStorage el objeto con el token
                    localStorage.setItem('jwt', JSON.stringify(data.jwt));

                    //redireccionamos a la página
                    location.replace('/bienvenida.html');
                }
                
            }).catch(err => {
                console.log("Promesa rechazada:");
                console.log(err);
            })
    };

});