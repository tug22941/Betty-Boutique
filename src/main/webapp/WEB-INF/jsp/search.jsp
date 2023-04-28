<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/header.jsp"/>
<link rel="stylesheet" href="/pub/css/search.css" />


<section class="py-3">
    <div class="container text-center">
        <h2>Product Search</h2>
    </div>
</section>


<section class="py-5">
    <div class="container text-center">

        <h4 class="pb-4">${productsList.size()} Search Results </h4>

        <div class="row justify-content-center p-3">
            <c:forEach items="${productsList}" var="product">
            <div class="col-md-3 col-sm-12">
                <a href="/product/detail/${product.id}">
                <div class="card">
                    <img src="${product.imageUrl}" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <span class="font-weight-bold">Price:</span> $${product.price}
                        </li>
                        <li class="list-group-item">
                            <span class="font-weight-bold">Category:</span> ${product.productType}
                        </li>
                    </ul>
                </div>
                </a>
            </div>
            </c:forEach>
        </div>

    </div>
</section>

<jsp:include page="include/footer.jsp"/>