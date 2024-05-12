

//evento, cuando todo se alla cargado ejecuta esa función
     
    	function llamada(){
    		//como parámetro ponemos el nombre del Servlet que queremos leer
    		fetch('userManage?op=1')
    		//con la respuesta(response) del servidor la pasamos a json
    		.then(response => response.json())
    		//los datos que recibimos(data) llame a una función pintarTablaUsuarios
    		.then(data => pintarTablaUsuarios(data))
    	}//fin llamada()
    	
    	
    	function pintarTablaUsuarios(datosUsuarios){
    		console.log(datosUsuarios);
    		
    		let html = "<table id='tablaUsuarios'>";
    		html += "<h4>"+"CABECERA TABLA"+"</h4>";
    		html += "<tr><th>id</th><th>nombreUser</th><th>email</th><th>passwordUser</th>"+
    		"<th>imgUser</th><th>Permiso</th><th>descripcionPerfil</th></tr>";
    		//<th>fechaNacimiento</th>
    		
    		for(let i=0; i<datosUsuarios.length; i++){
    			html += "<tr>";
    			

    			html += "<tr><td>"+datosUsuarios[i].id+"</td>";
    			html += "<td>"+datosUsuarios[i].nombreUser+"</td>";
    			html += "<td>"+datosUsuarios[i].email+"</td>";
    			html += "<td>"+datosUsuarios[i].passwordUser+"</td>";
    			html += "<td>"+datosUsuarios[i].imgUser+"</td>";
    			html += "<td>"+datosUsuarios[i].esAdmin+"</td>";
    			
    			//html += "<td>"+datosUsuarios[i].fechaNacimiento+"</td>";
    			html += "<td>"+datosUsuarios[i].descripcionPerfil+"</td>";
   				/**/
   				html += "<td><a href='userManage?id="+datosUsuarios[i].id+"&op=2'>Editar</a>"+"</td>";
   				//html += "<td><a href='userManage?id="+datosUsuarios[i].id+"&op=2'>Editar</a>"+"</td>";
   				html += "<td><a href='#' type=''>Borrar</a></td>";
    			
    			html += "</tr>";
    			
    		}//fin for
    		
    		
    		html +=  "</table>";
    		
    		document.getElementById("listado_usuarios").innerHTML = html;
    		/**/
    	}//fin pintar tabla

    window.onload = function (){
		llamada();


		//llamada(id, op);
    }
