let pedido = {
    fecha: '2022-05-31',
    lineasPedido: []
};
window.onload = function () {
    let cookies = document.cookie.split(";");
    let usuario = $("#username").text();
    if (document.cookie.split(";").find(cook => cook.search("carrito") > 0) !== undefined) {
        let carrito = JSON.parse(document.cookie.split(";").find(cook => cook.search("carrito") > 0).split("=")[1]);
        if (carrito[usuario] !== undefined) {
            pedido = carrito[usuario];
            cargarCarrito(pedido);
        }
    }

    $("#fecha_entrega").change(() => {
        pedido.fecha = $("#fecha_entrega").val();
    });

    $("#btnCrearPedido").click(() => {
        let datosPedido = JSON.stringify(pedido);
        let url = "./api";
        $.ajax({
            url: url,
            dataType: 'JSON',
            contentType: "application/json",
            method: 'POST',
            data:datosPedido,
            success: function (result) {
                if(result.response=="Pedido creado correctamente"){
                    alert(result.response);
                }

            },
            error: function (err) {
                console.log(err);
            }
        });
    });

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
        if (articulo !== undefined) {
            articulo.cantidad = cantidad;
            articulo.total = total
        } else {
            if (cantidad > 0) {

                pedido.lineasPedido.push({
                    idArticulo: idArticulo,
                    cantidad: cantidad,
                    precio: precio,
                    total: total
                });
            }
        }

        $("#cantProductos").text(pedido.lineasPedido.filter(art => art.cantidad > 0).length);
        let importeTotal = 0;
        pedido.lineasPedido.forEach(art => {
            importeTotal += +art.total;
        });
        $("#importeTotal").text(importeTotal);
        let usuario = $("#username").text();

        if (document.cookie.split(";").find(cook => cook.search("carrito") > 0) !== undefined) {
            carritosCookie = JSON.parse(document.cookie.split(";").find(cook => cook.search("carrito") > 0).split("=")[1]);

            carritosCookie[usuario] = pedido;
        } else {
            carritosCookie = {usuario: pedido};
        }
        let info = JSON.stringify(carritosCookie);
        document.cookie = "carrito=" + info;
    });


    function cargarCarrito(cart) {
        $("#fecha_entrega").val(cart.fecha);
        for (let i = 0; i < cart.lineasPedido.length; i++) {
            let idArticulo = cart.lineasPedido[i].idArticulo;
            let selector = "#cantidad" + idArticulo;
            $(selector).val(cart.lineasPedido[i].cantidad);
            selector = "#total" + idArticulo;
            $(selector).val(cart.lineasPedido[i].total);

        }
        $("#cantProductos").text(pedido.lineasPedido.filter(art => art.cantidad > 0).length);
        let importeTotal = 0;
        pedido.lineasPedido.forEach(art => {
            importeTotal += +art.total;
        });
        $("#importeTotal").text(importeTotal);
    }
};
