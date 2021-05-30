<nav class="navbar navbar-dark bg-dark navbar-expand-md container-fluid">

    <a class="navbar-brand">
        <img src="images/Amazon_logo.jpg" width="30" height="30" alt="ITI Store">
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item nav-active">
                <a class="nav-link" href="/demo_war_exploded/home">Home<span class="sr-only">(current)</span></a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.user.role == Role.ADMIN_ROLE}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Products
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="/demo_war_exploded/addProduct">Add Product</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/demo_war_exploded/products">View All Products</a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/products">Products</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>

        <ul class="navbar-nav ml-auto">

            <c:choose>
                <c:when test="${sessionScope.user.role == Role.ADMIN_ROLE}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Adminstration
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item" href="/demo_war_exploded/scratchCardRequest">Scratch Card Requests</a>
                            
                            <div class="dropdown-divider"></div>
                            
                            <a class="dropdown-item" href="/demo_war_exploded/customers">Customers</a>

                            <div class="dropdown-divider"></div>

                            <a class="dropdown-item" href="/demo_war_exploded/feedbacks">Feedbacks</a>
                            
                            <div class="dropdown-divider"></div>
                            
                            <a class="dropdown-item" href="/demo_war_exploded/store">Store</a>

                        </div>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/cart">Cart</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/view-profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/logout">Logout</a>
                    </li>
                </c:when>
                <c:when test="${sessionScope.user.role == Role.CUSTOMER_ROLE}">
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/cart">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/view-profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/logout">Logout</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/cart">Cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/registration">Registration</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/demo_war_exploded/login">Login</a>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
    </div>

</nav>