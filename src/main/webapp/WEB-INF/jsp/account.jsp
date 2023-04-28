<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="include/header.jsp" />

<section class="py-2 bg-purple">
    <div class="container text-center">

        <h2>User Account Page</h2>
        <h5 class="my-4">${fullName}</h5>

        <c:if test="${fullList.size() eq 0 || fullList == null}">
            <h4 class="pb-4">No Order History</h4>
        </c:if>

        <c:forEach items="${fullList}" var="orderList">
            <table class="table table-striped mt-2">
                <thead>
                <tr>
                    <th scope="col">ORDER PLACED ${orderList.get(0).shipping_date}</th>
                    <th scope="col">TOTAL ${orderList.get(0).total}</th>
                    <th scope="col">
                        SHIP TO
                        <div>${orderList.get(0).receiver}</div>
                    </th>
                    <th scope="col"><a>Order # 1113-6250626-7515853</a></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderList}" var="order">
                <tr>
                    <td></td>
                    <td><image class="w-10" src="${order.image_url}"></image></td>
                    <td>${order.quantity}</td>
                    <td>${order.name}</td>
                    <td></td>
                    <td>
                        <a href="">View order details</a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:forEach>
    </div>
</section>

<jsp:include page="include/footer.jsp" />