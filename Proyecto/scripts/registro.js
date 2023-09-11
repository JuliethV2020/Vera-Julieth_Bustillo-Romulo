window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán los datos que el usuario cargará del nuevo odontologo
    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = {
            matricula: document.querySelector('#inputMatricula').value,
            nombre: document.querySelector('#inputNombre').value,
            apellido: document.querySelector('#inputApellido').value,

        };
        //invocamos utilizando la función fetch la API clinica con el método POST que guardara el odontologo que enviaremos en formato JSON
        const url = 'http://localhost:8081/odontologos/registrar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 //Si no hay ningun error se muestra un mensaje diciendo que el odontologo se agrego
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Odontologo agregado correctamente </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                //Si hay algun error se muestra un mensaje diciendo que el odontologo no se pudo guardar
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos
                     resetUploadForm();
                    })
    });


    function resetUploadForm(){
        document.querySelector('#inputMatricula').value = "";
        document.querySelector('#inputNombre').value = "";
        document.querySelector('#inputApellido').value = "";

    }
});