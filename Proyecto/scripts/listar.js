$(document).ready( () =>{
    const list =()=>{
        $.ajax({
            url: 'http://localhost:8081/odontologos',
            type:'GET',
            dataType:'json',
            success:function(rest) {
               let data = '';
                rest.forEach(element => {
                    data+= `
                    <tr>
                    <td>${element.id}</td>
                    <td>${element.matricula}</td>
                    <td>${element.nombre}</td>
                    <td>${element.apellido}</td>
                    </tr>
                    `
                });
    
                $('#tbodyOdontologo').html(data);
            }
        })
    }
    list()      
})
