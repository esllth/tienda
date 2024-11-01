/*La siguiente funcion se utiliza para visualizar la imagen
 * seleccionada en la pagina html donde se desea cargar utilizando
 * un llamada "ajax"*/
function readURL(input){
if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e){
        $('#blah').attr('src', e.target.result).height(200);
};
reader.readAsDataUrl(input.files[0]);
}
}


/*La siguiente funcion se utiliza para activar la cantidad de elementos
 * seleccionados en el carrito de compras utilizando un llamado "ajax"*/
function addCard(formulario){
    var valor = formulario.elemento[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    $("#resultsBlock").load(url);
}