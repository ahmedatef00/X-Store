function get_AllProducts() {
    let allProducts = new Array;
    var products_str = localStorage.getItem('products');
    if (products_str !== null) {
        allProducts = JSON.parse(products_str);
        console.log(allProducts);
    }
    return allProducts;
}

function onSuccess(data) {
    $("#do_action").hide();
    $("#response").show();
    $("#response_message").text(data.message);
    localStorage.removeItem("products");
    window.location = "/iti-store/view-profile";
}

function setRedirect(data) {
    // var json = JSON.parse(data);
    $("#do_action").hide();
    $("#response").show();
    $("#response_message").val(data.Message);
    window.location = data.message;
    // similar behavior as clicking on a link
    // window.location.href = "http://stackoverflow.com";
}

function onError(data) {
    // var json = JSON.parse(data);
    $("#do_action").hide();
    $("#response").show();
    $("#response_message").val(data);
    $("#Message").text(data)
    $('#myModel').modal('show');
}

function checkOut(userId) {
    if (typeof userId !== 'undefined' && userId !== '') {

        var allProducts = get_AllProducts();
        var totalPrice = parseFloat($('#allCartTotalAfterShipping').text());
        var userBalance = parseFloat($('#currentUserBalance').text());
        console.log("tootallll pricce: " + totalPrice);
        console.log("balaaannnnceeee: " + userBalance);
        if (allProducts.length === 0) {
            $('#message').html("no items in the cart.. <img src='images/cart/emoji.jpg' style='width: 50px; height: 50px'>");
            $('#myModel').modal('show');
        } else if (totalPrice > userBalance) {
            $('#message').html("your balance is less than required to perform the operation <img src='images/cart/emoji.jpg' style='width: 50px; height: 50px'>");
            $('#myModel').modal('show');
        } else {
            var json = JSON.stringify(allProducts);
            $.ajax({
                type: "POST",
                url: "orders",
                dataType: "JSON",
                data: {products: json},
                statusCode: {
                    201: function (data) {
                        onSuccess(data);
                    },
                    460: function (data) {
                        console.log("error")
                        onError(data.responseJSON.message);
                    },
                    500: function () {
                        $("#message").text("500 Internal Server Error")
                        $('#myModel').modal('show');

                    },
                    302: function (data) {
                        setRedirect(data);
                    }
                }
            });
        }
    } else {
        window.location = '/iti-store/login';
    }
}

$(document).ready(function () {
    // get_AllProducts();
    onloadPage();

    function onloadPage() {
        var json = JSON.stringify(get_AllProducts());
        console.log("==========" + json);
        $.ajax({
            type: "POST",
            url: "products",
            dataType: "JSON",
            data: {products: json},
            statusCode: {
                200: function (data) {
                    console.log(data);
                    displayData(data);
                    calcTotal();
                },
                404: function () {
                    console.log("error")
                },
                500: function () {
                    alert("leh ya rb m 5k2tny4 m3za");
                }
            }
        })
        $("#response").hide();
    }

    var totalSum;

    function calcTotal() {
        var alltot = $(".totalPrice");
        totalSum = 0;
        for (var i = 0; i < alltot.length; i++) {
            totalSum += parseFloat($(alltot[i]).text());
        }
        $("#allCartTotal").text(totalSum);
        $("#allCartTotalAfterShipping").text(totalSum);
        updateCartProducts();
    }


    function displayData(data) {
        data.forEach(product => {
            var insertedRow = `<tr class="rem1">
                            <td class="cart_product">
                                <a href=""><img src="${product.primaryImage}" style="max-width: 50px; max-height: 50px" alt=""></a>
                            </td>
                            <td class="cart_description">
                                <h4><a href="">${product.productName}</a></h4>
                                <p>Web ID: <span class="productId">${product.productId}</span></p>
                            </td>
                            <td class="cart_price">
                                <p>${product.price}</p>
                            </td>
                            <td class="cart_quantity">
                                <div class="cart_quantity_button">
                                    <div class="value-minus">
                                        <a class="cart_quantity_down"> - </a>
                                    </div>
                                    <div class="entry value"><span>${product.quantity}</span></div>
                                    <div class="value-plus">
                                        <a class="cart_quantity_up"> + </a>
                                    </div>
                                </div>
                            </td>
                            <td class="cart_total">
                                <div class="row">
                                    <div class="col-md-8">
                                        <p class="cart_total_price totalPrice">${product.price * product.quantity}</p>
                                    </div>
                                    <div class="col-md-4">
                                         <span class="cart_total_price">&#36;</span>
                                    </div>
                                </div>
                            </td>
                            <td class="cart_delete">
                                <a class="cart_quantity_delete"><i class="fa fa-times close1"></i></a>
                            </td>
                        </tr>`

            $('#myTable tr:last').after(insertedRow);
        })
    }

    $(document).on('click', '.value-plus', function () {
        var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) + 1;
        divUpd.text(newVal);
        var totNode = $(this).parent().parent().parent().find('.cart_total .row .col-md-8 p');
        var priceNode = $(this).parent().parent().parent().find('.cart_price  p');
        calcPrice(newVal, totNode, priceNode);


    });

    $(document).on('click', '.removeAll', function () {
        localStorage.removeItem("products");
        $(".rem1").empty();
        $("#allCartTotal").text(0);
        $("#allCartTotalAfterShipping").text(0);
    });

    $(document).on('click', '.value-minus', function () {
        var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10) - 1;
        if (newVal >= 1) {
            divUpd.text(newVal);
            var totNode = $(this).parent().parent().parent().find('.cart_total .row .col-md-8 p');
            var priceNode = $(this).parent().parent().parent().find('.cart_price  p');
            calcPrice(newVal, totNode, priceNode);
        }

    });
    $(document).on('click', '.close1', function () {
        $(this).parents("tr").remove();
        calcTotal();
    });

    function calcPrice(newVal, totNode, priceNode) {
        var price = parseFloat(priceNode.text());
        var newPriceTotal = newVal * price;
        totNode.text(newPriceTotal);
        calcTotal();
    }

    function updateCartProducts() {
        var allIds = $(".productId");
        var qty = $(".value");
        var cart_price = $(".cart_price");
        let allProducts = new Array;
        var totalPrice = $(".totalPrice");

        for (var i = 0; i < allIds.length; i++) {
            console.log($(allIds[i]).text());
            console.log($(qty[i]).text());
            var product = {
                productId: $(allIds[i]).text(), productName: "", description: "",
                manufacturingName: "", manufacturingDate: "",
                expirationDate: ""
                , quantity: $(qty[i]).text(),
                categories: [],
                price: $(cart_price[i]).text().trim(),
                primaryImage: "",
                images: []
            }
            allProducts.push(product);
            console.log(product);
        }
        localStorage.setItem('products', JSON.stringify(allProducts));
    }
});