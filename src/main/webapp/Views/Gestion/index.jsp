<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../Fragment/cabecera.jspf" %>

<!-- Begin Page Content -->
<div class="container-fluid">
    <h2>Nuevo Pedido</h2>
    <div class="row">
        <div class="form-group col-md-6">
            <label class="col-form-label">Fecha de Entrega</label>
            <div class="col-sm-10">
                <input type="date"  class="form-control" id="fecha_entrega">
            </div>
        </div>
        <div class="form-group col-md-6">
            <div class="row">
                <div class="col-6"><p>Cantidad de Productos</p><p id="cantProductos"></p></div>
                <div class="col-6"><p>Importe Pedido</p><p id="importeTotal"></p></div>
            </div>
        </div>
    </div>
    ${carrito}
    <hr>
    <div id="productos" class="row">
        <c:forEach items="${productos}" var="producto">
            <article class="col-sm-12 col-md-3 producto">
                <h3>${producto.getNombre()}</h3>
                <img src="static/img/${producto.getIdproducto()}.jpg" alt="alt"/>
                <p>${producto.getDescripcion()}</p>
                <div class="row" id="${producto.getIdproducto()}">
                    <div class="col-4">
                        <label>Precio</label>
                        <input class="form-control precio" id="precio${producto.getIdproducto()}" type="number" readonly value="${producto.getPrecio()}">
                    </div>
                    <div class="col-4">
                        <label>Cantidad</label>
                        <input class="form-control cantidad" id="cantidad${producto.getIdproducto()}" type="number">
                    </div>
                    <div class="col-4">
                        <label>Total</label>
                        <input class="form-control total" id="total${producto.getIdproducto()}" type="number"><!-- comment -->
                    </div>
                </div>
                <br>
                <button class="btn btn-success btnAddToCart" id="btnAddToCart${producto.getIdproducto()}">Añadir al carrito</button>
            </article>
        </c:forEach>
    </div>
    <button id="btnCrearPedido" class="btn btn-success btn-lg">Crear Pedido</button>

</div>
<!-- /.container-fluid -->

<%@include file="../Fragment/footer.jspf" %>
