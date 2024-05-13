
	
	document.addEventListener('DOMContentLoaded', function(){
		
		//EVENTOS
	//devuelve una coleccion de elementos en este caso tipo button y que coja el primero que es i=0
	//const btnAgregar = document.querySelector("btnAgregar");
		const btnAgregar = document.getElementsByTagName("button")[0];

		//capturamos el evento de hacer click
		btnAgregar.addEventListener('click', function(){
			alert("patata");
			
			larala();
	
		});
		
	})//fin doc addEvent