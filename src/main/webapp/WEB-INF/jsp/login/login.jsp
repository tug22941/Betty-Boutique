<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/header.jsp" />

<link rel="stylesheet" href="/pub/css/signin.css" />
<script type="text/javascript" src="/pub/js/signin.js"></script>

<c:if test="${param['error'] != null}">
    <section class="pt-5 bg-light-grey text-center">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-sm-10 col-md-7 col-xl-5">
                    <div class="alert alert-danger" role="alert">
                        Invalid Username or Password
                    </div>
                </div>
            </div>
        </div>
    </section>
</c:if>

<section>
    <div class="row align-items-center fill">
        <form class="form-signin" method="POST" action="/login/loginpost">
            <h1 class="h3 mb-3 font-weight-normal text-center">Please sign in</h1>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input
                    type="text"
                    id="inputEmail"
                    class="form-control"
                    placeholder="Email address"
                    name="username"
                    required
                    autofocus
            />
            <label for="inputPassword" class="sr-only">Password</label>
            <input
                    type="password"
                    id="inputPassword"
                    class="form-control"
                    placeholder="Password"
                    name="password"
                    required
            />
            <div class="checkbox mb-3 text-center">
                <label>
                    <input type="checkbox" value="remember-me" /> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                Sign in
            </button>
            <div class="mt-3 text-center">
                <label>
                    <span>Not a member? </span ><a class="a" href="/login/signup">Register</a>
                </label>
            </div>
            <p class="mt-5 mb-3 text-muted text-center">&copy; 2023 - 2024</p>
        </form>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />