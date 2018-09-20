<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>

	<!-- Basic Page Needs
  ================================================== -->
	<meta charset="utf-8">
	<title>EVOLVE</title>
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Mobile Specific Metas
  ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<!-- CSS
  ================================================== -->
	<link rel="stylesheet" href="css/style.css">
	
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	<link rel="stylesheet" type="text/css" href="css/component.css" />
	<script src="js/modernizr.custom.js"></script>

    <!-- JS
  ================================================== -->
    <script src="js/jquery-1.8.2.min.js" type="text/javascript"></script> <!-- jQuery -->
	<script src="js/jquery.easing.1.3.js" type="text/javascript"></script> <!-- jQuery easing -->
	<script src="js/modernizr.custom.js" type="text/javascript"></script> <!-- Modernizr -->
    <script src="js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script> <!-- tabs, toggles, accordion -->
    <script src="js/custom.js" type="text/javascript"></script> <!-- jQuery initialization -->
	
		<script src="js/jquery.cbpQTRotator.min.js"></script>
		<script>
			$( function() {
				/*
				- how to call the plugin:
				$( selector ).cbpQTRotator( [options] );
				- options:
				{
					// default transition speed (ms)
					speed : 700,
					// default transition easing
					easing : 'ease',
					// rotator interval (ms)
					interval : 8000
				}
				- destroy:
				$( selector ).cbpQTRotator( 'destroy' );
				*/

				$( '#cbp-qtrotator' ).cbpQTRotator();

			} );
		</script>
  
    <!-- Responsive Menu -->
    <script src="js/jquery.meanmenu.js"></script> 
    <script>
    jQuery(document).ready(function () {
    jQuery('header nav').meanmenu();
    });
    </script>
	
	<!-- Tooltip -->
    <script src="js/tooltip.js"></script>
	
	<!-- Flexisel -->
    <script type="text/javascript" src="js/jquery.flexisel.js"></script>

	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">

</head>
<body>



	<!-- Primary Page Layout
	================================================== -->

<header class="header">
<div class="container">
<div class="logos columns">
<h1 class="logo"><a href="index.jsp"><img src="images/logo.png" alt="EVOLVE" /></a></h1>
</div><!-- logo -->

<div class="twelve columns">
<nav class="main_menu">

<ul>
<li>
<a href="index.jsp">
Home
<div class="sub">
Get Started
</div>
</a>
<ul>
<li><a href="index.jsp">Home Version 1</a></li>
<li><a href="index-2.jsp">Home Version 2</a></li>
<li><a href="index-3.jsp">Home Version 3</a></li>
<li><a href="index-4.jsp">Home Version 4</a></li>
<li><a href="index-5.jsp">Home Version 5</a></li>
<li><a href="index-6.jsp">Home Version 6</a></li><li><a href="http://www.weidea.net">More</a></li>
</ul>
</li>

<li class="current_page_item">
<a href="about-us.jsp">
Pages
<div class="sub">
Page Formats
</div>
</a>
<ul>
<li><a href="about-us.jsp">About Us</a></li>
<li><a href="about-us-2.jsp">About Us 2</a></li>
<li><a href="about-me.jsp">About Me</a></li>
<li><a href="services.jsp">Services</a></li>
<li><a href="services-2.jsp">Services 2</a></li>
<li><a href="meet-the-team.jsp">Meet The Team</a></li>
<li><a href="meet-the-team-2.jsp">Meet The Team 2</a></li>
<li><a href="faq.jsp">FAQ</a></li>
<li><a href="full-width-page.jsp">Full Width Page</a></li>
<li><a href="page-right-sidebar.jsp">Right Sidebar</a></li>
<li><a href="page-left-sidebar.jsp">Left Sidebar</a></li>
<li><a href="404-error-page.jsp">404 Error Page</a></li>
</ul>
</li>

<li>
<a href="#">
Shortcodes
<div class="sub">
Useful Shortcodes
</div>
</a>
<ul>
<li><a href="tabs.jsp">Tabs</a></li>
<li><a href="buttons.jsp">Buttons</a></li>
<li><a href="dropcaps.jsp">Dropcaps</a></li>
<li><a href="accordion-toggles.jsp">Accordion & Toggles</a></li>
<li><a href="grid-columns.jsp">Grid Columns</a></li>
<li><a href="images.jsp">Images</a></li>
<li><a href="video.jsp">Video</a></li>
</ul>
</li>

<li>
<a href="portfolio-2-columns.jsp">
Portfolio
<div class="sub">
Awesome Stuff
</div>
</a>
<ul>
<li><a href="portfolio-1-column.jsp">1 Column</a></li>
<li><a href="portfolio-2-columns.jsp">2 Columns</a></li>
<li><a href="portfolio-3-columns.jsp">3 Columns</a></li>
<li><a href="portfolio-4-columns.jsp">4 Columns</a></li>
<li><a href="single-project-half.jsp">Single Project Half</a></li>
<li><a href="single-project-wide.jsp">Single Project Wide</a></li>
</ul>
</li>

<li>
<a href="blog-large.jsp">
The Blog
<div class="sub">
News & Events
</div>
</a>
<ul>
<li><a href="blog-large.jsp">Large Image</a></li>
<li><a href="blog-medium.jsp">Medium Image</a></li>
<li><a href="blog-post.jsp">Single Post</a></li>
</ul>
</li>

<li>
<a href="contact.jsp">
Contact US
<div class="sub">
Write Message
</div>
</a>
</li>
</ul>

</nav><!-- navigation -->
</div>
<div class="clearfix"></div>
</div>
</header><!-- header -->

<div class="container">
<div class="sixteen columns page-title">
<div class="eight columns alpha">
<h3> <span>About Us</span> </h3>
</div>
<div class="eight columns omega">
<nav class="breadcrumbs">
<ul>
<li>You are here:</li>
<li>
<a href="#">Home</a>
</li>
<li>About Us</li>
</ul>
</nav>
</div>
<div class="clearfix"></div>
</div><!-- page-title -->
</div><!-- container -->

<div class="container">

<div class="sixteen columns">
					<div class="carousel">
						<div class="carousel-content">
							<img class="carousel-item" src="images/about/slide1.jpg" alt="">					
							<img class="carousel-item" src="images/about/slide2.jpg" alt="">					
							<img class="carousel-item" src="images/about/slide3.jpg" alt="">					
						</div>
					</div>
</div>

<div class="separator"></div>

<div class="eleven columns">
<h4 class="headline">We are here to serve you</h4>
<p> 
Lid est laborum dolo rumes fugats untras. Etharums ser quidem rerum facilis dolores nemis omnis fugats vitaes nemo minima rerums unsers sadips amets. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui.
</p>
<p>
Ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Asunt in anim uis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla.
</p>
</div>

<div class="five columns">
					<h4 class="headline">Our Skills</h3>

					<div class="element-wrap">					
						<div class="progress-bar" data-capacity="100" data-value="97">
							<div class="progress-label">Web Design 91%</div>

							<div class="progress-scale">
								<div class="progress-line"></div>
							</div>
						</div>

						<div class="progress-bar" data-capacity="100" data-value="86">
							<div class="progress-label">HTML/CSS 86%</div>

							<div class="progress-scale">
								<div class="progress-line m-2"></div>
							</div>
						</div>

						<div class="progress-bar" data-capacity="100" data-value="78">
							<div class="progress-label">Graphic Design 78%</div>

							<div class="progress-scale">
								<div class="progress-line m-3"></div>
							</div>
						</div>

					</div>
</div>
				
<div class="clearfix"></div>

<div class="separator"></div>

<div class="sixteen columns">
        <h2 style="text-align:center; font-size:24px; text-transform:uppercase;">Our amazing team.</h2>
		<p style="text-align:center; margin-bottom:40px; font-size:15px;">We could not do our success without this girls and men. They are just fantastic!</p>
</div>
            <div class="team-member one-third column">
                <img class="photo" src="images/team/team-1.jpg" alt="Team Member">
                <div class="content">
                    <h3 class="name">John Doe</h3>
                    <span class="job-title">Director and Founder</span>
                </div>

            </div>
            
            <div class="team-member one-third column">
                <img class="photo" src="images/team/team-2.jpg" alt="Team Member">
                <div class="content">
                    <h3 class="name">Jane Doe</h3>
                    <span class="job-title">Web Designer</span>
                </div>

            </div>
            
            <div class="team-member one-third column">
                <img class="photo" src="images/team/team-3.jpg" alt="Team Member">
                <div class="content">
                    <h3 class="name">John Doe</h3>
                    <span class="job-title">Web Developer</span>
                </div>

            </div>
			
    <div class="clearfix"></div>
    <div class="separator"></div>
	
    	<div class="sixteen columns">
        <h2 style="text-align:center; font-size:24px; text-transform:uppercase;">Our clients.</h2>
		<p style="text-align:center; margin-bottom:40px; font-size:15px;">They trust in our work because we are making all the best we can for them.</p>
	    </div>
	
    	<div class="sixteen columns clients">
		<!-- Start brand carousel -->
			<ul id="flexiselDemo2">
            <li><img src="images/client1.png" alt="" /></li>
            <li><img src="images/client2.png" alt="" /></li>
            <li><img src="images/client3.png" alt= "" /></li>
            <li><img src="images/client4.png" alt= "" /></li>
            <li><img src="images/client5.png" alt= "" /></li>
            </ul>
            <div class="clearout"></div>
		<!-- End brand carousel -->
	    </div>	
		
<div class="separator"></div>

<div class="sixteen columns">
<hr style="border-top: 1px dashed #DEE1E2;">
</div>

<div class="sixteen columns">
        <h2 style="text-align:center; font-size:24px; text-transform:uppercase;">What they have to say</h2>
		<p style="text-align:center; margin-bottom:40px; font-size:15px;">Few testimonials of our amazing clients that we have worked with in the past.</p>
		
				<div id="cbp-qtrotator" class="cbp-qtrotator">
					<div class="cbp-qtcontent">
						<img src="images/1.jpg" alt="img01" />
						<blockquote>
						  <p>People eat meat and think they will become as strong as an ox, forgetting that the ox eats grass.</p>
						  <footer>Pino Caruso</footer>
						</blockquote>
					</div>
					<div class="cbp-qtcontent">
						<img src="images/2.jpg" alt="img02" />
						<blockquote>
						  <p>Nothing will benefit human health and increase the chances for survival of life on Earth as much as the evolution to a vegetarian diet.</p>
						  <footer>Albert Einstein</footer>
						</blockquote>
					</div>
					<div class="cbp-qtcontent">
						<img src="images/3.jpg" alt="img03" />
						<blockquote>
						  <p>If you don't want to be beaten, imprisoned, mutilated, killed or tortured then you shouldn't condone such behaviour towards anyone, be they human or not.</p>
						  <footer>Moby</footer>
						</blockquote>
					</div>
					<div class="cbp-qtcontent">
						<img src="images/4.jpg" alt="img04" />
						<blockquote>
						  <p>My body will not be a tomb for other creatures.</p>
						  <footer>Leonardo Da Vinci</footer>
						</blockquote>
					</div>
				</div>
</div>

</div><!-- container -->

	<footer class="footer">
    <div class="container">
	<div class="footer-top clearfix">
	<div class="four columns">
	<h3>About Us</h3>
	<p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat.</p>
    <p>Vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui.</p>
	</div>
	<div class="four columns">
						<h3>Latest Tweets</h3>
						<div class="twitter">
							<ul>
								<!-- Twitter Message 1 -->
								<li>
									<span>Sed ut perspiciatis unde omnis iste natus error sit voluptatem <a href="#" class="link">http://twitter.com</a></span>
									<span class="twit-date">Jan 7, 2013</span>
								</li>
								<!-- Twitter Message 2 -->
								<li>
									<span>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aliquid :) <a href="#" class="link">http://twitter.com</a></span>
									<span class="twit-date">Jan 7, 2013</span>
								</li>
							</ul>
						</div>
</div>
<div class="four columns">
<h3>Flickr Stream</h3>
						<div class="latest-project">
							<div class="latest-project-item">
								<a href="#"><img src="images/elements/rp-1.jpg" alt=""></a>
							</div>
							<div class="latest-project-item">
								<a href="#"><img src="images/elements/rp-2.jpg" alt=""></a>
							</div>
							<div class="latest-project-item">
								<a href="#"><img src="images/elements/rp-3.jpg" alt=""></a>
							</div>
							<div class="latest-project-item">
								<a href="#"><img src="images/elements/rp-4.jpg" alt=""></a>
							</div>
							<div class="latest-project-item">
								<a href="#"><img src="images/elements/rp-5.jpg" alt=""></a>
							</div>
							<div class="latest-project-item">
								<a href="#"><img src="images/elements/rp-6.jpg" alt=""></a>
							</div>
						</div>
</div>
<div class="four columns">
						<h3>Contact Information</h3>
						<ul class="list contact" style="margin-bottom: 15px;">
							<li class="contact-address"><i class="fa fa-map-marker"></i><span>103088. Ut wisi enim ad minim veniam, quis nostrud.</span></li>
							<li class="contact-mail"><i class="fa fa-envelope"></i><a class="link" href="#">mail@mail.com</a></li>
							<li class="contact-phone"><i class="fa fa-phone"></i><span>+1 (229) 991-22-11</span></li>
							<li class="contact-address"><i class="fa fa-clock-o"></i><span>Monday-Friday: 9:<sup>00</sup> - 18:<sup>00</sup><br>
							Saturday: 10:<sup>00</sup> - 17:<sup>00</sup><br>
							Sunday: closed</span>
							</li>
						</ul>
		
<div class="tooltips">			
			<ul class="social-icons-footer">
				<li><a href="#" data-rel="tooltip" title="Twitter"><i class="fa fa-twitter"></i></a></li>
				<li><a href="#" data-rel="tooltip" title="Facebook"><i class="fa fa-facebook"></i></a></li>
				<li><a href="#" data-rel="tooltip" title="Google+"><i class="fa fa-google-plus"></i></a></li>
				<li><a href="#" data-rel="tooltip" title="Pinterest"><i class="fa fa-pinterest"></i></a></li>
				<li><a href="#" data-rel="tooltip" title="LinkedIn"><i class="fa fa-linkedin"></i></a></li>
				<li><a href="#" data-rel="tooltip" title="Dribbble"><i class="fa fa-dribbble"></i></a></li>
				<li><a href="#" data-rel="tooltip" title="RSS"><i class="fa fa-rss"></i></a></li>
			</ul>
</div>

</div>
	</div><!-- footer-top -->
	</div>

	<div class="container">
	<div class="footer-bottom clearfix">
	<div class="eight columns">
	<p>Copyright &copy; 2018 <a href="#">Great Tilly</a>. More Templates <a href="Great Tilly" target="_blank" title="Great Tilly">Great Tilly</a> - Collect from <a href="Great Tilly" title="Great Tilly" target="_blank">Great Tilly</a>.</p>
	</div>
	<div class="eight columns">
	<nav class="footer-nav">
<ul>
<li>
<a class="first" href="index.jsp">Home</a>
/
</li>
<li>
<a href="#">Shortcodes</a>
/
</li>
<li>
<a href="portfolio-2-columns.jsp">Portfolio</a>
/
</li>
<li>
<a href="blog-large.jsp">Blog</a>
/
</li>
<li>
<a href="contact.jsp">Contact</a>
</li>
</ul>
</nav>
</div>
</div><!-- footer-bottom -->
</div>
	
</footer><!-- footer -->

<script type="text/javascript">

$(window).load(function() {
	$("#flexiselDemo2").flexisel({
		visibleItems: 5,
		animationSpeed: 1000,
		autoPlay: false,
		autoPlaySpeed: 3000,    		
		pauseOnHover: true,
		enableResponsiveBreakpoints: true,
    	responsiveBreakpoints: { 
    		portrait: { 
    			changePoint:480,
    			visibleItems: 1
    		}, 
    		landscape: { 
    			changePoint:640,
    			visibleItems: 2
    		},
    		tablet: { 
    			changePoint:768,
    			visibleItems: 3
    		}
    	}
    });
    
});
</script>

<!-- End Document
================================================== -->
</body>
</html>