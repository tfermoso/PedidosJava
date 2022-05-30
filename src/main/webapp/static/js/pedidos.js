let pedido = {
    lineasPedido: []
};
window.onload = function () {

    console.log("Cargando pedidos.js");
    $(".cantidad").blur((e) => {
        let idArticulo = e.currentTarget.id.substr(8);
        let cantidad = $(e.currentTarget).val();
        let selector = "#precio" + idArticulo;
        let precio = $(selector).val();
        selector = "#total" + idArticulo;
        $(selector).val(cantidad * precio);
        //let filaArticulo = e.currentTarget.parentNode.parentNode;
        //let precio = filaArticulo.getElementsByClassName("precio")[0].value;
        //filaArticulo.getElementsByClassName("total")[0].value = precio*cantidad            
    });
    $(".btnAddToCart").click((e) => {
        let idArticulo = e.currentTarget.id.substr(12);
        let selector = "#cantidad" + idArticulo;
        let cantidad = $(selector).val();
        selector = "#total" + idArticulo;
        let total = $(selector).val();
        selector = "#precio" + idArticulo;
        let precio = $(selector).val();
        let articulo = pedido.lineasPedido.find(art => art.idArticulo === idArticulo);
        if (articulo !== undefined){
            articulo.cantidad=cantidad;
            articulo.total=total
        }else{
            if (cantidad > 0) {

                pedido.lineasPedido.push({
                    idArticulo: idArticulo,
                    cantidad: cantidad,
                    precio: precio,
                    total: total
                });
            }
        }
            
        $("#cantProductos").text(pedido.lineasPedido.filter(art=>art.cantidad>0).length);
        let importeTotal=0;
        pedido.lineasPedido.forEach(art=>{
            importeTotal+=+art.total;
        });
        $("#importeTotal").text(importeTotal);
    });
};
