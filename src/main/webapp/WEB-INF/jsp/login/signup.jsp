<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/pub/js/signup.js"></script>

<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" href="/pub/css/signup.css" />

<!-- Content Header-->
    <section class="py-3">
        <div class="container text-center">
            <h2>New Account</h2>
        </div>
    </section>

  <!-- Content -->
    <section class="py-2">
        <div class="container f-container">
            <form id="formId" method="POST" action="/login/signupSubmit">

                <c:if test="${success}" >
                    <div class="alert alert-success text-center" role="alert">
                        Sign Up Completed!
                    </div>
                </c:if>

                <div class="row justify-content-center">
                  <div class="mb-3 col-md-6 col-sm-12 col-xl-5">
                    <label for="txtFirstName" class="form-label">First Name <small>Required</small></label>
                    <input type="text" class="form-control input reqInput" id="txtFirstName" name="firstName" value="${form.firstName}">
                    <small id="firstNameHelp" class="form-text text-danger field"></small>
                      <c:if test="${bindingResult.hasFieldErrors('firstName')}">
                          <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                              <div style="color:red;">${error.getDefaultMessage()}</div>
                          </c:forEach>
                      </c:if>
                  </div>
                  <div class="mb-3 col-md-6 col-sm-12 col-xl-5">
                    <label for="txtLastName" class="form-label">Last Name <small>Required</small></label>
                    <input type="text" class="form-control input reqInput" id="txtLastName" name="lastName" value="${form.lastName}">
                    <small id="lastNameHelp" class="form-text text-danger field"></small>
                      <c:if test="${bindingResult.hasFieldErrors('lastName')}">
                          <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                              <div style="color:red;">${error.getDefaultMessage()}</div>
                          </c:forEach>
                      </c:if>
                  </div>
                </div>

                <div class="row justify-content-center align-items-center">
                    <div class="mb-3 col-md-6 col-sm-12 col-xl-5">
                      <label for="txtemail" class="form-label">Email Address <small>Required</small></label>
                      <input type="email" class="form-control input reqInput" id="txtEmail" aria-describedby="emailHelp" name="email" value="${form.email}">
                      <small id="emailHelp" class="form-text text-danger field"></small>
                        <c:if test="${bindingResult.hasFieldErrors('email')}">
                            <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                <div style="color:red;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </div>

                  <div class="mb-3 col-md-6 col-sm-12 col-xl-5">
                    <!-- empty column for styling-->
                  </div>

                </div>

                <div class="row justify-content-center">
                    <div class="mb-3 col-md-6 col-sm-12 col-xl-5">
                      <label for="txtPassword" class="form-label">Password <small>Required</small></label>
                      <input type="password" class="form-control input reqInput" id="txtPassword" name="password" value="${form.password}">
                      <small id="passwordHelp" class="form-text text-danger field"></small>
                        <c:if test="${bindingResult.hasFieldErrors('password')}">
                            <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                <div style="color:red;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="mb-3 col-md-6 col-sm-12 col-xl-5">
                      <label for="txtConfirmPassword" class="form-label">Confirm Password<small>Required</small></label>
                      <input type="password" class="form-control input-group input reqInput" id="txtConfirmPassword" name="confirmPassword" value="${form.confirmPassword}">
                      <small id="confirmPasswordHelp" class="form-text text-danger field"></small>
                        <c:if test="${bindingResult.hasFieldErrors('confirmPassword')}">
                            <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
                                <div style="color:red;">${error.getDefaultMessage()}</div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>

                <div class="row justify-content-center">
                  <div class="col col-5 text-center">
                    <button class="btn btn-success w-75" id="btnSubmit">Create Account</button>
                  </div>
                    <div class="col col-5 text-center">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                            <label class="form-check-label" for="flexRadioDefault1">
                                Premium Account
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
                            <label class="form-check-label" for="flexRadioDefault2">
                                Standard Account
                            </label>
                        </div>
                    </div>
                </div>

              </form>
        </div>
    </section>

<jsp:include page="../include/footer.jsp" />
