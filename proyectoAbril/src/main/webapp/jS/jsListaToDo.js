(function(){
    /** ¡variables*/
    var ulListaAgregado = document.getElementById("ulListaAgregado");
    var nombreProducto = document.getElementById("nombreProducto");

    var btnNuevoProducto = document.getElementById("btnAgregaProductoCompra");

    /** FUNCIONES */
    var agregarProLista= function(){
        alert("lista");
    };

    function comprobarAgregar(){
        alert("agrega");
    }

    function tachar(){
        alert("tachar");
    }


    /**EVENTOS */
    //cuando el usuario le de a agregar prducto se añadira a la lista
    btnNuevoProducto.addEventListener("click", agregarProLista);

//input producto
    nombreProducto.addEventListener("click", comprobarAgregar);

    //tachar elementos de la lista
    for(var i=0; i <=(ulListaAgregado.children.length -1); i++){
        ulListaAgregado.children[i].btnHecho.addEventListener("click", tachar);
    }

}());



/**
 * formulario    .. formAgregarProducto
 * inputProducto  ..   nombreProducto
 * 
 * lista  .. ulListaAgregado

    boton     .. btnAgregaProductoCompra
 * 
 * */ 


