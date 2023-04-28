<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" href="/pub/css/cart.css" />



<section>
    <c:if test="${orderProducts.size() eq 0 || orderProducts == null}">
        <h2 class="pb-4 text-center">Your Cart is Empty</h2>
    </c:if>

    <c:if test="${orderProducts.size() gt 0}">
        <h2 class="p-4 text-center">Cart List: ${quantity} Items</h2>
    </c:if>

    <c:forEach items="${orderProducts}" var="product">
        <div class="row justify-content-center p-3">
            <div class="col-md-6 col-sm-12 p-2 box-wrap">

                <div class="row">
                    <div class="col-md-4 col-sm-12">
                        <div>
                            <a href="/product/detail/${product.product_id}">
                                <image class="w-50" src="${product.image_url}"></image>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-8 col-sm-12 info-box">
                        <div class="text-center">
                            <h4>${product.name}</h4>
                        </div>
                        <div class="text-center">
                            <h4>$${product.price}</h4>
                        </div>
                        <div class="text-center">
                            <p>Quantity: x${product.quantity}</p>
                        </div>
                        <div class="text-center">
                            <a href="/order/deleteFromCart/${product.order_products_id}" class="btn btn-danger"  role="button" >Delete</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </c:forEach>

    <c:if test="${orderProducts.size() gt 0}">
    <div class="row justify-content-center p-3">
        <div class="col-md-6 col-sm-12 p-2 box-wrap2">
           <h5> Order Total: ${orderTotal}</h5>
        </div>
    </div>
    </c:if>
</section>

<c:if test="${orderProducts.size() gt 0}">
<form id="formId" action="/order/placeOrder/${orderId}/${orderTotal}">
<section class="py-1">
    <div class="container f-container">
        <div class="row justify-content-center text-center">
            <h2>Shipping Address</h2>
        </div>

        <div class="row justify-content-center">
            <div class="mb-3 col-6 col-sm-12 col-xl-6">
                <label for="fullName" class="form-label">Full Name (First and Last)</label>
                <input type="text" aria-label="fullName" id="fullName" class="form-control" name="fullName" value="${form.fullName}">
                <c:if test="${bindingResult.hasFieldErrors('fullName')}">
                    <c:forEach items="${bindingResult.getFieldErrors('fullName')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="mb-3 col-6 col-sm-12 col-xl-6">
                <label for="country" class="form-label">Country</label>
                <input type="text" aria-label="Country" id="country" class="form-control" name="country" value="${form.country}">
                <c:if test="${bindingResult.hasFieldErrors('country')}">
                    <c:forEach items="${bindingResult.getFieldErrors('country')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="mb-3 col-6 col-sm-12 col-xl-6">
                <label for="addressLine1" class="form-label">Address</label>
                <input type="text" placeholder="Street address or P.O Box" aria-label="addressLine1" id="addressLine1" class="form-control" name="addressLine1" value="${form.addressLine1}">
                <c:if test="${bindingResult.hasFieldErrors('addressLine1')}">
                    <c:forEach items="${bindingResult.getFieldErrors('addressLine1')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="mb-3 col-6 col-sm-12 col-xl-6">
                <input type="text" placeholder="Apt, suite, unit, building, floor, etc" aria-label="addressLine2" id="addressLine2" class="form-control" name="addressLine2" value="${form.addressLine2}">
                <c:if test="${bindingResult.hasFieldErrors('addressLine2')}">
                    <c:forEach items="${bindingResult.getFieldErrors('addressLine2')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>
        </div>

        <div class="row justify-content-center mt-3">
            <div class="mb-3 col-md-3 col-sm-12 col-xl-3">
                <label for="city" class="form-label">City</label>
                <input type="text" aria-label="city" id="city" class="form-control" name="city" value="${form.city}">
                <c:if test="${bindingResult.hasFieldErrors('city')}">
                    <c:forEach items="${bindingResult.getFieldErrors('city')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="mb-3 col-md-3 col-sm-12 col-xl-3">
                <label for="state" class="form-label">State</label>
                <input type="text" aria-label="state" id="state" class="form-control" name="state" value="${form.state}">
                <c:if test="${bindingResult.hasFieldErrors('state')}">
                    <c:forEach items="${bindingResult.getFieldErrors('state')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="mb-3 col-md-3 col-sm-12 col-xl-3">
                <label for="zipcode" class="form-label">ZIP Code</label>
                <input type="text" aria-label="zipcode" id="zipcode" class="form-control" name="zipcode" value="${form.zipcode}">
                <c:if test="${bindingResult.hasFieldErrors('zipcode')}">
                    <c:forEach items="${bindingResult.getFieldErrors('zipcode')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div
        </div>
    </div>
</section>

<section class="py-1 mb-5">
    <div class="container f-container">
        <div class="row justify-content-center text-center">
            <h2>Card Information</h2>
        </div>
        <div class="row justify-content-center">
            <div class="mb-3 col-6 col-sm-12 col-xl-6">
                <label for="cardNumber" class="form-label">Card Number</label>
                <input type="text" aria-label="cardNumber" id="cardNumber" class="form-control" name="cardNumber" value="${form.cardNumber}">
                <c:if test="${bindingResult.hasFieldErrors('cardNumber')}">
                    <c:forEach items="${bindingResult.getFieldErrors('cardNumber')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="mb-3 col-6 col-sm-12 col-xl-6">
                <label for="cardName" class="form-label">Name On Card</label>
                <input type="text" aria-label="cardName" id="cardName" class="form-control" name="cardName" value="${form.cardName}">
                <c:if test="${bindingResult.hasFieldErrors('cardName')}">
                    <c:forEach items="${bindingResult.getFieldErrors('cardName')}" var="error">
                        <div style="color:red;">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

    <div class="row justify-content-center mt-3 text-center">
        <div class="mb-3 col-md-3 col-sm-12 col-xl-3">
            <button class="btn btn-primary">Place Your Order</button>
        </div>
    </div>
</section>
</form>
</c:if>



<jsp:include page="../include/footer.jsp" />