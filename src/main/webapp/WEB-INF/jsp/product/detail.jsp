<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/header.jsp"/>
<link rel="stylesheet" href="/pub/css/details.css" />


<script>
    function toEdits() {
        location.href = "/product/edit/${product.id}";
    }
</script>

<style>
    a {
        color: black;
        text-decoration: none;
    }
</style>

<section py-5 bg-dark-grey>
    <div class="container text-center">
        <h1>Product Details</h1>
    </div>
</section>

<c:if test="${cartUpdated}">
    <section class="bg-light-grey text-center">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-10 col-md-7 col-xl-5">
                    <div class="alert alert-success" role="alert">
                        Your Order Has Been Updated
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>

<section>
    <div class="row justify-content-center p-3">
        <div class="col-md-10 col-sm-12 p-2 box-wrap">

            <div class="row">
                <div class="col-md-4 col-sm-12">
                    <div class="d-flex flex-column">
                        <div><img src="${product.imageUrl}"/></div>
                        <div class="text-center bg">
                            <h4>${product.productType}</h4>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-sm-12 info-box">
                    <div class="text-center">
                        <button type="button" class="btn btn-sm btn-danger" onclick="toEdits()">Edit Product Details</button>
                    </div>
                    <div class="text-center">
                        <h3>${product.name}</h3>
                    </div>
                    <div class="text-center">
                        <h4>$${product.price}</h4>
                    </div>
                    <div class="text-center">
                        <p>${product.description}</p>
                    </div>

                    <form action="/order/addToCart/${product.id}">
                    <div class="justify-content-center d-flex p-3">
                        <select class="form-select text-center w-33" aria-label="Select Quantity" name="quantity" value="${quantity}">
                            <option value="0" selected >No Quantity Selected</option>
                            <option value="1" <c:if test="${quantity eq 1}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${quantity eq 2}">selected</c:if>>2</option>
                            <option value="3" <c:if test="${quantity eq 3}">selected</c:if>>3</option>
                            <option value="4" <c:if test="${quantity eq 4}">selected</c:if>>4</option>
                        </select>
                    </div>
                    <div class="text-center p-2">
                        <c:if test="${bindingResult.hasFieldErrors('quantity')}">
                            <c:forEach items="${bindingResult.getFieldErrors('quantity')}" var="error">
                                <div style="color:red;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="text-center p-2">
                        <button class="btn btn-sm btn-warning" name="id">Add to Cart</button>
                    </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>