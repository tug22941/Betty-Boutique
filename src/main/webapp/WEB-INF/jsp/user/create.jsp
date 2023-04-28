<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp" />

<section class="py-3">
    <div class="container text-center">
        <!-- If form.id is empty : make header Create Employee"-->
        <c:if test="${empty form.id}">
            <h2>Create User</h2>
        </c:if>
        <!-- If form.id is not empty : make header Edit Employee"-->
        <c:if test="${not empty form.id}">
            <h2>Edit User: ${form.id}</h2>
        </c:if>
    </div>
</section>

<script>
    function toDetails(){
        location.href="/user/detail/${form.id}";
    }
</script>

<section class="py-5">
    <div class="container f-container">
        <form id="formId" action="/user/createSubmit">

            <c:if test="${success}" >
                <div class="alert alert-success" role="alert">
                    Form Submitted
                </div>
            </c:if>

        <input type="hidden" name="id" value="${form.id}"/>
            <c:if test="${not empty form.id}">
                <div class="row justify-content-center">
                    <div class="mb-3 col-6 col-sm-12 col-xl-6 text-center">
                        <button type="button" class="btn btn-success" onclick="toDetails()">View User Details</button>
                    </div>
                </div>
            </c:if>
            <div class="row justify-content-center">
                <div class="mb-3 col-6 col-sm-12 col-xl-6">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text" aria-label="User first name" id="firstName" class="form-control" name="firstName" value="${form.firstName}">
                    <c:if test="${bindingResult.hasFieldErrors('firstName')}">
                        <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="mb-3 col-6 col-sm-12 col-xl-6">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text" aria-label="User last name" id="lastName" class="form-control" name="lastName" value="${form.lastName}">
                    <c:if test="${bindingResult.hasFieldErrors('lastName')}">
                        <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="row align-items-center">
                <div class="mb-3 col-md-6 col-sm-12 col-xl-6">
                    <label for="email" class="form-label">Email address</label>
                    <input type="email" aria-label="User Email" id="email" class="form-control" name="email" value="${form.email}">
                    <div id="emailHelp" class="form-text"></div>
                    <c:if test="${bindingResult.hasFieldErrors('email')}">
                        <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="row justify-content-center align-items-center">
                <div class="mb-3 col-md-6 col-sm-12 col-xl-6">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" aria-label="Password" id="password" class="form-control" name="password" value="${form.password}">
                    <div id="password_help" class="form-text"></div>
                    <c:if test="${bindingResult.hasFieldErrors('password')}">
                        <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                            <div style="color:red;">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="mb-3 col-md-6 col-sm-12 col-xl-6">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input type="password" aria-label="confirmPassword" id="confirmPassword" class="form-control" name="confirmPassword" value="${form.confirmPassword}">
                    <div id="ConfirmPassword_help" class="form-text"></div>
                    <c:if test="${bindingResult.hasFieldErrors('confirmPassword')}">
                        <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
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

        <h4 class="pb-4">${usersList.size()} Search Results </h4>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">ID #</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Email</th>
                <th scope="col">Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${usersList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td><a href="/user/edit/${user.id}" class="btn btn-sm btn-primary"  role="button" >Edit</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />