<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home Page</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
      crossorigin="anonymous"
    />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>


    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&display=swap" rel="stylesheet"/>

    <link rel="stylesheet" href="/pub/css/global.css" />

  </head>
  <body>

    <section>
      <nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
        <a class="navbar-brand" href="/index"><h3>Betty's Boutique</h3></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 w-80">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="/index">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/about">About</a>
            </li>

            <sec:authorize access="hasAnyAuthority('ADMIN')">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                User | Product
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="/user/create">Create User</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="/product/create">Create Product</a></li>
              </ul>
            </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/login/logout">Sign Out</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/account/accountInformation"><sec:authentication property="principal.username" /></a>
              </li>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/login/loginPage">Sign In</a>
              </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
              <li class="nav-item d-flex justify-content-end cart-link">
                <a class="nav-link text-end cart-a" href="/order/cart">
                  <img src="/pub/images/cart.png" class="cart-img">
                  <span>Cart</span>
                </a>
              </li>
            </sec:authorize>
          </ul>
          <form class="d-flex" action="/search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search" value="${search}">
            <button class="btn btn-outline-success">Search</button>
          </form>
        </div>
      </nav>
    </section>