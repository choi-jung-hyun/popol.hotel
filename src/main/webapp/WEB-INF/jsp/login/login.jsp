<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<!DOCTYPE html>
<html>
<head>
<style>
@import url('https://fonts.googleapis.com/css?family=Numans');

html,body{
background-image: url('http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg');
background-size: cover;
background-repeat: no-repeat;
height: 100%;
font-family: 'Numans', sans-serif;
}

.container{
height: 100%;
align-content: center;
}

.card{
height: 370px;
margin-top: auto;
margin-bottom: auto;
width: 400px;
background-color: rgba(0,0,0,0.5) !important;
}

.social_icon span{
font-size: 60px;
margin-left: 10px;
color: #FFC312;
}

.social_icon span:hover{
color: white;
cursor: pointer;
}

.card-header h3{
color: white;
}

.social_icon{
position: absolute;
right: 20px;
top: -45px;
}

.input-group-prepend span{
width: 50px;
background-color: #FFC312;
color: black;
border:0 !important;
}

input:focus{
outline: 0 0 0 0  !important;
box-shadow: 0 0 0 0 !important;

}

.remember{
color: white;
}

.remember input
{
width: 20px;
height: 20px;
margin-left: 15px;
margin-right: 5px;
}

.login_btn{
color: black;
background-color: #FFC312;
width: 100px;
}

.login_btn:hover{
color: black;
background-color: white;
}

.links{
color: white;
}

.links a{
margin-left: 4px;
}
</style>
	<title>Login</title>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
 	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<!--template 시작  -->
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!--Bootsrap 4 CDN-->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <!--Fontawesome CDN-->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
	<!--Custom styles-->
	<link rel="stylesheet" type="text/css" href="styles.css">
	<!--template 끝  -->
	<!--RSA 시작  -->
	<script src="/resources/js/rsa/jsbn.js"></script>
	<script src="/resources/js/rsa/prng4.js"></script>
	<script src="/resources/js/rsa/rng.js"></script>
	<script src="/resources/js/rsa/rsa.js"></script>
	<!--RSA 끝  -->
	<script>
	
	function goSign_up(){
		location.href = '/member/goSign_up.do';
	}
	
	function getKey() {
		$.ajax({
			type : 'POST',
			url : '/include/getKey.do'

		}).done(function(map) {
			loginProc(map);
			console.log(map);
		})
	}
	
	function loginProc(map){
		//RSA 암호화
		var rsa = new RSAKey();
		rsa.setPublic(map.pubKeyModule, map.pubKeyExponent);
		
		if($("#userEmail").val() == '' || $("#userEmail").val() == null){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		if($("#userPw").val() == '' || $("#userPw").val() == null){
			alert("패스워드를 입력해주세요.");
			return false;
		}
		
		$.ajax({
			type : 'POST',
			url : '/login/loginProc.do',
			data : {
				userEmail : rsa.encrypt($("#userEmail").val()),
				userPw : rsa.encrypt($("#userPw").val()),
				keepLogin : $("input:checkbox[id='keepLogin']").is(":checked") == true ? 'Y' : 'N'
			}
		
		}).done(function(data){
			//로그인성공
			if(data.resultCode == 1){
				alert(data.msg);
				location.href = '/main/main.do';
			}else{
				alert(data.msg);
			}
			
			
			
		})
	}
	
	</script>
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign In</h3>
				<div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>
			</div>
			<div class="card-body">
				<form action="" method="post">
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-user"></i></span>
						</div>
						<input type="email" class="form-control" id="userEmail" placeholder="이메일">
						
					</div>
					<div class="input-group form-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fas fa-key"></i></span>
						</div>
						<input type="password" class="form-control" id="userPw" placeholder="비밀번호">
					</div>
					<div class="row align-items-center remember">
						<input type="checkbox" id="keepLogin">아이디 저장
					</div>
					<div class="form-group">
						<a href="javascript:getKey();" class="btn float-right login_btn">Login</a>
					  <a href="${naver_url}"><img height="30" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
					</div>
					
				</form>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links">
					계정이 필요하신가요?<a href="javascript:goSign_up();">회원가입</a>
				</div>
				<div class="d-flex justify-content-center">
					<a href="#">비밀번호를 찾으시나요?</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>