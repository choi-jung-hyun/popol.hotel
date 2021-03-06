<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/WEB-INF/jsp/include/inc_common_header.jsp"%>

</head>
<body>

	<!-- START nav -->
	<%@include file="/WEB-INF/jsp/include/inc_header.jsp"%>
	<!-- END nav -->

	<div class="hero-wrap js-fullheight"
		style="background-image: url('/resources/images/bg_1.jpg');">
		<div class="overlay"></div>
		<div class="container">
			<div
				class="row no-gutters slider-text js-fullheight align-items-center justify-content-center"
				data-scrollax-parent="true">
				<div class="col-md-9 text-center ftco-animate"
					data-scrollax=" properties: { translateY: '70%' }">
					<p class="breadcrumbs"
						data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">
						<span class="mr-2"><a href="index.html">Home</a></span> <span>Hotel</span>
					</p>
					<h1 class="mb-3 bread"
						data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Hotels</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 sidebar order-md-last ftco-animate">
					<div class="sidebar-wrap ftco-animate">
						<h3 class="heading mb-4">Find City</h3>
						<form action="#">
							<div class="fields">
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="Destination, City">
								</div>
								<div class="form-group">
									<div class="select-wrap one-third">
										<div class="icon">
											<span class="ion-ios-arrow-down"></span>
										</div>
										<select name="" id="" class="form-control"
											placeholder="Keyword search">
											<option value="">Select Location</option>
											<option value="">San Francisco USA</option>
											<option value="">Berlin Germany</option>
											<option value="">Lodon United Kingdom</option>
											<option value="">Paris Italy</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<input type="text" id="checkin_date"
										class="form-control checkin_date" placeholder="Date from">
								</div>
								<div class="form-group">
									<input type="text" id="checkout_date"
										class="form-control checkout_date" placeholder="Date to">
								</div>
								<div class="form-group">
									<div class="range-slider">
										<span> <input type="number" value="25000" min="0"
											max="120000" /> - <input type="number" value="50000" min="0"
											max="120000" />
										</span> <input value="1000" min="0" max="120000" step="500"
											type="range" /> <input value="50000" min="0" max="120000"
											step="500" type="range" />
										</svg>
									</div>
								</div>
								<div class="form-group">
									<input type="submit" value="Search"
										class="btn btn-primary py-3 px-5">
								</div>
							</div>
						</form>
					</div>
					<div class="sidebar-wrap ftco-animate">
						<h3 class="heading mb-4">Star Rating</h3>
						<form method="post" class="star-rating">
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">
									<p class="rate">
										<span><i class="icon-star"></i><i class="icon-star"></i><i
											class="icon-star"></i><i class="icon-star"></i><i
											class="icon-star"></i></span>
									</p>
								</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">
									<p class="rate">
										<span><i class="icon-star"></i><i class="icon-star"></i><i
											class="icon-star"></i><i class="icon-star"></i><i
											class="icon-star-o"></i></span>
									</p>
								</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">
									<p class="rate">
										<span><i class="icon-star"></i><i class="icon-star"></i><i
											class="icon-star"></i><i class="icon-star-o"></i><i
											class="icon-star-o"></i></span>
									</p>
								</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">
									<p class="rate">
										<span><i class="icon-star"></i><i class="icon-star"></i><i
											class="icon-star-o"></i><i class="icon-star-o"></i><i
											class="icon-star-o"></i></span>
									</p>
								</label>
							</div>
							<div class="form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">
									<p class="rate">
										<span><i class="icon-star"></i><i class="icon-star-o"></i><i
											class="icon-star-o"></i><i class="icon-star-o"></i><i
											class="icon-star-o"></i></span>
									</p>
								</label>
							</div>
						</form>
					</div>
				</div>
				<!-- END-->
				<div class="col-lg-9">
					<div class="row">
						<div class="col-sm col-md-6 col-lg-4 ftco-animate">
							<div class="destination">
								<a href="#"
									class="img img-2 d-flex justify-content-center align-items-center"
									style="background-image: url(/resources/images/hotel-1.jpg);">
									<div
										class="icon d-flex justify-content-center align-items-center">
										<span class="icon-link"></span>
									</div>
								</a>
								<div class="text p-3">
									<div class="d-flex">
										<div class="one">
											<h3>
												<a href="#">New Orleans, LA</a>
											</h3>
											<p class="rate">
												<i class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star-o"></i> <span>8 Rating</span>
											</p>
										</div>
										<div class="two">
											<span class="price per-price">$40<br> <small>/night</small></span>
										</div>
									</div>
									<p>Far far away, behind the word mountains, far from the
										countries</p>
									<hr>
									<p class="bottom-area d-flex">
										<span><i class="icon-map-o"></i> Miami, Fl</span> <span
											class="ml-auto"><a href="#">Book Now</a></span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm col-md-6 col-lg-4 ftco-animate">
							<div class="destination">
								<a href="#"
									class="img img-2 d-flex justify-content-center align-items-center"
									style="background-image: url(/resources/images/hotel-2.jpg);">
									<div
										class="icon d-flex justify-content-center align-items-center">
										<span class="icon-link"></span>
									</div>
								</a>
								<div class="text p-3">
									<div class="d-flex">
										<div class="one">
											<h3>
												<a href="#">New Orleans, LA</a>
											</h3>
											<p class="rate">
												<i class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star-o"></i> <span>8 Rating</span>
											</p>
										</div>
										<div class="two">
											<span class="price per-price">$40<br> <small>/night</small></span>
										</div>
									</div>
									<p>Far far away, behind the word mountains, far from the
										countries</p>
									<hr>
									<p class="bottom-area d-flex">
										<span><i class="icon-map-o"></i> Miami, Fl</span> <span
											class="ml-auto"><a href="#">Book Now</a></span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm col-md-6 col-lg-4 ftco-animate">
							<div class="destination">
								<a href="#"
									class="img img-2 d-flex justify-content-center align-items-center"
									style="background-image: url(/resources/images/hotel-3.jpg);">
									<div
										class="icon d-flex justify-content-center align-items-center">
										<span class="icon-link"></span>
									</div>
								</a>
								<div class="text p-3">
									<div class="d-flex">
										<div class="one">
											<h3>
												<a href="#">New Orleans, LA</a>
											</h3>
											<p class="rate">
												<i class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star-o"></i> <span>8 Rating</span>
											</p>
										</div>
										<div class="two">
											<span class="price per-price">$40<br> <small>/night</small></span>
										</div>
									</div>
									<p>Far far away, behind the word mountains, far from the
										countries</p>
									<hr>
									<p class="bottom-area d-flex">
										<span><i class="icon-map-o"></i> Miami, Fl</span> <span
											class="ml-auto"><a href="#">Book Now</a></span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm col-md-6 col-lg-4 ftco-animate">
							<div class="destination">
								<a href="#"
									class="img img-2 d-flex justify-content-center align-items-center"
									style="background-image: url(/resources/images/hotel-4.jpg);">
									<div
										class="icon d-flex justify-content-center align-items-center">
										<span class="icon-link"></span>
									</div>
								</a>
								<div class="text p-3">
									<div class="d-flex">
										<div class="one">
											<h3>
												<a href="#">New Orleans, LA</a>
											</h3>
											<p class="rate">
												<i class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star-o"></i> <span>8 Rating</span>
											</p>
										</div>
										<div class="two">
											<span class="price per-price">$40<br> <small>/night</small></span>
										</div>
									</div>
									<p>Far far away, behind the word mountains, far from the
										countries</p>
									<hr>
									<p class="bottom-area d-flex">
										<span><i class="icon-map-o"></i> Miami, Fl</span> <span
											class="ml-auto"><a href="#">Book Now</a></span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm col-md-6 col-lg-4 ftco-animate">
							<div class="destination">
								<a href="#"
									class="img img-2 d-flex justify-content-center align-items-center"
									style="background-image: url(/resources/images/hotel-5.jpg);">
									<div
										class="icon d-flex justify-content-center align-items-center">
										<span class="icon-link"></span>
									</div>
								</a>
								<div class="text p-3">
									<div class="d-flex">
										<div class="one">
											<h3>
												<a href="#">New Orleans, LA</a>
											</h3>
											<p class="rate">
												<i class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star-o"></i> <span>8 Rating</span>
											</p>
										</div>
										<div class="two">
											<span class="price per-price">$40<br> <small>/night</small></span>
										</div>
									</div>
									<p>Far far away, behind the word mountains, far from the
										countries</p>
									<hr>
									<p class="bottom-area d-flex">
										<span><i class="icon-map-o"></i> Miami, Fl</span> <span
											class="ml-auto"><a href="#">Book Now</a></span>
									</p>
								</div>
							</div>
						</div>
						<div class="col-sm col-md-6 col-lg-4 ftco-animate">
							<div class="destination">
								<a href="#"
									class="img img-2 d-flex justify-content-center align-items-center"
									style="background-image: url(/resources/images/hotel-5.jpg);">
									<div
										class="icon d-flex justify-content-center align-items-center">
										<span class="icon-link"></span>
									</div>
								</a>
								<div class="text p-3">
									<div class="d-flex">
										<div class="one">
											<h3>
												<a href="#">New Orleans, LA</a>
											</h3>
											<p class="rate">
												<i class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star"></i> <i class="icon-star"></i> <i
													class="icon-star-o"></i> <span>8 Rating</span>
											</p>
										</div>
										<div class="two">
											<span class="price per-price">$40<br> <small>/night</small></span>
										</div>
									</div>
									<p>Far far away, behind the word mountains, far from the
										countries</p>
									<hr>
									<p class="bottom-area d-flex">
										<span><i class="icon-map-o"></i> Miami, Fl</span> <span
											class="ml-auto"><a href="#">Book Now</a></span>
									</p>
								</div>
							</div>
						</div>
					</div>

					<div class="row mt-5">
						<div class="col text-center">
							<div class="block-27">
								<ul>
									<li><a href="#">&lt;</a></li>
									<li class="active"><span>1</span></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">&gt;</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- .col-md-8 -->
			</div>
		</div>
	</section>
	<!-- .section -->

	<section class="ftco-section-parallax">
		<div class="parallax-img d-flex align-items-center">
			<div class="container">
				<div class="row d-flex justify-content-center">
					<div
						class="col-md-7 text-center heading-section heading-section-white ftco-animate">
						<h2>Subcribe to our Newsletter</h2>
						<p>Far far away, behind the word mountains, far from the
							countries Vokalia and Consonantia, there live the blind texts.
							Separated they live in</p>
						<div class="row d-flex justify-content-center mt-5">
							<div class="col-md-8">
								<form action="#" class="subscribe-form">
									<div class="form-group d-flex">
										<input type="text" class="form-control"
											placeholder="Enter email address"> <input
											type="submit" value="Subscribe" class="submit px-3">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- footer에 js몇개 들어있음. 하단에넣지않으면 템플릿이 작동안함. main.js 랑 aos.js인가 그거랑 연결되잇는다른게 있는덧?  -->
	<%@include file="/WEB-INF/jsp/include/inc_footer.jsp"%>

</body>
</html>