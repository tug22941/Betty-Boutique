<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    function toDetails(){
        location.href="/product/detail/${form.id}";
    }
</script>

<jsp:include page="../include/header.jsp" />

<section class="py-3">
    <div class="container text-center">
        <!-- If form.id is empty : make header Create Employee"-->
        <c:if test="${empty form.id}">
            <h2>Create Product</h2>
        </c:if>
        <!-- If form.id is not empty : make header Edit Employee"-->
        <c:if test="${not empty form.id}">
            <h2>Edit Product: ${form.id}</h2>
        </c:if>
    </div>
</section>
<div class="row justify-content-center">
    <div class="mb-3 col-6 col-sm-12 col-xl-6">
    <c:if test="${success}" >
        <div class="alert alert-success text-center" role="alert">
            Product Created!
        </div>
    </c:if>
    </div>
</div>

<section class="py-5">
    <div class="container f-container">
        <form id="formId" action="/product/createSubmit" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${form.id}"/>
            <c:if test="${not empty form.id}">
                <div class="row justify-content-center">
                    <div class="mb-3 col-6 col-sm-12 col-xl-6 text-center">
                        <button type="button" class="btn btn-success" onclick="toDetails()">View Product Details</button>
                    </div>
                </div>
            </c:if>
            <div class="row justify-content-center">
                <div class="mb-3 col-6 col-sm-12 col-xl-6">
                    <label for="name" class="form-label">Product Name</label>
                    <input type="text" aria-label="Product name" id="name" class="form-control" name="name" value="${form.name}">
                    <c:if test="${bindingResult.hasFieldErrors('name')}">
                        <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="mb-3 col-6 col-sm-12 col-xl-6">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" aria-label="Product description" id="description" class="form-control" name="description" value="${form.description}">
                    <c:if test="${bindingResult.hasFieldErrors('description')}">
                        <c:forEach items="${bindingResult.getFieldErrors('description')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="row justify-content-center align-items-center">
                <div class="mb-3 col-6 col-xl-6">
                    <label for="fileUpload" class="form-label">Image URL</label>
                    <input type="file" class="form-control" id="fileupload" name="picture" value="${form.imageUrl}" aria-label="Image URL">
                    <c:if test="${bindingResult.hasFieldErrors('picture')}">
                        <c:forEach items="${bindingResult.getFieldErrors('picture')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="mb-3 col-6 col-xl-6">
                    <label for="productType" class="form-label">Product Type</label>
                    <input type="text" aria-label="Image URL" id="productType" class="form-control" name="productType" value="${form.productType}">
                    <c:if test="${bindingResult.hasFieldErrors('productType')}">
                        <c:forEach items="${bindingResult.getFieldErrors('productType')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="row justify-content-center align-items-center">
                <div class="mb-3 col-md-6 col-sm-12 col-xl-6">
                    <label for="price" class="form-label">Price</label>
                    <input type="text" aria-label="Product Price" id="price" class="form-control" name="price" value="${form.price}">
                    <c:if test="${bindingResult.hasFieldErrors('price')}">
                        <c:forEach items="${bindingResult.getFieldErrors('price')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="row justify-content-center text-center">
                <div class="col col-4">

                    <c:if test="${empty form.id}">
                        <button  class="btn btn-success" >Submit</button>
                    </c:if>
                    <c:if test="${not empty form.id}">
                        <button  class="btn btn-primary" >Edit</button>
                    </c:if>
                </div>
            </div>

        </form>
    </div>
</section>

<section class="py-5">
    <div class="container text-center">

        <h4 class="pb-4">Product Table: ${productsList.size()} Search Results </h4>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Image URL</th>
                <th scope="col">Type</th>
                <th scope="col">Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productsList}" var="product">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.imageUrl}</td>
                    <td>${product.productType}</td>
                    <td>${product.price}</td>
                    <td><a href="/product/edit/${product.id}" class="btn btn-sm btn-primary"  role="button" >Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />