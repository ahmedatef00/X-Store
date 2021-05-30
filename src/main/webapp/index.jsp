<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>${applicationScope.storeInstance.storeName} - Home</title>
  <%@include file="includes/head.jsp" %>
</head>

<body>
<header>
  <%@include file="includes/header.jsp" %>
</header>

<section class="container-fluid">
  <div class="row">
    <div id="carouselExampleCaptions" class="carousel slide carousel-fade" data-ride="carousel"
         data-pause=false>
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
        <li data-target="#carouselExampleCaptions" data-slide-to="3"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/demo_war_exploded/images/home/slider/slider1.jpg" class="w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2>${applicationScope.storeInstance.sliderTitle1}</h2>
            <p>${applicationScope.storeInstance.sliderSubTitle1}</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="/demo_war_exploded/images/home/slider/slider2.jpg" class="w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2>${applicationScope.storeInstance.sliderTitle2}</h2>
            <p>${applicationScope.storeInstance.sliderSubTitle2}</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="/demo_war_exploded/images/home/slider/slider3.jpg" class="w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2>${applicationScope.storeInstance.sliderTitle3}</h2>
            <p>${applicationScope.storeInstance.sliderSubTitle3}</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="/demo_war_exploded/images/home/slider/slider4.jpg" class="w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2>${applicationScope.storeInstance.sliderTitle4}</h2>
            <p>${applicationScope.storeInstance.sliderSubTitle4}</p>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
</section>

<section class="featured py-5">
  <div class="container">
    <div class="row my-3">
      <div class="col-10 mx-auto text-center">
        <h1 class="text-uppercase">featured products</h1>
        <p class="text-muted">
          We Sell EVERYTHING! What to expect?
        </p>
      </div>
    </div>
    <div class="row">
      <!-- single product -->
      <div class="col-10 mx-auto col-md-6 col-lg-4">
        <div class="featured-container p-5 text-center">
          <img style="height: 13rem;" src="images/home/img-products/product-1.png" class="img-fluid" alt="product" />
        </div>
        <h6 class="text-capitalize text-center my-2">Computer Parts</h6>
      </div>
      <!-- end single product -->
      <!-- single product -->
      <div class="col-10 mx-auto col-md-6 col-lg-4">
        <div class="featured-container p-5 text-center">
          <img style="height: 13rem;" src="images/home/img-products/product-2.png" class="img-fluid" alt="product" />
        </div>
        <h6 class="text-capitalize text-center my-2">Home Appliances</h6>
      </div>
      <!-- end single product -->
      <!-- single product -->
      <div class="col-10 mx-auto col-md-6 col-lg-4">
        <div class="featured-container p-5 text-center">
          <img style="height: 13rem;" src="images/home/img-products/product-3.png" class="img-fluid" alt="product" />
        </div>
        <h6 class="text-capitalize text-center my-2">Electronic Parts</h6>
      </div>
      <!-- end single product -->
      <!-- single product -->
      <div class="col-10 mx-auto col-md-6 col-lg-4">
        <div class="featured-container p-5 text-center">
          <img style="height: 13rem;" src="images/home/img-products/product-4.png" class="img-fluid" alt="product" />
        </div>
        <h6 class="text-capitalize text-center my-2">Mobiles</h6>
      </div>
      <!-- end single product -->
      <!-- single product -->
      <div class="col-10 mx-auto col-md-6 col-lg-4">
        <div class="featured-container p-5 text-center">
          <img style="height: 13rem;" src="images/home/img-products/product-5.png" class="img-fluid" alt="product" />
        </div>
        <h6 class="text-capitalize text-center my-2">Furnature</h6>
      </div>
      <!-- end single product -->
      <!-- single product -->
      <div class="col-10 mx-auto col-md-6 col-lg-4">
        <div class="featured-container p-5 text-center">
          <img style="height: 13rem;" src="images/home/img-products/product-6.png" class="img-fluid" alt="product" />
        </div>
        <h6 class="text-capitalize text-center my-2">Books</h6>
      </div>
      <!-- end single product -->
    </div>
  </div>
</section>

<section>
  <div class="container-fluid">
    <div class="row home-filler align-items-end pb-5">
      <div class="col-10 mx-auto text-white text-center">
        <h2 class="text-uppercase font-weight-bold">
          world class collection
        </h2>
        <br>
        <h5>
          <a href="/demo_war_exploded/products" class="text-weight-bold text-capitalize collection-link">
            view our products
          </a>
        </h5>
        <div class="collection-underline"></div>
        <br>
        <br>
        <br>
      </div>
    </div>
  </div>
</section>

<!-- start features Area -->
<section class="features-area section_gap">
  <div class="container">
    <div class="row features-inner">
      <!-- single features -->
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="single-features">
          <div class="f-icon">
            <img src="/demo_war_exploded/images/home/features/f-icon1.png" alt="">
          </div>
          <h6>Free Delivery</h6>
          <p>Free Shipping on all order</p>
        </div>
      </div>
      <!-- single features -->
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="single-features">
          <div class="f-icon">
            <img src="/demo_war_exploded/images/home/features/f-icon2.png" alt="">
          </div>
          <h6>Return Policy</h6>
          <p>Free Shipping on all order</p>
        </div>
      </div>
      <!-- single features -->
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="single-features">
          <div class="f-icon">
            <img src="/demo_war_exploded/images/home/features/f-icon3.png" alt="">
          </div>
          <h6>24/7 Support</h6>
          <p>Free Shipping on all order</p>
        </div>
      </div>
      <!-- single features -->
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="single-features">
          <div class="f-icon">
            <img src="/demo_war_exploded/images/home/features/f-icon4.png" alt="">
          </div>
          <h6>Secure Payment</h6>
          <p>Free Shipping on all order</p>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- end features Area -->

<!-- modal -->
<div class="modal fade" id="productModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <!-- modal header -->
      <div class="modal-header">
        <h5 class="modal-title text-capitalize">product info</h5>
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <!--end of  modal header -->
      <!-- modal body -->
      <div class="modal-body">
        <div class="row">
          <div class="col text-center">
            <img src="images/home/img-products/product-1.png" class="img-fluid" alt="" />
            <!-- ratings -->
            <div class="ratings">
              <span class="rating-icon"><i class="fas fa-star"></i></span>
              <span class="rating-icon"><i class="fas fa-star"></i></span>
              <span class="rating-icon"><i class="fas fa-star"></i></span>
              <span class="rating-icon"><i class="fas fa-star"></i></span>
              <span class="rating-icon"><i class="far fa-star"></i></span>
              <span class="text-capitalize">(25 customer reviews)</span>
            </div>
            <!-- end of ratings -->
            <h2 class="text-uppercase my-2">premium office armchair</h2>
            <h2>$10.00 - $200.00</h2>
            <p class="lead text-muted">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi,
              porro.
            </p>
            <!-- colors -->
            <h5 class="text-uppercase">
              colors :
              <span class="d-inline-block products-color products-color-black mr-2"></span>
              <span class="d-inline-block products-color products-color-red mr-2"></span>
              <span class="d-inline-block products-color products-color-blue mr-2"></span>
            </h5>
            <!-- end of colors -->
            <!-- sizes -->
            <h5 class="text-uppercase">
              sizes : <span class="mx-2">xs</span> <span class="mx-2">s</span>
              <span class="mx-2">m</span> <span class="mx-2">l</span>
              <span class="mx-2">xl</span>
            </h5>
            <div class="d-flex flex-wrap">
              <!-- cart buttons -->
              <div class="d-flex my-2">
                <span class="btn btn-black mx-1">-</span>
                <span class="btn btn-black mx-1">4</span>
                <span class="btn btn-black mx-1">+</span>
              </div>
              <button class="btn btn-black my-2 mx-2">wishlist</button>
              <button class="btn btn-yellow my-2 mx-2">add to cart</button>
            </div>
          </div>
        </div>
      </div>
      <!-- end modal body -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">close modal</button>
      </div>
    </div>
  </div>
</div>


<%@include file="includes/footer.jsp" %>
</body>

</html>